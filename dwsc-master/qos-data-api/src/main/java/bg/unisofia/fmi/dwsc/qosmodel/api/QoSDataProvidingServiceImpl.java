package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.handler.MessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationInvocationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationMessage;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataProvidingService")
public class QoSDataProvidingServiceImpl implements QoSDataProvidingService {

	private Logger logger;
	private ServiceDAO serviceDao;

	public QoSDataProvidingServiceImpl() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	private ServiceDAO getServiceDAO() {
		if (this.serviceDao == null) {
			this.serviceDao = new ServiceDAO();
		}
		return this.serviceDao;
	}

	private void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDao = serviceDAO;
	}

	@Override
	@WebMethod
	public boolean registerService(String serviceKey, String serviceName)
			throws RegisterServiceException {
		if ((serviceKey == null || serviceKey.equals(""))
				|| (serviceName == null || serviceName.equals(""))) {
			return false;
		}
		ServiceDAO serviceDAO = this.getServiceDAO();
		Service service = serviceDAO.getServiceByKey(serviceKey);
		if (service == null) {
			service = new Service();
			service.setServiceKey(serviceKey);
		}
		service.setName(serviceName);
		service = serviceDAO.save(service);
		try {
			if (service != null) {
				return true;
			} else {
				return false;
			}
		} finally {
			serviceDAO.destroy();
			setServiceDAO(null);
		}
	}

	@Override
	@WebMethod
	public List<ServiceQoSData> getServiceQoSDataByCategory(
			String serviceCategory) {
		// TODO Retrieve all service keys by category from UDDI.
		return null;
	}

	@Override
	@WebMethod
	public ServiceQoSData getServiceQoSData(String serviceKey) {
		if (serviceKey == null || serviceKey.equals("")) {
			return null;
		}
		ServiceQoSData serviceQosData = null;
		ServiceDAO serviceDao = this.getServiceDAO();
		Service foundService = serviceDao.getServiceByKey(serviceKey);
		if (foundService != null) {
			serviceQosData = new ServiceQoSData();
			serviceQosData.setKey(serviceKey);
			serviceQosData.setName(foundService.getName());
			// TODO set service category
			// serviceQosData.setCategory();
			Collection<Operation> serviceOps = foundService.getOperation();
			if (serviceOps != null) {
				OperationInvocationDAO opInvDao = new OperationInvocationDAO(
						serviceDao.getEntityMgr());
				Iterator<Operation> serviceOpsIterator = serviceOps.iterator();
				while (serviceOpsIterator.hasNext()) {
					Operation currOp = serviceOpsIterator.next();
					populateOperationData(serviceQosData, currOp,
							opInvDao);
				}
				opInvDao.destroy();
				opInvDao = null;
			}
		}
		serviceDao.destroy();
		this.setServiceDAO(null);
		return serviceQosData;
	}

	private void populateOperationData(
			ServiceQoSData serviceQosData, Operation operation,
			OperationInvocationDAO opInvDao) {
		Collection<OperationInvocation> currOpInvs = opInvDao
				.getOperationInvocationsLimited(operation.getId(), 50);
		if (currOpInvs != null) {
			OperationData opData = new OperationData();
			opData.setName(operation.getName());
			Iterator<OperationInvocation> opInvsIterator = currOpInvs
					.iterator();
			while (opInvsIterator.hasNext()) {
				OperationInvocation currOpInv = opInvsIterator.next();
				Collection<OperationMessage> opMsgs = currOpInv
						.getOperationMessage();
				if (opMsgs != null) {
					OperationInvocationData opInvDataItem = new OperationInvocationData();
					Iterator<OperationMessage> opMsgsIterator = opMsgs
							.iterator();
					while (opMsgsIterator.hasNext()) {
						OperationMessage opMsg = opMsgsIterator.next();
						populateOperationInvocation(opInvDataItem, opMsg);
					}
					if (opInvDataItem.getRequestReceived() != null
							&& opInvDataItem.getResponseSent() != null) {
						opInvDataItem.setSuccessful(true);
					} else {
						opInvDataItem.setSuccessful(false);
					}
					opData.addOperationInvocation(opInvDataItem);
				}
			}
			serviceQosData.addOperationData(opData);
		}
	}

	private void populateOperationInvocation(OperationInvocationData opInvData,
			OperationMessage opMessage) {
		int flow = opMessage.getFlow();
		long size = opMessage.getSize();
		Timestamp processed = opMessage.getProcessed();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(processed.getTime());
		switch (flow) {
		case OperationMessage.IN_FLOW:
		case OperationMessage.IN_FAULT_FLOW:
			opInvData.setRequestMsgSize(size);
			opInvData
					.setRequestReceived(calendar.getTime());
			break;
		case OperationMessage.OUT_FLOW:
		case OperationMessage.OUT_FAULT_FLOW:
			opInvData.setResponseMsgSize(size);
			opInvData.setResponseSent(calendar.getTime());
			break;
		}
	}
}
