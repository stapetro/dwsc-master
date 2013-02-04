package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.ode.utils.DOMUtils;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class EPRManipulatorTest {
	private static final String ENDPOINT_REFERENCE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<service-ref xmlns=\"http://docs.oasis-open.org/wsbpel/2.0/serviceref\">"
			+ "	<EndpointReference xmlns=\"http://www.w3.org/2005/08/addressing\">"
			+ "		<Metadata>"
			+ "			<ServiceName xmlns=\"http://www.w3.org/2006/05/addressing/wsdl\" EndpointName=\"AdditionServiceSlowHttpSoap12Endpoint\" xmlns:servicens=\"http://addition.ws.fmi.unisofia.bg\">servicens:AdditionServiceSlow</ServiceName>"
			+ "		</Metadata>"
			+ "		<Address>http://localhost:8080/TestServices/services/AdditionServiceSlow.AdditionServiceSlowHttpSoap12Endpoint/</Address>"
			+ "	</EndpointReference>" + "</service-ref>";
	
	private static final String NEW_ENDPOINT_ADDRESS = "http://localhost:8080/TestServices/services/AdditionServiceStandard.AdditionServiceStandardHttpSoap12Endpoint/";
	
	@Test
	public void testUpdateEpr() throws SAXException, IOException{
		Element eprElement = DOMUtils.stringToDOM(ENDPOINT_REFERENCE_XML);
		EPRManipulator eprManipulator = new EPRManipulator();
		eprManipulator.updateEPR(eprElement, NEW_ENDPOINT_ADDRESS);
		
		//TODO complete the test
	}
	
	@Test
	public void testGetEndpointAddressFromEPR() throws SAXException, IOException{
		Element eprElement = DOMUtils.stringToDOM(ENDPOINT_REFERENCE_XML);
		EPRManipulator eprManipulator = new EPRManipulator();
		String actualAddress = eprManipulator.getEndPointAddressFromEPR(eprElement);
		String extectedAddress = "http://localhost:8080/TestServices/services/AdditionServiceSlow.AdditionServiceSlowHttpSoap12Endpoint/";
	
		Assert.assertEquals(extectedAddress, actualAddress);
	}
}
