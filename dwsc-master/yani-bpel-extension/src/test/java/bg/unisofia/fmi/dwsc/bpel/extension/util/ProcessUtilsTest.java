package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.apache.ode.utils.DOMUtils;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;

public class ProcessUtilsTest {

	private static final String QOS_XML_ELEMENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<yani:ComposeQoS odebpelc:lineno=\"63\" xmlns:odebpelc=\"urn:org.apache.ode.bpel.compiler\" xmlns:yani=\"http://yaniwscomposer.com/YaniWsComposer\">"
			+ "	<yani:QoS odebpelc:lineno=\"64\">"
			+ "		<yani:qualityAttribute name=\"executionTime\" odebpelc:lineno=\"65\" value=\"0.432\"/>"
			+ "		<yani:qualityAttribute name=\"availability\" odebpelc:lineno=\"66\" value=\"0.7\"/>"
			+ "		<yani:qualityAttribute name=\"throughput\" odebpelc:lineno=\"67\" value=\"33\"/>"
			+ "	</yani:QoS>"
			+ "	<yani:partnerLinkCategories odebpelc:lineno=\"69\">"
			+ "		<yani:partnerLinkCategory category=\"addition\" odebpelc:lineno=\"70\" partnerLinkName=\"AdditionPL\"/>"
			+ "	</yani:partnerLinkCategories>" + "</yani:ComposeQoS>";
	
	private static final String PARTNERLINK_NAME = "AdditionPL";
	private static final String PARTNERLINK_CATEGORY = "addition";
	
	@Test
	public void testGetPartnerLinkNamesWithOneElement() throws SAXException, IOException{
		Element qosElement = DOMUtils.stringToDOM(QOS_XML_ELEMENT);
		ProcessUtils processUtils = new ProcessUtils();
		List<PartnerLinkDefinition> plDefinitions = processUtils.getPartnerLinkDefinitionNames(qosElement);
		
		Assert.assertEquals(1, plDefinitions.size());
		Assert.assertEquals(PARTNERLINK_NAME, plDefinitions.get(0).getName());
		Assert.assertEquals(PARTNERLINK_CATEGORY, plDefinitions.get(0).getCategory());
	}
	
}
