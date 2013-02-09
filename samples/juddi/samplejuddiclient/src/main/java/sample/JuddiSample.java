package sample;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.SliderUI;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.api_v3.Publisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.v3.client.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.DeleteBinding;
import org.uddi.api_v3.DeleteBusiness;
import org.uddi.api_v3.DeleteService;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindBusiness;
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

public class JuddiSample {

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JuddiSample sample = new JuddiSample();
		// sample.startClerkManager("META-INF/uddi.xml");
		AuthToken rootAuthToken = sample.getAuthToken("root", "");
		String myPublisherName = "my-publisher";
		AuthToken myPubAuthToken = sample.getAuthToken(myPublisherName, "");
		try {
			/*
			 * sample.savePublisher(myPublisherName, "My publisher",
			 * rootAuthToken); sample.publishSample(myPubAuthToken);
			 */
			// sample.findAllBusinesses();
			// String serviceKey = sample.getServiceKey("My Stas Service");
			// sample.publishBinding(myPubAuthToken, serviceKey);
			// sample.getServicesByCategory("addition");
			// String serviceKey = "";
			// sample.getBindings(serviceKey);
			 /*sample.deleteService(myPubAuthToken,
			 "uddi:juddi.apache.org:dcb0237b-324b-4b4f-8fe5-6a244dc1573f");
			 sample.deleteService(myPubAuthToken,
			 "uddi:juddi.apache.org:82f3a8ab-7e5d-4a41-8807-76a4a716311f");
			 sample.deleteService(myPubAuthToken,
			 "uddi:juddi.apache.org:1046f04e-88d9-474e-9011-11a09dbe7932");
			 sample.deleteService(myPubAuthToken,
			 "uddi:juddi.apache.org:f220c630-4520-4d19-8033-b53d9dc2a9bf"); */
//			 sample.publishBinding(myPubAuthToken, "uddi:juddi.apache.org:82f3a8ab-7e5d-4a41-8807-76a4a716311f", "custom url new 4", "custom");
			// sample.deleteService(myPubAuthToken,
			// "uddi:juddi.apache.org:c9e17741-d363-40fe-9f69-911729755a4b");
			/*String busKey = "uddi:juddi.apache.org:85e582ef-d4fb-4184-a2a9-5c556ce282ba";
			String[] serviceNames = { "Sample Service 1", "Sample Service 2",
					"Custom Service 3", "Custom Service 4" };
			String[] categories = {"samples", "samples", "custom", "custom"};
			String[] endpointURLs = {"url1", "url2", "Custom url3", "Custom url4"};
			
			for(int index = 0; index < serviceNames.length; index++) {
				String serviceKey = sample.publishService(myPubAuthToken, busKey, serviceNames[index], categories[index]);	
				sample.publishBinding(myPubAuthToken, serviceKey, endpointURLs[index], categories[index]);
			}*/
			String category = "addition";
			List<String> serviceKeys = sample.getServicesByCategory(category);
			for(String sKey : serviceKeys) {
				List<String> urls = sample.getAcessPointURL(sKey, category);
				System.out.println(sKey + " -> " + Arrays.toString(urls.toArray()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JuddiSample() {
		startClerkManager("META-INF/uddi.xml");
		init();
	}

	public AuthToken getAuthToken(String userId, String password) {
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
				System.out.println("'" + userId + "' AUTHTOKEN = "
						+ rootAuthToken.getAuthInfo());
				return rootAuthToken;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Failed....");
		}
		return null;
	}

	public void savePublisher(String publisherId, String publisherDesc,
			AuthToken rootAuthToken) throws DispositionReportFaultMessage,
			RemoteException {
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
		juddiApi.savePublisher(savePublisher);
	}

	public void publishSample(AuthToken myPubAuthToken) throws RemoteException {
		String myBusKey = publishBusiness(myPubAuthToken, "My Stas Business");
		String serviceKey = publishService(myPubAuthToken, myBusKey,
				"My Stas Service", "stas Category");
		String bindingKey = publishBinding(myPubAuthToken, serviceKey, "url example", "stas Category");
	}

	/**
	 * 
	 * @param myPubAuthToken
	 * @return Business kye
	 */
	public String publishBusiness(AuthToken myPubAuthToken,
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
		System.out.println(String.format("Business '%s' key: %s ",
				businessEntityName, myBusKey));
		return myBusKey;
	}

	public String publishService(AuthToken myPubAuthToken, String myBusKey,
			String serviceName, String category) throws RemoteException {
		// Creating a service to save. Only adding the minimum data: the parent
		// business key retrieved
		// from saving the business above and a single name.
		BusinessService myService = new BusinessService();
		myService.setBusinessKey(myBusKey);
		Name myServName = new Name();
		myServName.setValue(serviceName);
		myService.getName().add(myServName);

		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyReference = new KeyedReference();
		keyReference.setTModelKey("uddi:uddi.org:categorization:types");
		keyReference.setKeyName("serviceCategory");
		keyReference.setKeyValue(category);
		categoryBag.getKeyedReference().add(keyReference);
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
		System.out.println("myService key:  " + myServKey);
		return myServKey;
	}

	public String publishBinding(AuthToken myPubAuthToken, String serviceKey,
			String endpointUrl, String category) throws RemoteException {
		BindingTemplate bindingTemplate = new BindingTemplate();
		bindingTemplate.setServiceKey(serviceKey);
		AccessPoint accessPoint = new AccessPoint();
		accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
		accessPoint.setValue(endpointUrl);
		bindingTemplate.setAccessPoint(accessPoint);
		
		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyReference = new KeyedReference();
		keyReference.setTModelKey("uddi:uddi.org:categorization:types");
		keyReference.setKeyName("serviceCategory");
		keyReference.setKeyValue(category);
		categoryBag.getKeyedReference().add(keyReference);
		bindingTemplate.setCategoryBag(categoryBag);

		SaveBinding saveBinding = new SaveBinding();
		saveBinding.setAuthInfo(myPubAuthToken.getAuthInfo());
		saveBinding.getBindingTemplate().add(bindingTemplate);

		BindingDetail bDetail = publish.saveBinding(saveBinding);
		String bindingKey = bDetail.getBindingTemplate().get(0).getBindingKey();
		System.out.println("Binding key : " + bindingKey);
		return bindingKey;
	}

	public void findAllBusinesses() {
		FindBusiness body = new FindBusiness();
		Name findName = new Name();
		findName.setValue("%");
		body.getName().add(findName);
		FindQualifiers f = new FindQualifiers();
		f.getFindQualifier().add("approximateMatch");
		// body.setAuthInfo(authToken.getAuthInfo());
		body.setFindQualifiers(f);
		try {
			BusinessList businessList = inquiryApi.findBusiness(body);

			BusinessInfos businessInfos = businessList.getBusinessInfos();
			StringBuilder output = new StringBuilder();
			List<BusinessInfo> businessInfosList = businessInfos
					.getBusinessInfo();
			for (BusinessInfo bInfo : businessInfosList) {
				String businessKey = bInfo.getBusinessKey();
				List<Name> names = bInfo.getName();
				List<Description> bDesc = bInfo.getDescription();
				Arrays.toString(bInfo.getName().toArray());
				output.append(String.format("\nBusiness key: '%s', names: ",
						businessKey));
				printNames(names, output);
				output.append("descs: ");
				printDescriptions(bDesc, output);
				ServiceInfos serviceInfos = bInfo.getServiceInfos();
				List<ServiceInfo> serviceInfosList = serviceInfos
						.getServiceInfo();
				for (ServiceInfo sInfo : serviceInfosList) {
					List<Name> sNames = sInfo.getName();
					String bKey = sInfo.getBusinessKey();
					String sKey = sInfo.getServiceKey();
					output.append(String.format(
							"\nService key: '%s', business key: '%s', names: ",
							sKey, bKey));
					printNames(sNames, output);
				}
			}
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getServiceKey(String serviceName) throws RemoteException {
		FindService findService = new FindService();
		Name name = new Name();
		name.setValue(serviceName);
		findService.getName().add(name);
		FindQualifiers findQualifier = new FindQualifiers();
		findQualifier.getFindQualifier().add("approximateMatch");
		findService.setFindQualifiers(findQualifier);
		ServiceList services = inquiryApi.findService(findService);
		String serviceKey = services.getServiceInfos().getServiceInfo().get(0)
				.getServiceKey();
		System.out.println(String.format("Service key for '%s' : %s",
				serviceName, serviceKey));
		return serviceKey;
	}

	public List<String> getServicesByCategory(String categoryName)
			throws RemoteException {
		FindService findService = new FindService();
		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyReference = new KeyedReference();
		keyReference.setTModelKey("uddi:uddi.org:categorization:types");
		keyReference.setKeyName("serviceCategory");
		keyReference.setKeyValue(categoryName);
		categoryBag.getKeyedReference().add(keyReference);
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

	public List<BindingTemplate> getBindings(String serviceKey)
			throws RemoteException {
		FindBinding findBinding = new FindBinding();
		findBinding.setServiceKey(serviceKey);
		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("approximateMatch");
		findBinding.setFindQualifiers(findQualifiers);
		BindingDetail bDetail = inquiryApi.findBinding(findBinding);
		List<BindingTemplate> bTemplates = bDetail.getBindingTemplate();
		return bTemplates;
	}

	public List<String> getAcessPointURL(String serviceKey, String categoryName)
			throws RemoteException {
		FindBinding findBinding = new FindBinding();
		findBinding.setServiceKey(serviceKey);
		
		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyReference = new KeyedReference();
		keyReference.setTModelKey("uddi:uddi.org:categorization:types");
		keyReference.setKeyName("serviceCategory");
		keyReference.setKeyValue(categoryName);
		categoryBag.getKeyedReference().add(keyReference);
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

	public void deleteBusiness(AuthToken myPubAuthToken, String businessKey)
			throws RemoteException {
		DeleteBusiness delBusiness = new DeleteBusiness();
		delBusiness.setAuthInfo(myPubAuthToken.getAuthInfo());
		delBusiness.getBusinessKey().add(businessKey);
		publish.deleteBusiness(delBusiness);
	}

	public void deleteService(AuthToken myPubAuthToken, String serviceKey)
			throws RemoteException {
		DeleteService delService = new DeleteService();
		delService.setAuthInfo(myPubAuthToken.getAuthInfo());
		delService.getServiceKey().add(serviceKey);
		publish.deleteService(delService);
	}

	public void deleteServiceBinding(AuthToken myPubAuthToken, String bindingKey)
			throws RemoteException {
		DeleteBinding delBinding = new DeleteBinding();
		delBinding.setAuthInfo(myPubAuthToken.getAuthInfo());
		delBinding.getBindingKey().add(bindingKey);
		publish.deleteBinding(delBinding);
	}

	public void startClerkManager(String configFilePath) {
		try {
			UDDIClerkManager uddiClerkManager = new UDDIClerkManager(
					configFilePath);
			uddiClerkManager.start();
		} catch (ConfigurationException e) {
			e.printStackTrace();
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
						.getConstructor(String.class, String.class).newInstance("example-manager", "default");
				security = transport.getUDDISecurityService();
				juddiApi = transport.getJUDDIApiService();
				publish = transport.getUDDIPublishService();
				inquiryApi = transport.getUDDIInquiryService();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void printNames(List<Name> names, StringBuilder output) {
		output.append("[");
		for (Name name : names) {
			output.append(String.format("'%s', ", name.getValue()));
		}
		output.append("]");
	}

	private static void printDescriptions(List<Description> descriptions,
			StringBuilder output) {
		output.append("[");
		for (Description desc : descriptions) {
			output.append(String.format("'%s', ", desc.getValue()));
		}
		output.append("]");
	}
}
