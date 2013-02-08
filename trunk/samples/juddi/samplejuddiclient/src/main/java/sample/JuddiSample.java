package sample;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.Publisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.v3.client.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceInfos;
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
			sample.savePublisher(myPublisherName, "My publisher", rootAuthToken);
			sample.publishSample(myPubAuthToken);
			sample.findAllBusinesses();
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
		// Creating the parent business entity that will contain our service.
		BusinessEntity myBusEntity = new BusinessEntity();
		Name myBusName = new Name();
		myBusName.setValue("My Stas Business");
		myBusEntity.getName().add(myBusName);

		// Adding the business entity to the "save" structure, using our
		// publisher's authentication info
		// and saving away.
		SaveBusiness sb = new SaveBusiness();
		sb.getBusinessEntity().add(myBusEntity);
		sb.setAuthInfo(myPubAuthToken.getAuthInfo());
		BusinessDetail bd = publish.saveBusiness(sb);
		String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
		System.out.println("myBusiness key:  " + myBusKey);

		// Creating a service to save. Only adding the minimum data: the parent
		// business key retrieved
		// from saving the business above and a single name.
		BusinessService myService = new BusinessService();
		myService.setBusinessKey(myBusKey);
		Name myServName = new Name();
		myServName.setValue("My Stas Service");
		myService.getName().add(myServName);

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
		
		BindingTemplate bindingTemplate = new BindingTemplate();
		bindingTemplate.setServiceKey(myServKey);
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
						.getConstructor(String.class).newInstance("default");
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
