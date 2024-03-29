package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.sql.Timestamp;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.SaveQoSDataException;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationInvocationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationMessageDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationMessage;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataCollectingService")
public class QoSDataCollectingServiceImpl implements QoSDataCollectingService {

	private Logger logger;

	public QoSDataCollectingServiceImpl() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	@WebMethod
	public boolean save(List<MessageQoSData> msgQosData)
			throws SaveQoSDataException {
		if (msgQosData != null) {
			ServiceDAO serviceDao = new ServiceDAO();
			for (MessageQoSData msgQos : msgQosData) {
				save(msgQos, serviceDao);
			}
			serviceDao.destroy();
			serviceDao = null;
			return true;
		}
		return false;
	}

	private void save(MessageQoSData msgQos, ServiceDAO serviceDao) {
		String serviceName = msgQos.getServiceName();
		Service service = serviceDao.getService(serviceName);
		if (service != null) {
			String corrId = msgQos.getOperationCorrelationId();
			OperationInvocationDAO opInvDao = new OperationInvocationDAO(
					serviceDao.getEntityMgr());
			OperationInvocation opInvocation = opInvDao
					.getOperationInvocation(corrId);
			if (opInvocation != null) {
				saveOperationMessageOnly(msgQos, serviceDao);
			} else {
				String operationName = msgQos.getOperationName();
				OperationDAO operationDao = new OperationDAO(
						serviceDao.getEntityMgr());
				Operation operation = operationDao.getOperation(
						service.getId(), operationName);
				if (operation == null) {
					operation = new Operation();
					operation.setName(operationName);
					operation.setService(service);
				}
				opInvocation = opInvDao
						.getOperationInvocation(corrId);
				if (opInvocation == null) {
					opInvocation = new OperationInvocation();
					opInvocation.setCorrelationId(corrId);
					opInvocation.setOperation(operation);
					operation.addOperationInvocation(opInvocation);
					OperationMessage opMessage = createOperationMessage(msgQos);
					opInvocation.addOperationMessage(opMessage);
					operationDao.save(operation);
					operationDao.destroy();
					operationDao = null;
				} else {
					saveOperationMessageOnly(msgQos, serviceDao);
				}
			}
			opInvDao.destroy();
			opInvDao = null;
		}
	}

	private void saveOperationMessageOnly(MessageQoSData msgQos,
			ServiceDAO serviceDao) {
		OperationMessage opMessage = createOperationMessage(msgQos);
		OperationMessageDAO opMsgDao = new OperationMessageDAO(
				serviceDao.getEntityMgr());
		opMsgDao.save(opMessage);
		opMsgDao.destroy();
		opMsgDao = null;
	}

	private OperationMessage createOperationMessage(MessageQoSData msgQos) {
		OperationMessage opMessage = new OperationMessage();
		opMessage.setCorrelationId(msgQos.getOperationCorrelationId());
		opMessage.setFlow(msgQos.getFlow());
		opMessage.setSize(msgQos.getSize());
		java.util.Date processedDate = msgQos.getProcessed();
		if (processedDate != null) {
			Timestamp timeStamp = new Timestamp(processedDate.getTime());
			opMessage.setProcessed(timeStamp);
		}
		opMessage.setProcessedNanoTime(msgQos.getProcessedNanoTime());
		return opMessage;
	}

}
