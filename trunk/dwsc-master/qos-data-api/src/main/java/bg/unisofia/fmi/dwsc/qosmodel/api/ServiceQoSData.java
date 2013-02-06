package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "ServiceQoSData")
public class ServiceQoSData {

	/**
	 * Stores service name.
	 */
	private String name;

	/**
	 * Stores service key from UDDI.
	 */
	private String key;

	/**
	 * Stores service category name from UDDI.
	 */
	private String category;

	/**
	 * Groups all operation invocations to particular service operation.
	 * operation name -> operation invocation list. List because of JAXB
	 * capabilities.
	 */
	private Map<String, List<OperationInvocationData>> operationInvocationsMap;

	public ServiceQoSData() {

	}

	@XmlElement(name="serviceName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="serviceKey")
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@XmlElement(name="serviceCategory")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setOperationInvocationData(
			List<OperationInvocationData> opInvocationData) {
		if (opInvocationData != null) {
			Iterator<OperationInvocationData> opInvocationsIterator = opInvocationData
					.iterator();
			while (opInvocationsIterator.hasNext()) {
				this.addOperationInvocationData(opInvocationsIterator.next());
			}
		}
	}

	@XmlElementWrapper(name="operationInvocations")
	@XmlElement(name="operationInvocation")
	public List<OperationInvocationData> getOperationInvocationData() {
		if (this.operationInvocationsMap != null) {
			Collection<List<OperationInvocationData>> opInvocationCollections = this.operationInvocationsMap
					.values();
			Iterator<List<OperationInvocationData>> opInvocationCollectionsIterator = opInvocationCollections
					.iterator();
			List<OperationInvocationData> opInvocationsFlat = new ArrayList<>();
			while (opInvocationCollectionsIterator.hasNext()) {
				Collection<OperationInvocationData> currOpInvocations = opInvocationCollectionsIterator
						.next();
				opInvocationsFlat.addAll(currOpInvocations);
			}
			return opInvocationsFlat;
		}
		return null;
	}

	public void addOperationInvocationData(
			OperationInvocationData opInvocationData) {
		if (opInvocationData == null) {
			return;
		}
		String key = opInvocationData.getOperationName();
		if (key == null) {
			return;
		}
		if (this.operationInvocationsMap == null) {
			this.operationInvocationsMap = new HashMap<>();
		}
		List<OperationInvocationData> opInvications = null;
		if (this.operationInvocationsMap.containsKey(key)) {
			opInvications = this.operationInvocationsMap.get(opInvocationData);
			opInvications.add(opInvocationData);
		} else {
			opInvications = new ArrayList<>();
			opInvications.add(opInvocationData);
			this.operationInvocationsMap.put(key, opInvications);
		}
	}

	public Collection<OperationInvocationData> getOperationInvocationDataByOperation(
			String operationName) {
		if (operationName != null && this.operationInvocationsMap != null) {
			return this.operationInvocationsMap.get(operationName);
		}
		return null;
	}

	public Collection<List<OperationInvocationData>> getGroupedOperationInvocationData() {
		if (this.operationInvocationsMap != null) {
			return this.operationInvocationsMap.values();
		}
		return null;
	}
}
