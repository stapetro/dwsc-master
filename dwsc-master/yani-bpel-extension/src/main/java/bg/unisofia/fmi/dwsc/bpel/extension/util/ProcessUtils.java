package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.o.OPartnerLink;
import org.apache.ode.bpel.o.OProcess;
import org.apache.ode.bpel.runtime.BpelRuntimeContext;
import org.apache.ode.bpel.runtime.PartnerLinkInstance;
import org.apache.ode.bpel.runtime.extension.ExtensionContext;
import org.apache.ode.utils.DOMUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;

public class ProcessUtils {

	private static final String PARTNERLINK_CATEGORY_ATTRIBUTE_NAME = "partnerLinkCategory";

	private static final String PARTNERLINK_CATEGORY_NAME_ATTRIBUTE_NAME = "category";

	private static final String PARTNERLINK_NAME_ATTRIBUTE_NAME = "partnerLinkName";

	public List<PartnerLinkDefinition> getPartnerLinkDefinitions(
			ExtensionContext context, Element qosElement) throws FaultException {
		if (qosElement == null) {
			return null;
		}
		List<PartnerLinkDefinition> plDefinitionsList = getPartnerLinkDefinitionNames(qosElement);
		updatePartnerLinkDefinitionsWithEndpoints(plDefinitionsList, context);
		return plDefinitionsList;
	}

	/**
	 * Provide a PartnerLinkDefinition list with only partner link name and
	 * category
	 * 
	 * @param qosElement
	 *            qos element to obtain the partnerlink information
	 * @return List of PartnerLinkDefinition elements
	 */
	public List<PartnerLinkDefinition> getPartnerLinkDefinitionNames(
			Element qosElement) {
		if (qosElement == null) {
			return null;
		}

		List<PartnerLinkDefinition> plDefinitionsList = new LinkedList<PartnerLinkDefinition>();

		String namespaceUri = qosElement.getNamespaceURI();
		NodeList attributesNodeList = qosElement.getElementsByTagNameNS(
				namespaceUri, PARTNERLINK_CATEGORY_ATTRIBUTE_NAME);

		for (int i = 0; i < attributesNodeList.getLength(); i++) {
			Node plCategoryNode = attributesNodeList.item(i);
			NamedNodeMap nodeAttributes = plCategoryNode.getAttributes();
			Node categoryNode = nodeAttributes
					.getNamedItem(PARTNERLINK_CATEGORY_NAME_ATTRIBUTE_NAME);
			Node plNameNode = nodeAttributes
					.getNamedItem(PARTNERLINK_NAME_ATTRIBUTE_NAME);

			String category = categoryNode.getTextContent();
			String plName = plNameNode.getTextContent();

			PartnerLinkDefinition plDefinition = new PartnerLinkDefinition(
					plName, category, null, null);
			plDefinitionsList.add(plDefinition);
		}

		return plDefinitionsList;
	}

	public void updatePartnerLinkDefinitionsWithEndpoints(
			List<PartnerLinkDefinition> plDefinitionsList,
			ExtensionContext context) throws FaultException {
		if (plDefinitionsList == null || context == null) {
			return;
		}

		BpelRuntimeContext bpelContext = context.getInternalInstance();
		EPRManipulator eprManipulator = new EPRManipulator();
		for (int i = 0; i < plDefinitionsList.size(); i++) {
			PartnerLinkDefinition plDefinition = plDefinitionsList.get(i);
			String plDefinitionName = plDefinition.getName();
			PartnerLinkInstance plInstance = context
					.getPartnerLinkInstance(plDefinitionName);

			Element plEPRElement = bpelContext
					.fetchPartnerRoleEndpointReferenceData(plInstance);
			String endpointAddress = eprManipulator.getEndPointAddressFromEPR(plEPRElement);

			plDefinition.setEndpointReference(endpointAddress);
		}
	}

	@Deprecated
	public PartnerLinkDefinition getQoSPartnerLinksDefinition(
			ExtensionContext context) throws FaultException {
		OProcess processModel = context.getProcessModel();
		BpelRuntimeContext bpelContext = context.getInternalInstance();

		Set<OPartnerLink> partnerLinksSet = processModel.getAllPartnerLinks();
		Iterator<OPartnerLink> it = partnerLinksSet.iterator();

		// TODO update the hard coded partner link name
		PartnerLinkInstance plInstance = context
				.getPartnerLinkInstance("AdditionPL");

		// Element plEPRElement =
		// bpelContext.fetchMyRoleEndpointReferenceData(plInstance);
		Element plEPRElement = bpelContext
				.fetchPartnerRoleEndpointReferenceData(plInstance);
		System.out.println("pl instance -----> "
				+ DOMUtils.domToString(plEPRElement));

		String newEndpointAddress = "http://localhost:8080/TestServices/services/AdditionServiceStandard.AdditionServiceStandardHttpSoap12Endpoint/";
		EPRManipulator eprManipulator = new EPRManipulator();
		Element newEprElement = eprManipulator.updateEPR(plEPRElement,
				newEndpointAddress);
		bpelContext.writeEndpointReference(plInstance, newEprElement);

		// while(it.hasNext()){
		// OPartnerLink pl = it.next();
		//
		// PartnerLinkInstance plInstance = context.getPartnerLinkInstance(pl);
		// System.out.println("pl instance -----> " + plInstance.toString());
		// bpelContext.fet
		// //Element plEPRElement =
		// bpelContext.fetchMyRoleEndpointReferenceData(plInstance);
		// //System.out.println("pl EPR to String -> " +
		// DOMUtils.domToString(plEPRElement));
		// }

		return null;
	}
}
