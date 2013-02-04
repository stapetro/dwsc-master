package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.WebService;

public class ServiceSelector {

	public void updateServicesForPlDefinitions(
			List<PartnerLinkDefinition> plDefinitionList) {
		if (plDefinitionList == null) {
			return;
		}

		for (int i = 0; i < plDefinitionList.size(); i++) {
			PartnerLinkDefinition plDefinition = plDefinitionList.get(i);
			String category = plDefinition.getCategory();

			List<WebService> wsList = null;
			if (category.equals("addition")) {
				wsList = getAdditionServices();
			} else if (category.equals("pow")) {
				wsList = getPowServices();
			}

			plDefinition.setAssiciatedServiceList(wsList);
		}

		// TODO use mocked data for now
	}

	private List<WebService> getAdditionServices() {
		List<WebService> wsList = new LinkedList<WebService>();
		String category = "addition";

		Map<QualityAttributeEnum, String> qosStandard = new HashMap<QualityAttributeEnum, String>();
		qosStandard.put(QualityAttributeEnum.EXECUTION_TIME, "0,1");
		qosStandard.put(QualityAttributeEnum.AVAILABILITY, "1");
		qosStandard.put(QualityAttributeEnum.THROUGHPUT, "120");
		Map<QualityAttributeEnum, String> qosMinStandard = new HashMap<QualityAttributeEnum, String>();
		qosMinStandard.put(QualityAttributeEnum.EXECUTION_TIME, "0,5");
		qosMinStandard.put(QualityAttributeEnum.AVAILABILITY, "0.5");
		qosMinStandard.put(QualityAttributeEnum.THROUGHPUT, "44");
		WebService wsStandard = new WebService(
				"http://localhost:8080/TestServices/services/AdditionServiceStandard.AdditionServiceStandardHttpSoap12Endpoint/",
				category, qosStandard, qosMinStandard);

		Map<QualityAttributeEnum, String> qosSlow = new HashMap<QualityAttributeEnum, String>();
		qosSlow.put(QualityAttributeEnum.EXECUTION_TIME, "0,9");
		qosSlow.put(QualityAttributeEnum.AVAILABILITY, "1");
		qosSlow.put(QualityAttributeEnum.THROUGHPUT, "20");
		Map<QualityAttributeEnum, String> qosMinSlow = new HashMap<QualityAttributeEnum, String>();
		qosMinSlow.put(QualityAttributeEnum.EXECUTION_TIME, "1,5");
		qosMinSlow.put(QualityAttributeEnum.AVAILABILITY, "0.5");
		qosMinSlow.put(QualityAttributeEnum.THROUGHPUT, "22");
		WebService wsSlow = new WebService(
				"http://localhost:8080/TestServices/services/AdditionServiceSlow.AdditionServiceSlowdHttpSoap12Endpoint/",
				category, qosSlow, qosMinSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}

	private List<WebService> getPowServices() {
		List<WebService> wsList = new LinkedList<WebService>();
		String category = "pow";

		Map<QualityAttributeEnum, String> qosStandard = new HashMap<QualityAttributeEnum, String>();
		qosStandard.put(QualityAttributeEnum.EXECUTION_TIME, "0,1");
		qosStandard.put(QualityAttributeEnum.AVAILABILITY, "1");
		qosStandard.put(QualityAttributeEnum.THROUGHPUT, "120");
		Map<QualityAttributeEnum, String> qosMinStandard = new HashMap<QualityAttributeEnum, String>();
		qosMinStandard.put(QualityAttributeEnum.EXECUTION_TIME, "0,5");
		qosMinStandard.put(QualityAttributeEnum.AVAILABILITY, "0.5");
		qosMinStandard.put(QualityAttributeEnum.THROUGHPUT, "44");
		WebService wsStandard = new WebService(
				"http://localhost:8080/TestServices/services/PowServiceStandard.PowServiceStandardHttpSoap12Endpoint/",
				category, qosStandard, qosMinStandard);

		Map<QualityAttributeEnum, String> qosSlow = new HashMap<QualityAttributeEnum, String>();
		qosSlow.put(QualityAttributeEnum.EXECUTION_TIME, "0,9");
		qosSlow.put(QualityAttributeEnum.AVAILABILITY, "1");
		qosSlow.put(QualityAttributeEnum.THROUGHPUT, "20");
		Map<QualityAttributeEnum, String> qosMinSlow = new HashMap<QualityAttributeEnum, String>();
		qosMinSlow.put(QualityAttributeEnum.EXECUTION_TIME, "1,5");
		qosMinSlow.put(QualityAttributeEnum.AVAILABILITY, "0.5");
		qosMinSlow.put(QualityAttributeEnum.THROUGHPUT, "22");
		WebService wsSlow = new WebService(
				"http://localhost:8080/TestServices/services/PowServiceSlow.PowServiceSlowHttpSoap12Endpoint/",
				category, qosSlow, qosMinSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}
}
