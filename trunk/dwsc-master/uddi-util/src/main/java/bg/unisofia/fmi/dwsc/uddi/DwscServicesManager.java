package bg.unisofia.fmi.dwsc.uddi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.api_v3.Publisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.v3.client.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.SaveBinding;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class DwscServicesManager {

	private static final String PUBLISHER_NAME = "dwsc";

	private static final String PUBLISHER_DESC = "DWSC Publisher";

	private static final String BUSINESS_NAME = "DWSC Business";

	private String[] ADDITION_SERVICE_NAMES = { "AdditionServiceStandard",
			"AdditionServiceSlow", "AdditionServiceRandomAvailable" };

	private String[] ADDITION_SERVICE_ENDPOINTS = {
			"http://localhost:8080/axis2-services/services/AdditionServiceStandard.AdditionServiceStandardHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/AdditionServiceSlow.AdditionServiceSlowHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/AdditionServiceRandomAvailable.AdditionServiceRandomAvailableHttpSoap12Endpoint/" };

	private String[] POW_SERVICE_NAMES = { "PowServiceStandard",
			"PowServiceSlow", "PowServiceRandomAvailability" };

	private String[] POW_SERVICE_ENDPOINTS = {
			"http://localhost:8080/axis2-services/services/PowServiceStandard.PowServiceStandardHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/PowServiceSlow.PowServiceSlowHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/PowServiceRandomAvailability.PowServiceRandomAvailabilityHttpSoap12Endpoint/" };

	private String[] MULTIPLY_SERVICE_NAMES = { "MultiplicityServiceStandard",
			"MultiplicityServiceSlow", "MultiplicityServiceRandomAvailability" };

	private String[] MULTIPLY_SERVICE_ENDPOINTS = {
			"http://localhost:8080/axis2-services/services/MultiplicityServiceStandard.MultiplicityServiceStandardHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/MultiplicityServiceSlow.MultiplicityServiceSlowHttpSoap12Endpoint/",
			"http://localhost:8080/axis2-services/services/MultiplicityServiceRandomAvailability.MultiplicityServiceRandomAvailabilityHttpSoap12Endpoint/" };

	private enum ServiceCategory {

		ADDITION("addition"), POW("pow"), MULTIPLY("multiply");

		private final String categoryName;

		private ServiceCategory(String categoryName) {
			this.categoryName = categoryName;
		}

		public String toString() {
			return this.categoryName;
		}
	}

	private Logger logger;

	private UDDIClerkManager uddiClerkManager;

	/**
	 * Security set from UDDI API.
	 */
	private UDDISecurityPortType security;

	/**
	 * Proprietary jUDDI API set.
	 */
	private JUDDIApiPortType juddiApi;

	/**
	 * Publis API set.
	 */
	private UDDIPublicationPortType publish;

	private UDDIInquiryPortType inquiryApi;

	public DwscServicesManager() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		startClerkManager("META-INF/uddi.xml");
		init();
	}

	public void publish() {
		AuthToken rootAuthToken = getAuthToken("root", "");
		if (savePublisher(PUBLISHER_NAME, PUBLISHER_DESC, rootAuthToken)) {
			AuthToken dwscPubToken = getAuthToken(PUBLISHER_NAME, "");
			try {
				String businessKey = publishBusiness(dwscPubToken,
						BUSINESS_NAME);
				int index;
				for (index = 0; index < ADDITION_SERVICE_NAMES.length; index++) {
					String serviceCategory = ServiceCategory.ADDITION
							.toString();
					String serviceName = ADDITION_SERVICE_NAMES[index];
					String serviceEndpointUrl = ADDITION_SERVICE_ENDPOINTS[index];
					publishServiceDetails(dwscPubToken, businessKey,
							serviceCategory, serviceName, serviceEndpointUrl);
				}
				for (index = 0; index < POW_SERVICE_NAMES.length; index++) {
					String serviceCategory = ServiceCategory.POW.toString();
					String serviceName = POW_SERVICE_NAMES[index];
					String serviceEndpointUrl = POW_SERVICE_ENDPOINTS[index];
					publishServiceDetails(dwscPubToken, businessKey,
							serviceCategory, serviceName, serviceEndpointUrl);
				}
				for (index = 0; index < MULTIPLY_SERVICE_NAMES.length; index++) {
					String serviceCategory = ServiceCategory.MULTIPLY
							.toString();
					String serviceName = MULTIPLY_SERVICE_NAMES[index];
					String serviceEndpointUrl = MULTIPLY_SERVICE_ENDPOINTS[index];
					publishServiceDetails(dwscPubToken, businessKey,
							serviceCategory, serviceName, serviceEndpointUrl);
				}
			} catch (RemoteException e) {
				this.logger.error(
						"Error occured while publishing DWSC services to UDDI",
						e);
			}
		}
	}

	public void release() {
		stopClerkManager();
	}

	private void publishServiceDetails(AuthToken pubAuthToken,
			String businessKey, String serviceCategory, String serviceName,
			String serviceEndpointUrl) throws RemoteException {
		String serviceKey = publishService(pubAuthToken, businessKey,
				serviceName, serviceCategory);
		publishBinding(pubAuthToken, serviceKey, serviceEndpointUrl,
				serviceCategory);
	}

	private AuthToken getAuthToken(String userId, String password) {
		try {
			// Setting up the values to get an authentication token for the
			// 'root' user ('root' user
			// has admin privileges and can save other publishers).
			GetAuthToken getAuthTokenRoot = new GetAuthToken();
			if (userId != null && userId.equals("") == false) {
				getAuthTokenRoot.setUserID(userId);
				if (password == null) {
					password = "";
				}
				getAuthTokenRoot.setCred(password);
				// Making API call that retrieves the authentication token for
				// the 'root' user.
				AuthToken rootAuthToken = security
						.getAuthToken(getAuthTokenRoot);
				return rootAuthToken;
			}
		} catch (Exception exception) {
			this.logger.error("Auth token retrieval FAILED", exception);
		}
		return null;
	}

	private boolean savePublisher(String publisherId, String publisherDesc,
			AuthToken rootAuthToken) {
		// Creating a new publisher that we will use to publish our entities to.
		Publisher publisher = new Publisher();
		publisher.setAuthorizedName(publisherId);
		publisher.setPublisherName(publisherDesc);
		// Adding the publisher to the "save" structure, using the 'root' user
		// authentication info and
		// saving away.
		SavePublisher savePublisher = new SavePublisher();
		savePublisher.getPublisher().add(publisher);
		savePublisher.setAuthInfo(rootAuthToken.getAuthInfo());
		try {
			juddiApi.savePublisher(savePublisher);
			return true;
		} catch (DispositionReportFaultMessage e) {
			this.logger.error(
					String.format("Error saving publisher '%s'", publisherId),
					e);
		} catch (RemoteException e) {
			this.logger.error(
					String.format("Error saving publisher '%s'", publisherId),
					e);
		}
		return false;
	}

	/**
	 * 
	 * @param myPubAuthToken
	 * @return Business key
	 */
	private String publishBusiness(AuthToken myPubAuthToken,
			String businessEntityName) throws RemoteException {
		// Creating the parent business entity that will contain our service.
		BusinessEntity myBusEntity = new BusinessEntity();
		Name myBusName = new Name();
		myBusName.setValue(businessEntityName);
		myBusEntity.getName().add(myBusName);

		// Adding the business entity to the "save" structure, using our
		// publisher's authentication info
		// and saving away.
		SaveBusiness sb = new SaveBusiness();
		sb.getBusinessEntity().add(myBusEntity);
		sb.setAuthInfo(myPubAuthToken.getAuthInfo());
		BusinessDetail bd = publish.saveBusiness(sb);
		String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
		return myBusKey;
	}

	private String publishService(AuthToken myPubAuthToken, String myBusKey,
			String serviceName, String category) throws RemoteException {
		// Creating a service to save. Only adding the minimum data: the parent
		// business key retrieved
		// from saving the business above and a single name.
		BusinessService myService = new BusinessService();
		myService.setBusinessKey(myBusKey);
		Name myServName = new Name();
		myServName.setValue(serviceName);
		myService.getName().add(myServName);

		CategoryBag categoryBag = newCategoryBag(category);
		myService.setCategoryBag(categoryBag);

		// Add binding templates, etc...
		// Adding the service to the "save" structure, using our publisher's
		// authentication info and
		// saving away.
		SaveService ss = new SaveService();
		ss.getBusinessService().add(myService);
		ss.setAuthInfo(myPubAuthToken.getAuthInfo());
		ServiceDetail sd = publish.saveService(ss);
		String myServKey = sd.getBusinessService().get(0).getServiceKey();
		return myServKey;
	}

	private String publishBinding(AuthToken myPubAuthToken, String serviceKey,
			String endpointUrl, String category) throws RemoteException {
		BindingTemplate bindingTemplate = new BindingTemplate();
		bindingTemplate.setServiceKey(serviceKey);
		AccessPoint accessPoint = new AccessPoint();
		accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
		accessPoint.setValue(endpointUrl);
		bindingTemplate.setAccessPoint(accessPoint);

		CategoryBag categoryBag = newCategoryBag(category);
		bindingTemplate.setCategoryBag(categoryBag);

		SaveBinding saveBinding = new SaveBinding();
		saveBinding.setAuthInfo(myPubAuthToken.getAuthInfo());
		saveBinding.getBindingTemplate().add(bindingTemplate);

		BindingDetail bDetail = publish.saveBinding(saveBinding);
		String bindingKey = bDetail.getBindingTemplate().get(0).getBindingKey();
		return bindingKey;
	}
	
	public List<String> getServiceKeysByCategory(String categoryValue)
			throws RemoteException {
		FindService findService = new FindService();
		CategoryBag categoryBag = newCategoryBag(categoryValue);
		findService.setCategoryBag(categoryBag);
		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("approximateMatch");
		findService.setFindQualifiers(findQualifiers);
		ServiceList serviceList = inquiryApi.findService(findService);
		ServiceInfos serviceInfos = serviceList.getServiceInfos();
		List<ServiceInfo> sInfoList = serviceInfos.getServiceInfo();
		List<String> serviceKeys = new ArrayList<String>();
		for (ServiceInfo serviceInfo : sInfoList) {
			serviceKeys.add(serviceInfo.getServiceKey());
		}
		return serviceKeys;
	}

	public List<String> getBindingAcessPointURL(String serviceKey, String categoryValue)
			throws RemoteException {
		FindBinding findBinding = new FindBinding();
		findBinding.setServiceKey(serviceKey);
		
		CategoryBag categoryBag = newCategoryBag(categoryValue);
		findBinding.setCategoryBag(categoryBag);
		
		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("approximateMatch");
		findBinding.setFindQualifiers(findQualifiers);
		BindingDetail bDetail = inquiryApi.findBinding(findBinding);
		List<BindingTemplate> bTemplates = bDetail.getBindingTemplate();
		List<String> endpointUrls = new ArrayList<String>();
		for (BindingTemplate bTemplate : bTemplates) {
			endpointUrls.add(bTemplate.getAccessPoint().getValue());
		}
		return endpointUrls;
	}	

	private CategoryBag newCategoryBag(String categoryValue) {
		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyReference = new KeyedReference();
		keyReference.setTModelKey("uddi:uddi.org:categorization:types");
		keyReference.setKeyName("serviceCategory");
		keyReference.setKeyValue(categoryValue);
		categoryBag.getKeyedReference().add(keyReference);
		return categoryBag;
	}

	private void startClerkManager(String configFilePath) {
		try {
			this.uddiClerkManager = new UDDIClerkManager(configFilePath);
			uddiClerkManager.start();
		} catch (ConfigurationException e) {
			this.logger.error("Error occured while starting clerk manager for DWSC", e);
		}
	}

	private void stopClerkManager() {
		try {
			this.uddiClerkManager.stop();
		} catch (ConfigurationException e) {
			this.logger.error("Error occured while stopping clerk manager for DWSC", e);
		}
	}

	private void init() throws RuntimeException {
		try {
			UDDIClerkManager uddiClerkManager = UDDIClientContainer
					.getUDDIClerkManager("example-manager");
			System.out.println("clerk manager '" + uddiClerkManager.getName()
					+ "' is retrieved.");
			String clazz = uddiClerkManager.getClientConfig()
					.getUDDINode("default").getProxyTransport();
			Class<?> transportClass = ClassUtil.forName(clazz, Transport.class);
			if (transportClass != null) {
				Transport transport = (Transport) transportClass
						.getConstructor(String.class, String.class)
						.newInstance("example-manager", "default");
				security = transport.getUDDISecurityService();
				juddiApi = transport.getJUDDIApiService();
				publish = transport.getUDDIPublishService();
				inquiryApi = transport.getUDDIInquiryService();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
