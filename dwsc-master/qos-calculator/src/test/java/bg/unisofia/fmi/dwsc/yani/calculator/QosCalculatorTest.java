package bg.unisofia.fmi.dwsc.yani.calculator;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;
import datagenerator.MockGenerator;

public class QosCalculatorTest {

	@Test
	public void testGetQualityForAvailability() {
		List<RawQos> rawQosList = MockGenerator.getListOfRawQosData();
		QosCalculator qosCalc = new QosCalculator();

		IQualityAttribute qualityAttribute = qosCalc.getQuality(
				QualityAttributeEnum.AVAILABILITY, rawQosList);

		double availability = qualityAttribute.getQosAsDouble();

		Assert.assertEquals(1.0, availability);
	}

	@Test
	public void testGetQualityForExecutionTime() {
		List<RawQos> rawQosList = MockGenerator.getListOfRawQosData();
		QosCalculator qosCalc = new QosCalculator();

		IQualityAttribute qualityAttribute = qosCalc.getQuality(
				QualityAttributeEnum.EXECUTION_TIME, rawQosList);

		double executiontime = qualityAttribute.getQosAsDouble();

		Assert.assertEquals(1.0, executiontime);
	}

	@Test
	public void testGetQualityForThroughput() {
		List<RawQos> rawQosList = MockGenerator.getListOfRawQosData();
		QosCalculator qosCalc = new QosCalculator();

		IQualityAttribute qualityAttribute = qosCalc.getQuality(
				QualityAttributeEnum.THROUGHPUT, rawQosList);

		double throughput = qualityAttribute.getQosAsDouble();

		Assert.assertEquals(70.0, throughput);
	}

}
