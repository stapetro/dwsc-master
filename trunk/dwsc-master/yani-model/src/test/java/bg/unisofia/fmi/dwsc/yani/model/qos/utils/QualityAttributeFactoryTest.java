package bg.unisofia.fmi.dwsc.yani.model.qos.utils;

import junit.framework.Assert;

import org.junit.Test;

import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class QualityAttributeFactoryTest {

	@Test
	public void testFactoryWithExecutionTimeAttribute() {
		IQualityAttribute qualityAttribute = QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.EXECUTION_TIME,
						"0.33");

		QualityAttributeEnum type = qualityAttribute.getType();
		Assert.assertEquals(QualityAttributeEnum.EXECUTION_TIME, type);
	}

	@Test
	public void testFactoryWithAvailabilityAttribute() {
		IQualityAttribute qualityAttribute = QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.AVAILABILITY, "0.33");

		QualityAttributeEnum type = qualityAttribute.getType();
		Assert.assertEquals(QualityAttributeEnum.AVAILABILITY, type);
	}

	@Test
	public void testFactoryWithThroughputAttribute() {
		IQualityAttribute qualityAttribute = QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "0.33");

		QualityAttributeEnum type = qualityAttribute.getType();
		Assert.assertEquals(QualityAttributeEnum.THROUGHPUT, type);
	}
}
