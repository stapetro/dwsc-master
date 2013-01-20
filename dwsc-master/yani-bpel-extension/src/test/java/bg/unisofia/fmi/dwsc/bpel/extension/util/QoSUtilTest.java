package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ode.utils.DOMUtils;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import bg.unisofia.fmi.dwsc.yani.model.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

public class QoSUtilTest {

	private static final String QOS_ELEMENT_AS_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<yani:ComposeQoS odebpelc:lineno=\"63\" xmlns:odebpelc=\"urn:org.apache.ode.bpel.compiler\" xmlns:yani=\"http://yaniwscomposer.com/YaniWsComposer\">"
			+ "	<yani:QoS odebpelc:lineno=\"64\">"
			+ "		<yani:qualityAttribute name=\"executionTime\" odebpelc:lineno=\"65\" value=\"0.432\"/>"
			+ "		<yani:qualityAttribute name=\"availability\" odebpelc:lineno=\"66\" value=\"0.7\"/>"
			+ "		<yani:qualityAttribute name=\"throughput\" odebpelc:lineno=\"67\" value=\"33\"/>"
			+ "	</yani:QoS>" + "</yani:ComposeQoS>";

	@Test
	public void testQoSUtilsWithValidElementForCorrectNumberOfQualityAttributes()
			throws SAXException, IOException {
		QoSUtils qosUtil = new QoSUtils();
		Element qosElement = DOMUtils.stringToDOM(QOS_ELEMENT_AS_STRING);
		QualityProfile resultQualityProfile = qosUtil
				.getQualityProfile(qosElement);

		Map<QualityAttributeEnum, String> qualityRequirementsMap = resultQualityProfile
				.getQualityRequirements();
		int numberOfQualityAttributes = qualityRequirementsMap.size();

		Assert.assertEquals(3, numberOfQualityAttributes);
	}

	@Test
	public void testQoSUtilsWithValidElementForCorrectQoSValues()
			throws SAXException, IOException {
		QoSUtils qosUtil = new QoSUtils();
		Element qosElement = DOMUtils.stringToDOM(QOS_ELEMENT_AS_STRING);
		QualityProfile resultQualityProfile = qosUtil
				.getQualityProfile(qosElement);

		Map<QualityAttributeEnum, String> qualityRequirementsMap = resultQualityProfile
				.getQualityRequirements();

		String executionTimeValue = qualityRequirementsMap
				.get(QualityAttributeEnum.EXECUTION_TIME);
		String availabilityValue = qualityRequirementsMap
				.get(QualityAttributeEnum.AVAILABILITY);
		String throughputValue = qualityRequirementsMap
				.get(QualityAttributeEnum.THROUGHPUT);

		Assert.assertEquals("0.432", executionTimeValue);
		Assert.assertEquals("0.7", availabilityValue);
		Assert.assertEquals("33", throughputValue);
	}

	@Test
	public void testQoSUtilsWithNullElement(){
		QoSUtils qosUtil = new QoSUtils();
		QualityProfile qualityProfile = qosUtil.getQualityProfile(null);
		
		Assert.assertNull(qualityProfile);
	}
}
