package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;

public class QoSCalculatorTest {

	@Test
	public void testSum() {
		Map<QualityAttributeEnum, IQualityAttribute> qos1 = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qos1.put(QualityAttributeEnum.AVAILABILITY,
				new AvailabilityQualityAttribute("0.3"));
		qos1.put(QualityAttributeEnum.EXECUTION_TIME,
				new ExecutionTimeQualityAttribute("0.5"));
		qos1.put(QualityAttributeEnum.THROUGHPUT,
				new ThroughputQualityAttribute("33"));

		Map<QualityAttributeEnum, IQualityAttribute> qos2 = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qos2.put(QualityAttributeEnum.AVAILABILITY,
				new AvailabilityQualityAttribute("0.6"));
		qos2.put(QualityAttributeEnum.EXECUTION_TIME,
				new ExecutionTimeQualityAttribute("0.5"));
		qos2.put(QualityAttributeEnum.THROUGHPUT,
				new ThroughputQualityAttribute("22"));

		QoSCalculator qosCalc = new QoSCalculator();
		Map<QualityAttributeEnum, IQualityAttribute> aggregatedQos = qosCalc
				.aggregateQoS(qos1, qos2);

		String availabilityQos = aggregatedQos.get(
				QualityAttributeEnum.AVAILABILITY).getQos();
		String executionTimeQos = aggregatedQos.get(
				QualityAttributeEnum.EXECUTION_TIME).getQos();
		String throughputQos = aggregatedQos.get(
				QualityAttributeEnum.THROUGHPUT).getQos();

		double availabilityQosDouble = Double.parseDouble(availabilityQos);
		double executionTimeQosDouble = Double.parseDouble(executionTimeQos);
		double throughputQosDouble = Double.parseDouble(throughputQos);

		Assert.assertEquals(0.18, availabilityQosDouble);
		Assert.assertEquals(1.0, executionTimeQosDouble);
		Assert.assertEquals(22.0, throughputQosDouble);
	}

}
