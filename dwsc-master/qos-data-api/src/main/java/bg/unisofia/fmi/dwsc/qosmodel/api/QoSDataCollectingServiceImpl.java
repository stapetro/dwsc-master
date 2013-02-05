package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.SaveQoSDataException;

@WebService(endpointInterface = "bg.unisofia.fmi.dwsc.qosmodel.api.QoSDataCollectingService")
public class QoSDataCollectingServiceImpl implements QoSDataCollectingService {

	@Override
	@WebMethod
	public boolean save(List<MessageQoSData> msgQosData)
			throws SaveQoSDataException {
		// TODO To be implemented
		return false;
	}

}
