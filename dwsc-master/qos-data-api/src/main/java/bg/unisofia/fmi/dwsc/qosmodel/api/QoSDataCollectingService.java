package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.SaveQoSDataException;

/**
 * Represents service for collecting QoS raw data from monitoring module.
 * 
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface QoSDataCollectingService {

	@WebMethod
	public boolean save(List<MessageQoSData> msgQosData) throws SaveQoSDataException;
}
