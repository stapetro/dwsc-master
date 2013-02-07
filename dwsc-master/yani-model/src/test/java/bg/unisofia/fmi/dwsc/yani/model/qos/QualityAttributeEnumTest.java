package bg.unisofia.fmi.dwsc.yani.model.qos;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class QualityAttributeEnumTest {

	private static final String AVAILABILITY = "availability";
	private static final String EXECUTION_TIME = "executionTime";
	private static final String THROUGHPUT = "throughput";

	@Test
	public void testGetQualityAttributesEnumFromStringForAvailability() {
		QualityAttributeEnum availabilityQualityAttribute = QualityAttributeEnum
				.getQualityAttributeEnumFromString(AVAILABILITY);

		Assert.assertEquals(QualityAttributeEnum.AVAILABILITY,
				availabilityQualityAttribute);
	}

	@Test
	public void testGetQualityAttributesEnumFromStringForExecutionTime() {
		QualityAttributeEnum executionTimeQualityAttribute = QualityAttributeEnum
				.getQualityAttributeEnumFromString(EXECUTION_TIME);

		Assert.assertEquals(QualityAttributeEnum.EXECUTION_TIME,
				executionTimeQualityAttribute);
	}

	@Test
	public void testGetQualityAttributesEnumFromStringForThroughput() {
		QualityAttributeEnum throughputQualityAttribute = QualityAttributeEnum
				.getQualityAttributeEnumFromString(THROUGHPUT);

		Assert.assertEquals(QualityAttributeEnum.THROUGHPUT,
				throughputQualityAttribute);
	}

}
