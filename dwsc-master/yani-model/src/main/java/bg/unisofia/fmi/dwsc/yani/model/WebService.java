package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

public class WebService {

	private String endPoint;
	private String category;
	private Map<QualityAttributesEnum, String> qos;

	public WebService(String endPoint, String category,
			Map<QualityAttributesEnum, String> qosMap) {
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

	public Map<QualityAttributesEnum, String> getQos() {
		return qos;
	}

	public void setQos(Map<QualityAttributesEnum, String> qos) {
		this.qos = qos;
	}
}
