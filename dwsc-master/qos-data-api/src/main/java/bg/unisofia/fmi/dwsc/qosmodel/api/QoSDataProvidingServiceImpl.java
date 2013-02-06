package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataProvidingService")
public class QoSDataProvidingServiceImpl implements QoSDataProvidingService {

	public QoSDataProvidingServiceImpl() {

	}

	@Override
	@WebMethod
	public boolean registerService(String serviceKey, String serviceName)
			throws RegisterServiceException {
		throw new RegisterServiceException("Not implemented yet");
	}

	@Override
	@WebMethod
	public List<ServiceQoSData> getServiceQoSDataByCategory(
			String serviceCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ServiceQoSData getServiceQoSData(String serviceKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
