package bg.unisofia.fmi.dwsc.yani.model.qos;

import junit.framework.Assert;

import org.junit.Test;

public class ThroughputQualityAttributeTest {
	@Test
	public void testAddQosForExecutionTime() {
		ThroughputQualityAttribute throughput = new ThroughputQualityAttribute(
				"33");
		String addThroughput = "22";

		String aggregatedThroughput = throughput.addQoS(addThroughput);
		double aggregatedDoublethroughput = Double
				.parseDouble(aggregatedThroughput);

		Assert.assertEquals(22.0, aggregatedDoublethroughput);
	}

	@Test
	public void testCorrectType() {
		ThroughputQualityAttribute throughput = new ThroughputQualityAttribute(
				"33");
		QualityAttributeEnum type = throughput.getType();

		Assert.assertEquals(QualityAttributeEnum.THROUGHPUT, type);
	}
}
