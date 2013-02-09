package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "serviceOperation")
public class OperationData {
	
	@XmlElement(name="operationName")
	private String name;
	
	@XmlElementWrapper(name="operationInvocations")
	@XmlElement(name="operationInvocation")
	private List<OperationInvocationData> opInvocations;
	
	public OperationData() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OperationInvocationData> getOpInvocations() {
		return opInvocations;
	}

	public void setOpInvocations(List<OperationInvocationData> opInvocations) {
		this.opInvocations = opInvocations;
	}
	
	public void addOperationInvocation(OperationInvocationData opInvData) {
		if(opInvData != null) {
			if(this.opInvocations == null) {
				this.opInvocations = new ArrayList<OperationInvocationData>();
			}
			this.opInvocations.add(opInvData);
		}
	}

}
