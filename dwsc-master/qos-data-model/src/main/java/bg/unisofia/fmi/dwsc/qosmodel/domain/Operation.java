package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import javax.persistence.ManyToOne;

@Entity
public class Operation {

	@Id
	@SequenceGenerator(name = "OPERATION_OPERATIONID_GENERATOR", sequenceName = "OPERATION_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_OPERATIONID_GENERATOR")
	private long id;
	@Basic
	private String name;
	@OneToMany(mappedBy = "operation")
	private Collection<OperationInvocation> operationInvocation;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Service service;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String param) {
		this.name = param;
	}

	public String getName() {
		return name;
	}

	public Collection<OperationInvocation> getOperationInvocation() {
		return operationInvocation;
	}

	public void setOperationInvocation(Collection<OperationInvocation> param) {
		this.operationInvocation = param;
	}

	public void add(OperationInvocation operationInvocation) {
		if (operationInvocation != null) {
			if (this.operationInvocation == null) {
				this.operationInvocation = new ArrayList<>();
			}
			operationInvocation.setOperation(this);
			this.operationInvocation.add(operationInvocation);
		}
	}

	public Service getService() {
	    return service;
	}

	public void setService(Service param) {
	    this.service = param;
	}

}