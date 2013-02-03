package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.PublishServiceException;

/**
 * Represents service for collecting QoS raw data from monitoring module.
 * 
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface QosDataCollectingService {

}
