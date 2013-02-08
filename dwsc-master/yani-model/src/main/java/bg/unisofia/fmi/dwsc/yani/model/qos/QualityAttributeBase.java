package bg.unisofia.fmi.dwsc.yani.model.qos;

public abstract class QualityAttributeBase implements IQualityAttribute {

	protected QualityAttributeEnum type;
	protected String qos;

	protected QualityAttributeBase(QualityAttributeEnum type, String qos) {
		this.type = type;
		this.qos = qos;
	}

	public QualityAttributeEnum getType() {
		return type;
	}

	public String getQos() {
		return qos;
	}

	public void setQos(String qos) {
		this.qos = qos;
	}

	public double getQosAsDouble() {
		return Double.parseDouble(this.qos);
	}

	@Override
	public String toString() {
		String result = String.format("{%s -> %s}", type.getName(), qos);
		return result;
	}
}
