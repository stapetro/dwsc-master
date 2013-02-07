package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import java.util.Collection;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Service")
public class Service {

	@Id
	@SequenceGenerator(name = "SERVICE_SERVICEID_GENERATOR", sequenceName = "SERVICE_ENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_SERVICEID_GENERATOR")
	private long id;
	@Basic
	@Column(unique = true, nullable = false)
	private String name;
	@Basic
	@Column(unique = true, nullable = false)
	private String serviceKey;
	@OneToMany(mappedBy = "service", cascade=CascadeType.PERSIST)
	private Collection<Operation> operations;
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

	public void add(Operation op) {
		if (op != null) {
			if (this.operations == null) {
				this.operations = new ArrayList<>();
			}
			this.operations.add(op);
		}
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		json.put("serviceId", getId());
		json.put("serviceKey", getServiceKey());
		json.put("name", getName());
		String output = json.toString();
		json = null;
		return output;
	}

	public void setServiceKey(String param) {
		this.serviceKey = param;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public Collection<Operation> getOperation() {
	    return operations;
	}

	public void setOperation(Collection<Operation> param) {
	    this.operations = param;
	}

}