package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Operation {

	@Id
	@SequenceGenerator(name = "OPERATION_OPERATIONID_GENERATOR", sequenceName = "OPERATION_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_OPERATIONID_GENERATOR")
	private long id;
	@Basic
	private String name;
	@ManyToMany(mappedBy = "operations")
	private Collection<Service> services;
	@OneToMany
	@JoinColumn(name = "OPERATION_ID", referencedColumnName = "id")
	private Collection<OperationInvocation> operationInvocation;

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
		return services;
	}

	public void setService(Collection<Service> param) {
		this.services = param;
	}

	public Collection<OperationInvocation> getOperationInvocation() {
		return operationInvocation;
	}

	public void setOperationInvocation(Collection<OperationInvocation> param) {
		this.operationInvocation = param;
	}
	
	public void add(Service srv) {
		if(srv != null) {
			if(this.services == null) {
				this.services = new ArrayList<>();
			}
			this.services.add(srv);
		}
	}

}