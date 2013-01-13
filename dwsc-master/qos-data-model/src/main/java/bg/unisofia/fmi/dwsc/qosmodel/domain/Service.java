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
@Table(name = "Service")
public class Service {

	@Id
	@SequenceGenerator(name = "SERVICE_SERVICEID_GENERATOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_SERVICEID_GENERATOR")
	private long id;
	@Basic
	private String name;
	@ManyToMany
	private Collection<Operation> operation;
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

	public Collection<Operation> getOperation() {
	    return operation;
	}

	public void setOperation(Collection<Operation> param) {
	    this.operation = param;
	}

}