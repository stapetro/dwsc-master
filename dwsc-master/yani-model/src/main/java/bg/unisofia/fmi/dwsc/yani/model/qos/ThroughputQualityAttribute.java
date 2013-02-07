package bg.unisofia.fmi.dwsc.yani.model.qos;

public class ThroughputQualityAttribute extends QualityAttributeBase {

	public ThroughputQualityAttribute(String qos) {
		super(QualityAttributeEnum.THROUGHPUT, qos);
	}

	@Override
	public String addQoS(String qosArg) {
		double currentQoS = Double.parseDouble(super.qos);
		double argumentQos = Double.parseDouble(qosArg);

		double aggregatedQos = Math.min(currentQoS, argumentQos);
		String aggregatedQosString = String.format("%f", aggregatedQos);

		super.qos = aggregatedQosString;
		return aggregatedQosString;
	}
}
