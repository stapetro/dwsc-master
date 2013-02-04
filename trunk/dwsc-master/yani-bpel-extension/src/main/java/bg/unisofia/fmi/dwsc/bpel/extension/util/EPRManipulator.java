package bg.unisofia.fmi.dwsc.bpel.extension.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EPRManipulator {

	private final Log log = LogFactory.getLog(getClass());

	/**
	 * Name of the Address element
	 */
	private static final String ADDRESS_ELEMENT_NAME = "Address";

	/**
	 * Provide the endpoint address from a partnerlink ERP element
	 * 
	 * @param eprElement
	 * @return
	 */
	public String getEndPointAddressFromEPR(Element eprElement) {
		if (eprElement == null) {
			return null;
		}

		NodeList addressNodeList = eprElement
				.getElementsByTagName(ADDRESS_ELEMENT_NAME);
		if (addressNodeList.getLength() != 1) {
			return null;
		}

		Node addressNode = addressNodeList.item(0);
		String endpointAddress = addressNode.getTextContent();

		return endpointAddress;
	}

	/**
	 * Update the endpoint reference element with the provided address
	 * 
	 * @param eprElement
	 *            EPR element to update
	 * @param newEndpointAddress
	 *            the new address to set
	 * @return the updated EPR element
	 */
	public Element updateEPR(Element eprElement, String newEndpointAddress) {
		if (eprElement == null) {
			return null;
		}
		log.info("Updating endpoint reference...");

		NodeList addressElementList = eprElement
				.getElementsByTagName(ADDRESS_ELEMENT_NAME);
		// TODO check the length of the list - it should be EXACTLY 1
		Node addressNode = addressElementList.item(0);

		log.info("Old endpoint reference address: "
				+ addressNode.getTextContent());
		addressNode.setTextContent(newEndpointAddress);
		log.info("New endpoint reference address: "
				+ addressNode.getTextContent());

		return eprElement;
	}

}
