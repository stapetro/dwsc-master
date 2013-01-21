package bg.unisofia.fmi.dwsc.bpel.extension.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EPRManipulator {

	public Element updateEPR(Element eprElement, String newEndpointAddress) {
		if (eprElement == null) {
			return null;
		}

		NodeList addressElementList = eprElement
				.getElementsByTagName("Address");
		// TODO check the length of the list - it should be EXACTLY 1
		Node addressNode = addressElementList.item(0);

		System.out.println("before -> " + addressNode.getTextContent());
		addressNode.setTextContent(newEndpointAddress);
		System.out.println("before -> " + addressNode.getTextContent());
		return eprElement;
	}

}
