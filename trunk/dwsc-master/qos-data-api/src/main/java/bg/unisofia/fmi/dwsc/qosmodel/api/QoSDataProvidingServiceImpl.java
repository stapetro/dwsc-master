package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataProvidingService")
public class QoSDataProvidingServiceImpl implements QoSDataProvidingService {

	public QoSDataProvidingServiceImpl() {

	}

	@Override
	@WebMethod
	public boolean publishService(String serviceKey, String serviceName)
			throws RegisterServiceException {
		throw new RegisterServiceException("Not implemented yet");
	}

}
