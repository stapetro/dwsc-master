package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class WebService {

	private String endPoint;
	private String category;
	private Map<QualityAttributeEnum, IQualityAttribute> qos;
	private Map<QualityAttributeEnum, IQualityAttribute> worstQos;
	private Map<QualityAttributeEnum, IQualityAttribute> bestQos;

	public WebService(String endPoint, String category,
			Map<QualityAttributeEnum, IQualityAttribute> qosMap,
			Map<QualityAttributeEnum, IQualityAttribute> minimumQos,
			Map<QualityAttributeEnum, IQualityAttribute> bestQos) {
		this.endPoint = endPoint;
		this.category = category;
		this.qos = qosMap;
		this.worstQos = minimumQos;
		this.bestQos = bestQos;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getQos() {
		return qos;
	}

	public void setQos(Map<QualityAttributeEnum, IQualityAttribute> qos) {
		this.qos = qos;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getWorstQos() {
		return worstQos;
	}

	public void setWorstQos(
			Map<QualityAttributeEnum, IQualityAttribute> minimumQos) {
		this.worstQos = minimumQos;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getBestQos() {
		return bestQos;
	}

	public void setBestQos(Map<QualityAttributeEnum, IQualityAttribute> bestQos) {
		this.bestQos = bestQos;
	}
}
