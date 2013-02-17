package bg.unisofia.fmi.dwsc.yani.calculator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;

public class BestQosCalculator {

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
		IQualityAttribute availability = new AvailabilityQualityAttribute("1");

		return availability;
	}

	private IQualityAttribute getExecutionTimeQos(List<RawQos> rawQosList) {
		long bestTime = Long.MAX_VALUE;
		int count = 0;
		for (RawQos r : rawQosList) {
			boolean isSuccessful = r.isSuccessful();
			if (isSuccessful == false) {
				continue;
			}

			long startTime = r.getRequestReceived().getTime();
			long endTime = r.getRequestSent().getTime();

			long execTime = endTime - startTime;
			if (execTime < bestTime) {
				bestTime = execTime;
			}
			count++;
		}

		IQualityAttribute executionTimeAttribute = null;
		if (count == 0) {
			executionTimeAttribute = new ExecutionTimeQualityAttribute(""
					+ Double.MAX_VALUE);
		} else {
			bestTime /= 1000;
			executionTimeAttribute = new ExecutionTimeQualityAttribute(""
					+ bestTime);
		}
		return executionTimeAttribute;
	}

	private IQualityAttribute getThroughputQos(List<RawQos> rawQosList) {
		double bestThroughput = Double.MIN_VALUE;
		for (RawQos r : rawQosList) {
			boolean isSuccessful = r.isSuccessful();
			if (isSuccessful == false) {
				continue;
			}

			long startTime = r.getRequestReceived().getTime();
			long endTime = r.getRequestSent().getTime();

			long inputSize = r.getRequestMessageSize();
			long outputSize = r.getRequestResponseSize();

			long timeInSeconds = (endTime - startTime) / 1000;

			double throughput = (1.0 * (inputSize + outputSize))
					/ timeInSeconds;
			if (throughput > bestThroughput) {
				bestThroughput = throughput;
			}
		}

		IQualityAttribute throughputQualityAttribute = new ThroughputQualityAttribute(
				"" + bestThroughput);

		return throughputQualityAttribute;
	}

}
