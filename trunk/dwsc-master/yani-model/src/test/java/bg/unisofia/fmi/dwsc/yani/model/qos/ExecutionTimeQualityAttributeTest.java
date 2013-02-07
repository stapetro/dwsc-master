package bg.unisofia.fmi.dwsc.yani.model.qos;

import junit.framework.Assert;

import org.junit.Test;

public class ExecutionTimeQualityAttributeTest {

	@Test
	public void testAddQosForExecutionTime() {
		ExecutionTimeQualityAttribute execTime = new ExecutionTimeQualityAttribute("4.23");
		String addTime = "1.11";
		
		
		String aggregatedTime = execTime.addQoS(addTime);
		double aggregatedDoubleTime = Double.parseDouble(aggregatedTime);
				
		Assert.assertEquals(5.34, aggregatedDoubleTime);
	}
	
	@Test
	public void testCorrectType(){
		ExecutionTimeQualityAttribute execTime = new ExecutionTimeQualityAttribute("4.23");
		QualityAttributeEnum type = execTime.getType();
		
		Assert.assertEquals(QualityAttributeEnum.EXECUTION_TIME, type);
	}

}
