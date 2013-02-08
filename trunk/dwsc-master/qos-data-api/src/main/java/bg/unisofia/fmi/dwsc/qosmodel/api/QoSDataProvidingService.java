package bg.unisofia.fmi.dwsc.qosmodel.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import bg.unisofia.fmi.dwsc.qosmodel.api.exceptions.RegisterServiceException;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface QoSDataProvidingService {

	/**
	 * Publishes service description to QoS Repository. If service is already
	 * registered, updates it.
	 * 
	 * @param serviceKey
	 *            Service key from UDDI registry to be specified.
	 * @param serviceName
	 *            Service name from UDDI registry to be specified.
	 * @return True - published successfully, false - otherwise.
	 */
	@WebMethod
	public boolean registerService(String serviceKey, String serviceName)
			throws RegisterServiceException;

	/**
	 * Gets QoS data for services from a particular category.
	 * 
	 * @param serviceCategory
	 *            service category from UDDI registry to be specified.
	 * @return QoS data for all services related to the specified category or
	 *         NULL.
	 */
	@WebMethod
	public List<ServiceQoSData> getServiceQoSDataByCategory(
			String serviceCategory);

	/**
	 * Gets QoS data from service.
	 * 
	 * @param serviceKey
	 *            serviceKey from UDDI registry to be specified.
	 * @return QoS data for service or NULL.
	 */
	@WebMethod
	public ServiceQoSData getServiceQoSData(String serviceKey);
}
