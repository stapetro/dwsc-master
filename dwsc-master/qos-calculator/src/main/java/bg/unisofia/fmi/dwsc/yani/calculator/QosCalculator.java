package bg.unisofia.fmi.dwsc.yani.calculator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;

public class QosCalculator {

	public IQualityAttribute getQuality(QualityAttributeEnum type,
			List<RawQos> rawQosList) {
		if (type == null || rawQosList == null) {
			return null;
		}

		IQualityAttribute qualityAttribute = null;

		switch (type) {
		case AVAILABILITY: {
			qualityAttribute = getAvailabilityQos(rawQosList);
			break;
		}
		case EXECUTION_TIME: {
			qualityAttribute = getExecutionTimeQos(rawQosList);
			break;
		}
		case THROUGHPUT: {
			qualityAttribute = getThroughputQos(rawQosList);
			break;
		}
		}

		return qualityAttribute;
	}

	private IQualityAttribute getAvailabilityQos(List<RawQos> rawQosList) {
		int successCount = 0;
		for (RawQos r : rawQosList) {
			if (r.isSuccessful() == true) {
				successCount++;
			}
		}

		int listSize = rawQosList.size();
		double availabilityRate = successCount / listSize;

		IQualityAttribute availability = new AvailabilityQualityAttribute(""
				+ availabilityRate);

		return availability;
	}

	private IQualityAttribute getExecutionTimeQos(List<RawQos> rawQosList) {
		long totalTime = 0;
		int count = 0;
		for (RawQos r : rawQosList) {
			boolean isSuccessful = r.isSuccessful();
			if (isSuccessful == false) {
				continue;
			}

			long startTime = r.getRequestReceived().getTime();
			long endTime = r.getRequestSent().getTime();

			totalTime += endTime - startTime;
			count++;
		}

		IQualityAttribute executionTimeAttribute = null;
		if (count == 0) {
			executionTimeAttribute = new ExecutionTimeQualityAttribute(""
					+ Double.MAX_VALUE);
		} else {
			double executionTime = 1.0 * totalTime / count;
			executionTime /= 1000.0;
			executionTimeAttribute = new ExecutionTimeQualityAttribute(""
					+ executionTime);
		}
		return executionTimeAttribute;
	}

	private IQualityAttribute getThroughputQos(List<RawQos> rawQosList) {
		double totalThroughput = 0;
		int count = 0;
		for (RawQos r : rawQosList) {
			boolean isSuccessful = r.isSuccessful();
			System.out.println(".... is successful-> " + isSuccessful);
			if (isSuccessful == false) {
				continue;
			}

			long startTime = r.getRequestReceived().getTime();
			long endTime = r.getRequestSent().getTime();

			long inputSize = r.getRequestMessageSize();
			long outputSize = r.getRequestResponseSize();

			long timeInSeconds = (endTime - startTime) / 1000;

			totalThroughput += (1.0 * (inputSize + outputSize)) / timeInSeconds;
			count++;
		}

		IQualityAttribute throughputQualityAttribute = null;
		if (count == 0) {
			throughputQualityAttribute = new ThroughputQualityAttribute("" + 0);
		} else {
			double averageThroughput = totalThroughput / count;
			throughputQualityAttribute = new ThroughputQualityAttribute(""
					+ averageThroughput);
		}

		return throughputQualityAttribute;
	}
}
