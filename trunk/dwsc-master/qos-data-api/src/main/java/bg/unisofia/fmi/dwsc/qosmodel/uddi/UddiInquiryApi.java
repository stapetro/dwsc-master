package bg.unisofia.fmi.dwsc.qosmodel.uddi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.ClassUtil;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.config.UDDIClientContainer;
import org.apache.juddi.v3.client.transport.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uddi.api_v3.BindingDetail;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.FindBinding;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.ServiceInfo;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;

public class UddiInquiryApi {

	private Logger logger;

	private UDDIClerkManager uddiClerkManager;

	private UDDIInquiryPortType inquiryApi;

	public UddiInquiryApi() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		final String CONFIG_FILE = "conf/uddi.xml";
		startClerkManager(CONFIG_FILE);
		init();
	}

	public void release() {
		stopClerkManager();
	}

	public List<String> getServiceKeysByCategory(String categoryValue) {
		if (categoryValue == null || categoryValue.equals("")) {
			return null;
		}
		FindService findService = new FindService();
		CategoryBag categoryBag = newCategoryBag(categoryValue);
		findService.setCategoryBag(categoryBag);
		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("approximateMatch");
		findService.setFindQualifiers(findQualifiers);
		try {
			ServiceList serviceList = inquiryApi.findService(findService);
			ServiceInfos serviceInfos = serviceList.getServiceInfos();
			List<ServiceInfo> sInfoList = serviceInfos.getServiceInfo();
			List<String> serviceKeys = new ArrayList<String>();
			for (ServiceInfo serviceInfo : sInfoList) {
				serviceKeys.add(serviceInfo.getServiceKey());
			}
			return serviceKeys;
		} catch (DispositionReportFaultMessage e) {
			this.logger
					.error(String
							.format("Error occured during inquiry request for service category '%s'",
									categoryValue), e);
		} catch (RemoteException e) {
			this.logger
					.error(String
							.format("Communication error to UDDI during inquiry request for service category '%s'",
									categoryValue), e);
		}
		return null;
	}

	public List<String> getBindingAcessPointURL(String serviceKey,
			String categoryValue) {
		if ((serviceKey == null || serviceKey.equals(""))
				|| (categoryValue == null || categoryValue.equals(""))) {
			return null;
		}
		FindBinding findBinding = new FindBinding();
		findBinding.setServiceKey(serviceKey);

		CategoryBag categoryBag = newCategoryBag(categoryValue);
		findBinding.setCategoryBag(categoryBag);

		FindQualifiers findQualifiers = new FindQualifiers();
		findQualifiers.getFindQualifier().add("approximateMatch");
		findBinding.setFindQualifiers(findQualifiers);
		try {
			BindingDetail bDetail = inquiryApi.findBinding(findBinding);
			List<BindingTemplate> bTemplates = bDetail.getBindingTemplate();
			List<String> endpointUrls = new ArrayList<String>();
			for (BindingTemplate bTemplate : bTemplates) {
				endpointUrls.add(bTemplate.getAccessPoint().getValue());
			}
			return endpointUrls;
		} catch (DispositionReportFaultMessage e) {
			this.logger
					.error(String
							.format("Error occured during inquiry request for binding with service key '%s' in category '%s'",
									serviceKey, categoryValue), e);
		} catch (RemoteException e) {
			this.logger
					.error(String
							.format("Communication error occured during inquiry request for binding with service key '%s' in category '%s'",
									serviceKey, categoryValue), e);
		}
		return null;
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
			this.logger.error(
					"Error occured while starting clerk manager for DWSC", e);
		}
	}

	private void stopClerkManager() {
		try {
			this.uddiClerkManager.stop();
		} catch (ConfigurationException e) {
			this.logger.error(
					"Error occured while stopping clerk manager for DWSC", e);
		}
	}

	private void init() throws RuntimeException {
		try {
			UDDIClerkManager uddiClerkManager = UDDIClientContainer
					.getUDDIClerkManager("example-manager");
			this.logger.debug("clerk manager '{}' is retrieved",
					uddiClerkManager.getName());
			String clazz = uddiClerkManager.getClientConfig()
					.getUDDINode("default").getProxyTransport();
			Class<?> transportClass = ClassUtil.forName(clazz, Transport.class);
			if (transportClass != null) {
				Transport transport = (Transport) transportClass
						.getConstructor(String.class, String.class)
						.newInstance("example-manager", "default");
				inquiryApi = transport.getUDDIInquiryService();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
