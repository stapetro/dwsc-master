package bg.unisofia.fmi.dwsc.yani.model.qos.utils;

import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;

public class QualityAttributeFactory {

	public static IQualityAttribute getQualityAttribute(
			QualityAttributeEnum type, String value) {
		if (type == null) {
			return null;
		}

		IQualityAttribute qualityAttribute = null;
		switch (type) {
		case EXECUTION_TIME: {
			qualityAttribute = new ExecutionTimeQualityAttribute(value);
			break;
		}
		case AVAILABILITY: {
			qualityAttribute = new AvailabilityQualityAttribute(value);
			break;
		}
		case THROUGHPUT: {
			qualityAttribute = new ThroughputQualityAttribute(value);
			break;
		}
		}

		return qualityAttribute;
	}
}
