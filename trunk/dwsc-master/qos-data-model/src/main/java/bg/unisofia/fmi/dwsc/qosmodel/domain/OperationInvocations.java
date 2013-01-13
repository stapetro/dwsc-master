package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "OPERATION_INVOCATIONS")
public class OperationInvocations {

	@Id
	@SequenceGenerator(name = "OPERATION_INVOCATIONS_ID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_INVOCATIONS_ID_GENERATOR")
	private long id;
	@Basic
	@Column(nullable = false)
	private boolean successful = false;
	@ManyToMany(mappedBy = "operationInvocations")
	private Collection<User> user;
	@Basic
	private Timestamp requestReceived;
	@Basic
	private Timestamp responseSent;
	@Basic
	private long reqSoapMsgSize;
	@Basic
	private long respSoapMsgSize;

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

}