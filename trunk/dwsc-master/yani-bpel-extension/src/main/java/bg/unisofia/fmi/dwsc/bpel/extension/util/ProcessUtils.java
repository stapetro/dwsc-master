package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.ode.bpel.common.FaultException;
import org.apache.ode.bpel.o.OPartnerLink;
import org.apache.ode.bpel.o.OProcess;
import org.apache.ode.bpel.runtime.BpelRuntimeContext;
import org.apache.ode.bpel.runtime.PartnerLinkInstance;
import org.apache.ode.bpel.runtime.extension.ExtensionContext;
import org.w3c.dom.Element;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;

public class ProcessUtils {

	public List<String> getPartnerLinkNamesForQoSSelection(Element qosElement) {
		return null;
	}

	public PartnerLinkDefinition getQoSPartnerLinksDefinition(
			ExtensionContext context) throws FaultException {
		OProcess processModel = context.getProcessModel();
		BpelRuntimeContext bpelContext = context.getInternalInstance();
		Set<OPartnerLink> partnerLinksSet = processModel.getAllPartnerLinks();
		Iterator<OPartnerLink> it = partnerLinksSet.iterator();

		PartnerLinkInstance plInstance = context
				.getPartnerLinkInstance("AdditionPL");
		System.out.println("pl instance -----> " + plInstance.toString());

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
