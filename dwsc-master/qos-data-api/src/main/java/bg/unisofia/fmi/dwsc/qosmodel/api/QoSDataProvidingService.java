package bg.unisofia.fmi.dwsc.qosmodel.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;

@WebService
@SOAPBinding(style = Style.RPC)
public interface QoSDataProvidingService {

	@WebMethod
	/**
	 * Publishes service description to QoS Repository
	 * @param serviceKey serviceKey from UDDI registry to be specified.
	 * @param serviceName Service name from UDDI registry to be specified.
	 * @return True - published successfully, false - otherwise.
	 */
	public boolean publishService(String serviceKey, String serviceName)
			throws RegisterServiceException;

	/**
	 * 
	 * @param serviceKey serviceKey from UDDI registry to be specified.
	 * @return
	 */
	// TODO Should return data structure with all data.
//	public String getServiceQoSData(String serviceKey);
}
