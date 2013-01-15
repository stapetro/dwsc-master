package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.util.Collection;

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

	public Collection<OperationInvocations> getOperationInvocations() {
		return operationInvocations;
	}

	public void setOperationInvocations(Collection<OperationInvocations> param) {
		this.operationInvocations = param;
	}

}