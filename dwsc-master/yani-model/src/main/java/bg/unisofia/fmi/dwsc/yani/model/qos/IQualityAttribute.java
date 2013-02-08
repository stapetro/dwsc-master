package bg.unisofia.fmi.dwsc.yani.model.qos;

public interface IQualityAttribute {

	public String addQoS(String qos);

	public QualityAttributeEnum getType();

	public String getQos();
	
	public double getQosAsDouble();
	
	public boolean isUpperBound();

}
