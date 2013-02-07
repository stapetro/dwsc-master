package bg.unisofia.fmi.dwsc.qosmodel.domain;

import java.util.ArrayList;
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
	private Collection<OperationMessage> operationMessage;
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

	public Collection<OperationMessage> getOperationMessage() {
		return operationMessage;
	}

	public void setOperationMessage(Collection<OperationMessage> param) {
		this.operationMessage = param;
	}
	
	public void add(OperationMessage opMsg) {
		if(opMsg != null) {
			if(this.operationMessage == null) {
				this.operationMessage = new ArrayList<>();
			}
			this.operationMessage.add(opMsg);
		}
	}

}