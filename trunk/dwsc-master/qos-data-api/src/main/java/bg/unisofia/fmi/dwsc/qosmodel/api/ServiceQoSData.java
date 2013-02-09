package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceQoSData")
public class ServiceQoSData {

	/**
	 * Stores service name.
	 */
	@XmlElement(name="serviceName")
	private String name;

	/**
	 * Stores service key from UDDI.
	 */
	@XmlElement(name="serviceKey")
	private String key;

	/**
	 * Stores service category name from UDDI.
	 */
	@XmlElement(name="serviceCategory")
	private String category;
	
	@XmlElement(name="serviceEndpointUrl")
	private String endPointUrl;

	@XmlElementWrapper(name="operations")
	@XmlElement(name="operation")
	private List<OperationData> operations;

	public ServiceQoSData() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getEndPointUrl() {
		return endPointUrl;
	}

	public void setEndPointUrl(String endPointUrl) {
		this.endPointUrl = endPointUrl;
	}

	public List<OperationData> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationData> operations) {
		this.operations = operations;
	}

	public void addOperationData(OperationData opData) {
		if(opData != null) {
			if(this.operations == null) {
				this.operations = new ArrayList<OperationData>();
			}
			this.operations.add(opData);
		}
	}
}
