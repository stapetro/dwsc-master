package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "operation_message")
public class OperationMessage {

	/* FLOW constants are retrieved from org.apache.axis2.context.MessageContext */
	public final static int IN_FLOW = 1;
	public final static int IN_FAULT_FLOW = 3;

	public final static int OUT_FLOW = 2;
	public final static int OUT_FAULT_FLOW = 4;
	/* END FLOW Constants declaration */

	@Id
	@SequenceGenerator(name = "OPERATION_MSGS_ID_GENERATOR", sequenceName = "OP_MSG_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_MSGS_ID_GENERATOR")
	private long id;
	@ManyToMany(mappedBy = "operationMessage", cascade = CascadeType.PERSIST)
	// @JoinTable(name = "user_operation_invocation", joinColumns = {
	// @JoinColumn(name = "operationInvocation_ID", nullable = false) },
	// inverseJoinColumns = { @JoinColumn(name = "user_ID", nullable = false) })
	private Collection<User> user;
	@Basic
	private Timestamp processed;
	@Basic
	private long processedNanoTime;
	@Basic
	private long size;
	@Basic
	private int flow;
	@Basic
	@Column(nullable = false)
	private String correlationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> param) {
		this.user = param;
	}

	public void setProcessed(Timestamp param) {
		this.processed = param;
	}

	public Timestamp getProcessed() {
		return processed;
	}
	
	public long getProcessedNanoTime() {
		return processedNanoTime;
	}

	public void setProcessedNanoTime(long processedNanoTime) {
		this.processedNanoTime = processedNanoTime;
	}

	public void setSize(long param) {
		this.size = param;
	}

	public long getSize() {
		return size;
	}

	public void add(User user) {
		if (user != null) {
			if (this.user == null) {
				this.user = new ArrayList<User>();
			}
			this.user.add(user);
		}
	}

	public void setFlow(int param) {
		this.flow = param;
	}

	public int getFlow() {
		return flow;
	}

	public void setCorrelationId(String param) {
		this.correlationId = param;
	}

	public String getCorrelationId() {
		return correlationId;
	}

}