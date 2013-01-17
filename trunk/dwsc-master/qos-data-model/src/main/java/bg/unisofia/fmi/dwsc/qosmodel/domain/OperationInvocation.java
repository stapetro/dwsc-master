package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;

@Entity
@Table(name = "operation_invocation")
public class OperationInvocation {

	@Id
	@SequenceGenerator(name = "OPERATION_INVOCATIONS_ID_GENERATOR", sequenceName = "OP_INV_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_INVOCATIONS_ID_GENERATOR")
	private long id;
	@Basic
	@Column(nullable = false)
	private boolean successful = false;
	@ManyToMany(mappedBy = "operationInvocation")
//	@JoinTable(name = "user_operation_invocation", joinColumns = { @JoinColumn(name = "operationInvocation_ID", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_ID", nullable = false) })
	private Collection<User> user;
	@Basic
	private Timestamp requestReceived;
	@Basic
	private Timestamp responseSent;
	@Basic
	private long reqSoapMsgSize;
	@Basic
	private long respSoapMsgSize;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Operation operation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSuccessful(boolean param) {
		this.successful = param;
	}

	public boolean getSuccessful() {
		return successful;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> param) {
		this.user = param;
	}

	public void setRequestReceived(Timestamp param) {
		this.requestReceived = param;
	}

	public Timestamp getRequestReceived() {
		return requestReceived;
	}

	public void setResponseSent(Timestamp param) {
		this.responseSent = param;
	}

	public Timestamp getResponseSent() {
		return responseSent;
	}

	public void setReqSoapMsgSize(long param) {
		this.reqSoapMsgSize = param;
	}

	public long getReqSoapMsgSize() {
		return reqSoapMsgSize;
	}

	public void setRespSoapMsgSize(long param) {
		this.respSoapMsgSize = param;
	}

	public long getRespSoapMsgSize() {
		return respSoapMsgSize;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation param) {
		this.operation = param;
	}

	public void add(User user) {
		if (user != null) {
			if (this.user == null) {
				this.user = new ArrayList<>();
			}
			this.user.add(user);
		}
	}

}