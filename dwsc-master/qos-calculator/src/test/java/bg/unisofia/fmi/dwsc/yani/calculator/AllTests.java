package bg.unisofia.fmi.dwsc.yani.calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BestQosCalculatorTest.class, QosCalculatorTest.class,
		WorstQosCalculatorTest.class })
public class AllTests {

}
