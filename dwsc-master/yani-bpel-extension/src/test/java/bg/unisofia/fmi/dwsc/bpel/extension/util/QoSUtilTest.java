package bg.unisofia.fmi.dwsc.bpel.extension.util;

import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.apache.ode.utils.DOMUtils;
import org.junit.Test;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class QoSUtilTest {

	private static final String QOS_ELEMENT_AS_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<yani:ComposeQoS odebpelc:lineno=\"63\" xmlns:odebpelc=\"urn:org.apache.ode.bpel.compiler\" xmlns:yani=\"http://yaniwscomposer.com/YaniWsComposer\">"
			+ "	<yani:QoS odebpelc:lineno=\"64\">"
			+ "		<yani:qualityAttribute name=\"executionTime\" odebpelc:lineno=\"65\" value=\"0.432\" cost=\"0.3\"/>"
			+ "		<yani:qualityAttribute name=\"availability\" odebpelc:lineno=\"66\" value=\"0.7\" cost=\"0.5\"/>"
			+ "		<yani:qualityAttribute name=\"throughput\" odebpelc:lineno=\"67\" value=\"33\" cost=\"0.2\"/>"
			+ "	</yani:QoS>" + "</yani:ComposeQoS>";
	
	private static final String QOS_ELEMENT_WITH_MISSING_COST_AS_STRING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<yani:ComposeQoS odebpelc:lineno=\"63\" xmlns:odebpelc=\"urn:org.apache.ode.bpel.compiler\" xmlns:yani=\"http://yaniwscomposer.com/YaniWsComposer\">"
			+ "	<yani:QoS odebpelc:lineno=\"64\">"
			+ "		<yani:qualityAttribute name=\"executionTime\" odebpelc:lineno=\"65\" value=\"0.432\" cost=\"0.3\"/>"
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

		Map<QualityAttributeEnum, IQualityAttribute> qualityRequirementsMap = resultQualityProfile
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

		Map<QualityAttributeEnum, IQualityAttribute> qualityRequirementsMap = resultQualityProfile
				.getQualityRequirements();

		String executionTimeValue = qualityRequirementsMap
				.get(QualityAttributeEnum.EXECUTION_TIME).getQos();
		String availabilityValue = qualityRequirementsMap
				.get(QualityAttributeEnum.AVAILABILITY).getQos();
		String throughputValue = qualityRequirementsMap
				.get(QualityAttributeEnum.THROUGHPUT).getQos();

		Assert.assertEquals("0.432", executionTimeValue);
		Assert.assertEquals("0.7", availabilityValue);
		Assert.assertEquals("33", throughputValue);
	}
	
	@Test
	public void testQoSUtilsWithValidElementForCorrectCostWithMissingCosts()
			throws SAXException, IOException {
		QoSUtils qosUtil = new QoSUtils();
		Element qosElement = DOMUtils.stringToDOM(QOS_ELEMENT_WITH_MISSING_COST_AS_STRING);
		QualityProfile resultQualityProfile = qosUtil
				.getQualityProfile(qosElement);

		Map<QualityAttributeEnum, String> qualityCostsMap = resultQualityProfile
				.getQualityCost();

		String executionTimeValue = qualityCostsMap
				.get(QualityAttributeEnum.EXECUTION_TIME);
		String availabilityValue = qualityCostsMap
				.get(QualityAttributeEnum.AVAILABILITY);
		String throughputValue = qualityCostsMap
				.get(QualityAttributeEnum.THROUGHPUT);

		Assert.assertEquals("0.3", executionTimeValue);
		Assert.assertEquals("0", availabilityValue);
		Assert.assertEquals("0", throughputValue);
	}
	
	@Test
	public void testQoSUtilsWithValidElementForCorrectQoSCost()
			throws SAXException, IOException {
		QoSUtils qosUtil = new QoSUtils();
		Element qosElement = DOMUtils.stringToDOM(QOS_ELEMENT_AS_STRING);
		QualityProfile resultQualityProfile = qosUtil
				.getQualityProfile(qosElement);

		Map<QualityAttributeEnum, String> qualityCostsMap = resultQualityProfile
				.getQualityCost();

		String executionTimeValue = qualityCostsMap
				.get(QualityAttributeEnum.EXECUTION_TIME);
		String availabilityValue = qualityCostsMap
				.get(QualityAttributeEnum.AVAILABILITY);
		String throughputValue = qualityCostsMap
				.get(QualityAttributeEnum.THROUGHPUT);

		Assert.assertEquals("0.3", executionTimeValue);
		Assert.assertEquals("0.5", availabilityValue);
		Assert.assertEquals("0.2", throughputValue);
	}

	@Test
	public void testQoSUtilsWithNullElement(){
		QoSUtils qosUtil = new QoSUtils();
		QualityProfile qualityProfile = qosUtil.getQualityProfile(null);
		
		Assert.assertNull(qualityProfile);
	}
}
