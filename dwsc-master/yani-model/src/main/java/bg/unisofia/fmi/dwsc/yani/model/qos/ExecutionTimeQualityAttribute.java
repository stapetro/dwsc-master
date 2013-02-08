package bg.unisofia.fmi.dwsc.yani.model.qos;

public class ExecutionTimeQualityAttribute extends QualityAttributeBase {

	private static final boolean IS_UPPER_BOUND = false;

	public ExecutionTimeQualityAttribute(String qos) {
		super(QualityAttributeEnum.EXECUTION_TIME, qos);
	}

	@Override
	public String addQoS(String qosArg) {
		double currentQoS = Double.parseDouble(super.qos);
		double argumentQos = Double.parseDouble(qosArg);

		double aggregatedQos = currentQoS + argumentQos;
		String aggregatedQosString = String.format("%f", aggregatedQos);

		super.qos = aggregatedQosString;
		return aggregatedQosString;
	}

	public boolean isUpperBound() {
		return IS_UPPER_BOUND;
	}
}
