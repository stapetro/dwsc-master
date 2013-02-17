package bg.unisofia.fmi.dwsc.yani.model.qos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import bg.unisofia.fmi.dwsc.yani.model.qos.utils.QualityAttributeFactoryTest;

@RunWith(Suite.class)
@SuiteClasses({ AvailabilityQualityAttributeTest.class,
		ExecutionTimeQualityAttributeTest.class,
		QualityAttributeEnumTest.class, ThroughputQualityAttributeTest.class,
		QualityAttributeFactoryTest.class })
public class AllTests {

}
