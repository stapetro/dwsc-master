package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import net.sf.json.JSONObject;


@Entity
public class Operation {

	@Id
	@SequenceGenerator(name = "OPERATION_OPERATIONID_GENERATOR", sequenceName = "OPERATION_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_OPERATIONID_GENERATOR")
	private long id;
	@Basic
	@Column(nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Service service;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="operation", fetch=FetchType.LAZY)
	@JoinColumn(name = "operation_id", referencedColumnName = "id", nullable=false)
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

	public Service getService() {
	    return service;
	}

	public void setService(Service param) {
	    this.service = param;
	}

	public Collection<OperationInvocation> getOperationInvocation() {
	    return operationInvocation;
	}

	public void setOperationInvocation(Collection<OperationInvocation> param) {
	    this.operationInvocation = param;
	}
	
	public void addOperationInvocation(OperationInvocation opInvocation) {
		if(opInvocation != null) {
			if(this.operationInvocation == null) {
				this.operationInvocation = new ArrayList<OperationInvocation>();
			}
			this.operationInvocation.add(opInvocation);
		}
	}
	
	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("operationId", getId());
		json.put("name", getName());
		String output = json.toString();
		json = null;
		return output;
	}

}