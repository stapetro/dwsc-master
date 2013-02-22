package bg.unisofia.fmi.dwsc.qosmodel.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationMessage;

public class OperationMessageDAO extends
		GenericAppManagedDAOImpl<OperationMessage> {

	public OperationMessageDAO() {
		super();
	}

	public OperationMessageDAO(EntityManager entityMgr) {
		super(entityMgr);
	}

	public OperationMessage save(OperationMessage opMessage) {
		return this.save(opMessage, true);
	}

	public Collection<OperationMessage> getOperationMessages(
			String operationInvocationCorrelationId) {
		if (operationInvocationCorrelationId == null
				|| operationInvocationCorrelationId.equals("")) {
			return null;
		}
		TypedQuery<OperationMessage> query = this.entityMgr
				.createQuery(
						"SELECT opMsg FROM OperationMessage opMsg WHERE opMsg.correlationId = :corrId",
						OperationMessage.class);
		query.setParameter("corrId", operationInvocationCorrelationId);
		return query.getResultList();
	}

	/**
	 * 
	 * @param operationMessage
	 *            Operation message entity to be specified.
	 * @param createTransaction
	 *            True - create transaction, false - otherwise.
	 * @return Saved operation message
	 */
	private OperationMessage save(OperationMessage operationMessage,
			boolean createTransaction) {
		if (operationMessage != null) {
			OperationMessage foundOperationMsg = find(operationMessage.getId());
			if (foundOperationMsg != null) {
				foundOperationMsg = update(operationMessage, createTransaction);
			} else {
				foundOperationMsg = create(operationMessage, createTransaction);
			}
			return foundOperationMsg;
		}
		return null;
	}
}
