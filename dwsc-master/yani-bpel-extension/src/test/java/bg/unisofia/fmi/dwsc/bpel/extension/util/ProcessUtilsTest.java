package bg.unisofia.fmi.dwsc.bpel.extension.util;

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
	
}
