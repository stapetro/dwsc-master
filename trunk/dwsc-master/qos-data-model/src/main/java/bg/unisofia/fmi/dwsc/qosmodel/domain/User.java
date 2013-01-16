package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@SequenceGenerator(name = "USER_USERID_GENERATOR", sequenceName = "USER_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_USERID_GENERATOR")
	private long id;
	@Basic
	private String name;
	@ManyToMany
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

	public Collection<OperationInvocation> getOperationInvocation() {
		return operationInvocation;
	}

	public void setOperationInvocation(Collection<OperationInvocation> param) {
		this.operationInvocation = param;
	}

}