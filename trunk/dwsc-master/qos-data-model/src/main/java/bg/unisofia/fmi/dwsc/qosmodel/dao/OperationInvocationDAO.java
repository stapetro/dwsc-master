package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;

/**
 * Represents DAO for operationInvocation entities.
 * 
 */
public class OperationInvocationDAO extends GenericAppManagedDAOImpl<OperationInvocation> {

	public OperationInvocationDAO() {
		super();
	}

	public OperationInvocationDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public OperationInvocation save(OperationInvocation operationInvocation) {
		OperationInvocation foundOperationInvocation = this.save(operationInvocation, true);
		return foundOperationInvocation;
	}

	public Collection<OperationInvocation> save(Collection<OperationInvocation> operationInvocations) {
		if (operationInvocations != null && operationInvocations.size() > 0) {
			Collection<OperationInvocation> newOperationInvocationss = new ArrayList<>();
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (OperationInvocation operationInvocation : operationInvocations) {
				OperationInvocation newOperationInvocations = this.save(operationInvocation, false);
				if (newOperationInvocations != null) {
					newOperationInvocationss.add(newOperationInvocations);
				}
			}
			tx.commit();
			return newOperationInvocationss;
		}
		return null;
	}

	public void remove(Collection<OperationInvocation> operationInvocations) {
		if (operationInvocations != null && operationInvocations.size() > 0) {
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (OperationInvocation operationInvocation : operationInvocations) {
				remove(operationInvocation.getId(), false);
			}
			tx.commit();
		}
	}

	public Collection<OperationInvocation> getOperationInvocations() {
		TypedQuery<OperationInvocation> query = this.entityMgr.createQuery(
				"Select operationInvocation from OperationInvocations operationInvocation", OperationInvocation.class);
		return query.getResultList();
	}

	/**
	 * 
	 * @param operationInvocation
	 *            OperationInvocations entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved operationInvocation
	 */
	private OperationInvocation save(OperationInvocation operationInvocation, boolean createTransaction) {
		if (operationInvocation != null) {
			OperationInvocation foundOperationInvocations = find(operationInvocation.getId());
			if (foundOperationInvocations != null) {
				foundOperationInvocations = update(operationInvocation, createTransaction);
			} else {
				foundOperationInvocations = create(operationInvocation, createTransaction);
			}
			return foundOperationInvocations;
		}
		return null;
	}

}
