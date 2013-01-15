package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

/**
 * Represents DAO for service entities.
 * 
 */
public class OperationDAO extends GenericAppManagedDAOImpl<Operation> {

	public OperationDAO() {
		super();
	}
	
	public OperationDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public Operation save(Operation op) {
		Operation foundOperation = find(op.getId());
		if (foundOperation != null) {
			foundOperation = update(op);
		} else {
			foundOperation = create(op);
		}
		return foundOperation;
	}
	
	public Operation save(String operationName, Service service) {
		if(operationName == null || operationName.equals("")) {
			return null;
		}
		Operation operation = new Operation();
		operation.setName(operationName);
		if(service != null) {
			Collection<Service> services = new ArrayList<>();
			services.add(service);
			operation.setService(services);
		}
		return save(operation);
	}

	public void remove(Collection<Operation> ops) {
		if (ops != null && ops.size() > 0) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (Operation op : ops) {
				remove(op.getId(), false);
			}
			tx.commit();
		}
	}

	public Collection<Operation> getOperations() {
		TypedQuery<Operation> query = this.entityMgr.createQuery(
				"Select op from Operation op", Operation.class);
		return query.getResultList();
	}

}
