package bg.unisofia.fmi.dwsc.yani.model.qos;

import junit.framework.Assert;

import org.junit.Test;

public class AvailabilityQualityAttributeTest {
	
	@Test
	public void testAddQosForExecutionTime() {
		AvailabilityQualityAttribute availability = new AvailabilityQualityAttribute(
				"0.5");
		String addAvailability = "0.5";

		String aggregatedAvailability = availability.addQoS(addAvailability);
		double aggregatedDoubleAvailability = Double.parseDouble(aggregatedAvailability);

		Assert.assertEquals(0.25, aggregatedDoubleAvailability);
	}

	@Test
	public void testCorrectType() {
		AvailabilityQualityAttribute availability = new AvailabilityQualityAttribute(
				"0.5");
		QualityAttributeEnum type = availability.getType();

		Assert.assertEquals(QualityAttributeEnum.AVAILABILITY, type);
	}
}
