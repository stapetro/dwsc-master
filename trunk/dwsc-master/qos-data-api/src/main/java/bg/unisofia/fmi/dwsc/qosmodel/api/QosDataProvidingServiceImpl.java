package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.PublishServiceException;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QosDataProvidingService")
public class QosDataProvidingServiceImpl implements QosDataProvidingService {

	public QosDataProvidingServiceImpl() {

	}

	@Override
	@WebMethod
	public boolean publishService(String serviceKey, String serviceName)
			throws PublishServiceException {
		throw new PublishServiceException("Not implemented yet");
	}

}
