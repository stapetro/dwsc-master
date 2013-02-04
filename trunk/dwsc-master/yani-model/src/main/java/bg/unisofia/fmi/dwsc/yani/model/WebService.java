package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

public class WebService {

	private String endPoint;
	private String category;
	private Map<QualityAttributeEnum, String> qos;
	private Map<QualityAttributeEnum, String> worstQos;

	public WebService(String endPoint, String category,
			Map<QualityAttributeEnum, String> qosMap,
			Map<QualityAttributeEnum, String> minimumQos) {
		this.endPoint = endPoint;
		this.category = category;
		this.qos = qosMap;
		this.worstQos = minimumQos;
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

	public Map<QualityAttributeEnum, String> getQos() {
		return qos;
	}

	public void setQos(Map<QualityAttributeEnum, String> qos) {
		this.qos = qos;
	}

	public Map<QualityAttributeEnum, String> getWorstQos() {
		return worstQos;
	}

	public void setWorstQos(Map<QualityAttributeEnum, String> minimumQos) {
		this.worstQos = minimumQos;
	}
}
