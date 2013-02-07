package bg.unisofia.fmi.dwsc.qosmodel.dao;

import javax.persistence.EntityManager;

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
			OperationMessage foundOperationMsg = find(operationMessage
					.getId());
			if (foundOperationMsg != null) {
				foundOperationMsg = update(operationMessage,
						createTransaction);
			} else {
				foundOperationMsg = create(operationMessage,
						createTransaction);
			}
			return foundOperationMsg;
		}
		return null;
	}
}
