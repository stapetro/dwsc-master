package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

/**
 * Represents DAO for operation entities.
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
		Operation foundOperation = this.save(op, true);
		return foundOperation;
	}

	public Operation save(String operationName, Service service) {
		if (operationName == null || operationName.equals("")) {
			return null;
		}
		Operation operation = new Operation();
		operation.setName(operationName);
		if (service != null) {
			operation.setService(service);
		}
		return save(operation);
	}

	public Collection<Operation> save(Collection<Operation> operations) {
		if (operations != null && operations.size() > 0) {
			Collection<Operation> newOperations = new ArrayList<Operation>();
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (Operation op : operations) {
				Operation newOperation = this.save(op, false);
				if (newOperation != null) {
					newOperations.add(newOperation);
				}
			}
			tx.commit();
			return newOperations;
		}
		return null;
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

	public Operation getOperation(long serviceId, String opName) {
		TypedQuery<Operation> query = this.entityMgr
				.createQuery(
						"SELECT op FROM Operation op WHERE op.name = :name AND op.service.id = :serviceId",
						Operation.class);
		query.setParameter("name", opName);
		query.setParameter("serviceId", serviceId);
		try {
			return query.getSingleResult();
		} catch (NoResultException noResEx) {
			this.logger
			.warn(String.format("Operation with name '%s' NOT found",
					opName), noResEx);
		} catch (Exception ex) {
			this.logger
					.error(String.format("Error occured when finding operation with name '%s'",
							opName), ex);
		}
		return null;
	}

	/**
	 * 
	 * @param operation
	 *            Operation entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved operation
	 */
	private Operation save(Operation operation, boolean createTransaction) {
		if (operation != null) {
			Operation foundService = find(operation.getId());
			if (foundService != null) {
				foundService = update(operation, createTransaction);
			} else {
				foundService = create(operation, createTransaction);
			}
			return foundService;
		}
		return null;
	}

}
