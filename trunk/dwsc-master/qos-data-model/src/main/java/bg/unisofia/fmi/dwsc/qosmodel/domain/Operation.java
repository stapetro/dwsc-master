package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.util.Collection;

@Entity
public class Operation {

	@Id
	@SequenceGenerator(name = "OPERATION_OPERATIONID_GENERATOR", sequenceName = "OPERATION_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_OPERATIONID_GENERATOR")
	private long id;
	@Basic
	private String name;
	@ManyToMany(mappedBy = "operation")
	private Collection<Service> service;
	@OneToMany
	@JoinColumn(name = "Operation_id", referencedColumnName = "id")
	private Collection<OperationInvocations> operationInvocations;

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

	public Collection<Service> getService() {
		return service;
	}

	public void setService(Collection<Service> param) {
		this.service = param;
	}

	public Collection<OperationInvocations> getOperationInvocations() {
		return operationInvocations;
	}

	public void setOperationInvocations(Collection<OperationInvocations> param) {
		this.operationInvocations = param;
	}

}