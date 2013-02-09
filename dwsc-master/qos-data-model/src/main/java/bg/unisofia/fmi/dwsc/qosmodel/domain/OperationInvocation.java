package bg.unisofia.fmi.dwsc.qosmodel.domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "operation_invocation")
public class OperationInvocation {

	@Id
	@SequenceGenerator(name = "OPERATION_INVOCATIONID_GENERATOR", sequenceName = "OPERATION_INVOCATION_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_INVOCATIONID_GENERATOR")
	private long id;
	@Basic
	@Column(unique = true, nullable = false)
	private String correlationId;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Operation operation;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "correlationId", referencedColumnName = "correlationId", nullable = false)
	private Collection<OperationMessage> operationMessage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<OperationMessage> getOperationMessage() {
		return operationMessage;
	}

	public void setOperationMessage(Collection<OperationMessage> param) {
		this.operationMessage = param;
	}

	public void setCorrelationId(String param) {
		this.correlationId = param;
	}

	public String getCorrelationId() {
		return correlationId;
	}
	
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public void addOperationMessage(OperationMessage opMessage) {
		if(opMessage != null) {
			if(this.operationMessage == null) {
				this.operationMessage = new ArrayList<OperationMessage>();		
			}
			this.operationMessage.add(opMessage);
		}
	}

}