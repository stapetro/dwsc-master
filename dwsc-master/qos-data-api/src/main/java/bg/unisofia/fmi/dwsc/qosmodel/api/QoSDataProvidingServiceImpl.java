package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationInvocationDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.OperationMessageDAO;
import bg.unisofia.fmi.dwsc.qosmodel.dao.ServiceDAO;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Operation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationInvocation;
import bg.unisofia.fmi.dwsc.qosmodel.domain.OperationMessage;
import bg.unisofia.fmi.dwsc.qosmodel.domain.Service;
import bg.unisofia.fmi.dwsc.qosmodel.uddi.UddiInquiryApi;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataProvidingService")
public class QoSDataProvidingServiceImpl implements QoSDataProvidingService {

	private Logger logger;
	private UddiInquiryApi uddiApi;

	public QoSDataProvidingServiceImpl() {
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	@PostConstruct
	private void init() {
		this.uddiApi = new UddiInquiryApi();
	}

	@PreDestroy
	private void release() {
		this.uddiApi.release();
		this.uddiApi = null;
	}

	@Override
	@WebMethod
	public boolean registerService(String serviceKey, String serviceName)
			throws RegisterServiceException {
		if ((serviceKey == null || serviceKey.equals(""))
				|| (serviceName == null || serviceName.equals(""))) {
			return false;
		}
		ServiceDAO serviceDAO = new ServiceDAO();
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
		}
	}

	@Override
	@WebMethod
	public List<ServiceQoSData> getServiceQoSDataByCategory(
			String serviceCategory) {
		if (serviceCategory == null || serviceCategory.equals("")) {
			return null;
		}
		if (this.uddiApi == null) {
			this.logger.error("UDDI DWSC Inquiry API is not initialized.");
			return null;
		}
		List<String> serviceKeys = this.uddiApi
				.getServiceKeysByCategory(serviceCategory);
		if (serviceKeys != null) {
			List<ServiceQoSData> serviceQoSDataItems = new ArrayList<ServiceQoSData>();
			for (String serviceKey : serviceKeys) {
				List<String> serviceEndpointUrls = this.uddiApi
						.getBindingAcessPointURL(serviceKey, serviceCategory);
				if (serviceEndpointUrls != null
						&& serviceEndpointUrls.size() > 0) {
					String serviceEndpointUrl = serviceEndpointUrls.get(0);
					if (serviceEndpointUrl != null
							&& serviceEndpointUrl.equals("") == false) {
						ServiceQoSData serviceQoSData = createServiceQoSData(
								serviceKey, serviceCategory, serviceEndpointUrl);
						if (serviceQoSData != null) {
							serviceQoSDataItems.add(serviceQoSData);
						}
					}
				}
			}
			return serviceQoSDataItems;
		}
		return null;
	}

	@Override
	@WebMethod
	public ServiceQoSData getServiceQoSData(String serviceKey,
			String serviceCategory) {
		if ((serviceKey == null || serviceKey.equals(""))
				|| (serviceCategory == null || serviceCategory.equals(""))) {
			return null;
		}
		if (this.uddiApi == null) {
			this.logger.error("UDDI DWSC Inquiry API is not initialized.");
			return null;
		}
		List<String> serviceEndpointUrls = this.uddiApi
				.getBindingAcessPointURL(serviceKey, serviceCategory);
		if (serviceEndpointUrls != null && serviceEndpointUrls.size() > 0) {
			String serviceEndpointUrl = serviceEndpointUrls.get(0);
			if (serviceEndpointUrl != null
					&& serviceEndpointUrl.equals("") == false) {
				ServiceQoSData serviceQoSData = createServiceQoSData(
						serviceKey, serviceCategory, serviceEndpointUrl);
				if (serviceQoSData != null) {
					return serviceQoSData;
				}
			}
		}
		return null;
	}

	private ServiceQoSData createServiceQoSData(String serviceKey,
			String categoryName, String serviceEndpointUrl) {
		ServiceQoSData serviceQosData = null;
		ServiceDAO serviceDao = new ServiceDAO();
		EntityManager entityMgr = serviceDao.getEntityMgr();
		Service foundService = serviceDao.getServiceByKey(serviceKey);
		if (foundService != null) {
			entityMgr.refresh(foundService);
			serviceQosData = new ServiceQoSData();
			serviceQosData.setKey(serviceKey);
			serviceQosData.setName(foundService.getName());
			serviceQosData.setCategory(categoryName);
			serviceQosData.setEndPointUrl(serviceEndpointUrl);
			Collection<Operation> serviceOps = foundService.getOperation();
			if (serviceOps != null) {
				OperationInvocationDAO opInvDao = new OperationInvocationDAO(
						serviceDao.getEntityMgr());
				OperationMessageDAO opMsgDao = new OperationMessageDAO(
						serviceDao.getEntityMgr());
				for (Operation currOp : serviceOps) {
					populateOperationData(serviceQosData, currOp, opInvDao,
							opMsgDao);
				}
				opInvDao.destroy();
				opInvDao = null;
			}
		}
		serviceDao.destroy();
		return serviceQosData;
	}

	private void populateOperationData(ServiceQoSData serviceQosData,
			Operation operation, OperationInvocationDAO opInvDao,
			OperationMessageDAO opMsgDao) {
		Collection<OperationInvocation> currOpInvs = opInvDao
				.getOperationInvocationsLimited(operation.getId(), 50);
		if (currOpInvs != null) {
			OperationData opData = new OperationData();
			opData.setName(operation.getName());
			for (OperationInvocation currOpInv : currOpInvs) {
				Collection<OperationMessage> opMsgs = opMsgDao
						.getOperationMessages(currOpInv.getCorrelationId());
				if (opMsgs != null) {
					OperationInvocationData opInvDataItem = new OperationInvocationData();
					for (OperationMessage opMsg : opMsgs) {
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
			opInvData.setRequestReceived(calendar.getTime());
			opInvData.setRequestReceivedNanoTime(opMessage
					.getProcessedNanoTime());
			break;
		case OperationMessage.OUT_FLOW:
		case OperationMessage.OUT_FAULT_FLOW:
			opInvData.setResponseMsgSize(size);
			opInvData.setResponseSent(calendar.getTime());
			opInvData.setResponseSentNanoTime(opMessage.getProcessedNanoTime());
			break;
		}
	}
}
