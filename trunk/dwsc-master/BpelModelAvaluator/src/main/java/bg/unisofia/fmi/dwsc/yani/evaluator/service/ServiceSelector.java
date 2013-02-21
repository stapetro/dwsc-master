package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.impl.AvalonLogger;

import bg.unisofia.fmi.dwsc.service.adapter.ServiceAdapter;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.utils.QualityAttributeFactory;

public class ServiceSelector {

	public void updateServicesForPlDefinitions(
			List<PartnerLinkDefinition> plDefinitionList) throws Exception {
		if (plDefinitionList == null) {
			return;
		}

//		List<String> categoriesList = getCategoriesList(plDefinitionList);
//		ServiceAdapter serviceAdapter = new ServiceAdapter();
//		Map<String, List<WebService>> availableWebServices = serviceAdapter.getServices(categoriesList);
		
		for (int i = 0; i < plDefinitionList.size(); i++) {
			PartnerLinkDefinition plDefinition = plDefinitionList.get(i);
			String category = plDefinition.getCategory();

			List<WebService> wsList = null;
			if (category.equals("addition")) {
				wsList = getAdditionServices();
			} else if (category.equals("pow")) {
				wsList = getPowServices();
			} else if (category.equals("multiplicity")){
				wsList = getMultiplicityServices();
			}
			
			//List<WebService> wsList = availableWebServices.get(category);
			
			plDefinition.setAssiciatedServiceList(wsList);
		}

		// TODO use mocked data for now
	}

	private List<String> getCategoriesList(
			List<PartnerLinkDefinition> plDefinitionList) {
		List<String> categoriesList = new LinkedList<String>();
		for (PartnerLinkDefinition plDef : plDefinitionList) {
			String category = plDef.getCategory();
			categoriesList.add(category);
		}

		return categoriesList;
	}

	private List<WebService> getAdditionServices() {
		List<WebService> wsList = new LinkedList<WebService>();
		String category = "addition";

		Map<QualityAttributeEnum, IQualityAttribute> qosStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.2"));

		qosStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "110"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.9"));

		qosMinStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.6"));

		qosMinStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "77"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.1"));

		qosMaxStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "200"));

		WebService wsStandard = new WebService(
				"http://localhost:8080/axis2-services/services/AdditionServiceStandard.AdditionServiceStandardHttpSoap12Endpoint/",
				category, qosStandard, qosMinStandard, qosMaxStandard);

		Map<QualityAttributeEnum, IQualityAttribute> qosSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.9"));

		qosSlow.put(QualityAttributeEnum.AVAILABILITY, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.AVAILABILITY, "0.9"));

		qosSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "20"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "2.3"));

		qosMinSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.4"));

		qosMinSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "5"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.2"));

		qosMaxSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1.0"));

		qosMaxSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "40"));

		WebService wsSlow = new WebService(
				"http://localhost:8080/axis2-services/services/AdditionServiceSlow.AdditionServiceSlowdHttpSoap12Endpoint/",
				category, qosSlow, qosMinSlow, qosMaxSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}

	private List<WebService> getPowServices() {
		List<WebService> wsList = new LinkedList<WebService>();
		String category = "pow";

		Map<QualityAttributeEnum, IQualityAttribute> qosStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.1"));

		qosStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "120"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.5"));

		qosMinStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.5"));

		qosMinStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "44"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.05"));

		qosMaxStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "190"));

		WebService wsStandard = new WebService(
				"http://localhost:8080/axis2-services/services/PowServiceStandard.PowServiceStandardHttpSoap12Endpoint/",
				category, qosStandard, qosMinStandard, qosMaxStandard);

		Map<QualityAttributeEnum, IQualityAttribute> qosSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.9"));

		qosSlow.put(QualityAttributeEnum.AVAILABILITY, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.AVAILABILITY, "0.9"));

		qosSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "40"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "1.5"));

		qosMinSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.3"));

		qosMinSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "10"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.5"));

		qosMaxSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "60"));

		WebService wsSlow = new WebService(
				"http://localhost:8080/axis2-services/services/PowServiceSlow.PowServiceSlowHttpSoap12Endpoint/",
				category, qosSlow, qosMinSlow, qosMaxSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}
	
	private List<WebService> getMultiplicityServices() {
		List<WebService> wsList = new LinkedList<WebService>();
		String category = "multiplicity";

		Map<QualityAttributeEnum, IQualityAttribute> qosStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.1"));

		qosStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "120"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.5"));

		qosMinStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.5"));

		qosMinStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "44"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxStandard = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxStandard.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.05"));

		qosMaxStandard.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxStandard.put(QualityAttributeEnum.THROUGHPUT,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.THROUGHPUT, "190"));

		WebService wsStandard = new WebService(
				"http://localhost:8080/axis2-services/services/MultiplicityServiceStandard.MultiplicityServiceStandardHttpSoap12Endpoint/",
				category, qosStandard, qosMinStandard, qosMaxStandard);

		Map<QualityAttributeEnum, IQualityAttribute> qosSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.9"));

		qosSlow.put(QualityAttributeEnum.AVAILABILITY, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.AVAILABILITY, "0.9"));

		qosSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "40"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMinSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMinSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "1.5"));

		qosMinSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "0.3"));

		qosMinSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "10"));

		Map<QualityAttributeEnum, IQualityAttribute> qosMaxSlow = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosMaxSlow.put(QualityAttributeEnum.EXECUTION_TIME,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.EXECUTION_TIME, "0.5"));

		qosMaxSlow.put(QualityAttributeEnum.AVAILABILITY,
				QualityAttributeFactory.getQualityAttribute(
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "60"));

		WebService wsSlow = new WebService(
				"http://localhost:8080/axis2-services/services/MultiplicityServiceSlow.MultiplicityServiceSlowHttpSoap12Endpoint/",
				category, qosSlow, qosMinSlow, qosMaxSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}
}
