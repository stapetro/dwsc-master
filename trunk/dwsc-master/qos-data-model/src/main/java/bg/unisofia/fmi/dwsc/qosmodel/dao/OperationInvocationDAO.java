package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;

/**
 * Represents DAO for operationInvocation entities.
 * 
 */
public class OperationInvocationDAO extends
		GenericAppManagedDAOImpl<OperationInvocation> {

	public OperationInvocationDAO() {
		super();
	}

	public OperationInvocationDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public OperationInvocation save(OperationInvocation operationInvocation) {
		OperationInvocation foundOperationInvocation = this.save(
				operationInvocation, true);
		return foundOperationInvocation;
	}

	public Collection<OperationInvocation> save(
			Collection<OperationInvocation> operationInvocations) {
		if (operationInvocations != null && operationInvocations.size() > 0) {
			Collection<OperationInvocation> newOperationInvocations = new ArrayList<OperationInvocation>();
			EntityTransaction tx = getTransaction();
			tx.begin();
			for (OperationInvocation operationInvocation : operationInvocations) {
				OperationInvocation newOperationInvocation = this.save(
						operationInvocation, false);
				if (newOperationInvocation != null) {
					newOperationInvocations.add(newOperationInvocation);
				}
			}
			tx.commit();
			return newOperationInvocations;
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
		TypedQuery<OperationInvocation> query = this.entityMgr
				.createQuery(
						"Select operationInvocation from OperationInvocation operationInvocation",
						OperationInvocation.class);
		return query.getResultList();
	}

	/**
	 * 
	 * @param size
	 *            Limit for result size to be specified. If size is non-positive
	 *            is specified, it fetches all entities.
	 * @return
	 */
	public Collection<OperationInvocation> getOperationInvocationsLimited(
			long operationId, int size) {
		TypedQuery<OperationInvocation> query = this.entityMgr
				.createQuery(
						"SELECT opInv FROM OperationInvocation opInv WHERE opInv.operation.id = :opId",
						OperationInvocation.class);
		query.setParameter("opId", operationId);
		if (size > 0) {
			query.setMaxResults(size);
		}
		return query.getResultList();
	}

	public OperationInvocation getOperationInvocation(
			String operationInvocationCorrelationId) {
		if (operationInvocationCorrelationId == null
				|| operationInvocationCorrelationId.equals("")) {
			return null;
		}
		TypedQuery<OperationInvocation> query = this.entityMgr
				.createQuery(
						"SELECT opInv FROM OperationInvocation opInv WHERE opInv.correlationId = :corrId",
						OperationInvocation.class);
		query.setParameter("corrId", operationInvocationCorrelationId);
		try {
			return query.getSingleResult();
		} catch (NoResultException noResEx) {
			this.logger.error(String.format(
					"Operation invocation with correlation id '%s' NOT found",
					operationInvocationCorrelationId), noResEx);
		} catch (Exception ex) {
			this.logger
					.error(String
							.format("Error occured when finding operation invocation with correlation id '%s'",
									operationInvocationCorrelationId), ex);
		}
		return null;
	}

	/**
	 * 
	 * @param operationInvocation
	 *            OperationInvocations entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved operationInvocation
	 */
	private OperationInvocation save(OperationInvocation operationInvocation,
			boolean createTransaction) {
		if (operationInvocation != null) {
			OperationInvocation foundOperationInvocations = find(operationInvocation
					.getId());
			if (foundOperationInvocations != null) {
				foundOperationInvocations = update(operationInvocation,
						createTransaction);
			} else {
				foundOperationInvocations = create(operationInvocation,
						createTransaction);
			}
			return foundOperationInvocations;
		}
		return null;
	}

}
