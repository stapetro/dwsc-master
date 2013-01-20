package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

public class WebService {

	private String endPoint;
	private String category;
	private Map<QualityAttributeEnum, String> qos;

	public WebService(String endPoint, String category,
			Map<QualityAttributeEnum, String> qosMap) {
		this.endPoint = endPoint;
		this.category = category;
		this.qos = qosMap;
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
}
