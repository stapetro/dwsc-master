package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bg.unisofia.fmi.dwsc.yani.model.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

/**
 * This class provides functionality for working with qosElements for the YANI
 * BPEL Extension.
 * 
 * @author krasimir.baylov
 * 
 */
public class QoSUtils {

	/**
	 * Name of the element containing quality attribute requirements data
	 */
	private static final String QUALITY_ATTRIBUTE_ELEMENT_TAG_NAME = "qualityAttribute";

	/**
	 * Name of the attribute containing the value for certain quality attribute
	 */
	private static final String VALUE_ATTRIBUTE_NAME = "value";

	/**
	 * Name of the attribute containing the name of the quality attribute
	 */
	private static final String QUALITY_ATTRIBUTE_NAME = "name";

	/**
	 * Name of the attribute containing the cost of the quality attribute
	 */
	private static final String QUALITY_COST_ATTRIBTE_NAME = "cost";

	/**
	 * Default cost value for quality attributes
	 */
	private static final String DEFAULT_COST_VALUE = "0";

	/**
	 * Provide the quality profile for the current process. The quality profile
	 * specifies all quality requirements that the user requires
	 * 
	 * @param qosElement
	 *            element containing the user defined requirements
	 * @return the generated quality profile
	 */
	public QualityProfile getQualityProfile(Element qosElement) {
		if (qosElement == null) {
			return null;
		}

		Map<QualityAttributeEnum, String> qualityRequirements = new HashMap<QualityAttributeEnum, String>();
		Map<QualityAttributeEnum, String> qualityCost = new HashMap<QualityAttributeEnum, String>();

		String namespaceUri = qosElement.getNamespaceURI();
		NodeList attributesNodeList = qosElement.getElementsByTagNameNS(
				namespaceUri, QUALITY_ATTRIBUTE_ELEMENT_TAG_NAME);

		for (int i = 0; i < attributesNodeList.getLength(); i++) {
			Node node = attributesNodeList.item(i);
			NamedNodeMap nodeAttributes = node.getAttributes();
			Node nameNode = nodeAttributes.getNamedItem(QUALITY_ATTRIBUTE_NAME);
			Node valueNode = nodeAttributes.getNamedItem(VALUE_ATTRIBUTE_NAME);
			Node costNode = nodeAttributes
					.getNamedItem(QUALITY_COST_ATTRIBTE_NAME);

			String attributeName = nameNode.getTextContent();
			QualityAttributeEnum qualityAttribute = QualityAttributeEnum
					.getQualityAttributeEnumFromString(attributeName);
			String attributeValue = valueNode.getTextContent();

			String costValue = costNode == null ? DEFAULT_COST_VALUE : costNode
					.getTextContent();

			qualityRequirements.put(qualityAttribute, attributeValue);
			qualityCost.put(qualityAttribute, costValue);
		}

		QualityProfile qualityProfile = new QualityProfile(qualityRequirements,
				qualityCost);
		return qualityProfile;
	}
}
