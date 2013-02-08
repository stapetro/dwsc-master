package bg.unisofia.fmi.dwsc.yani.evaluator.service.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.utils.QualityAttributeFactory;

/**
 * This is a class providing mock functionality for performing unit tests over
 * the bpel-model-evaluator
 * 
 * IMPORTANT!!! Do not modify the content of this class!!! Most tests are based
 * on it!!!
 * 
 * @author krasimir.baylov
 * 
 */
public final class MockGenerator {

	public List<PartnerLinkDefinition> getPlDefinitionList() {
		List<PartnerLinkDefinition> plDefinitionList = new LinkedList<PartnerLinkDefinition>();
		PartnerLinkDefinition additionPl = getAdditionPartnerLink();
		PartnerLinkDefinition powPl = getPowPartnerLink();

		plDefinitionList.add(additionPl);
		plDefinitionList.add(powPl);

		return plDefinitionList;
	}

	public PartnerLinkDefinition getAdditionPartnerLink() {
		List<WebService> additionWebServices = getAdditionServices();
		PartnerLinkDefinition additionPl = new PartnerLinkDefinition(
				"PL_Addition", "addition", "addition_default_endpoint",
				additionWebServices);
		return additionPl;
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

		WebService wsStandard = new WebService("addition_endpoint_1", category,
				qosStandard, qosMinStandard, qosMaxStandard);

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
						QualityAttributeEnum.AVAILABILITY, "1"));

		qosMaxSlow.put(QualityAttributeEnum.THROUGHPUT, QualityAttributeFactory
				.getQualityAttribute(QualityAttributeEnum.THROUGHPUT, "40"));

		WebService wsSlow = new WebService("addition_endpoint_2", category,
				qosSlow, qosMinSlow, qosMaxSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}

	public PartnerLinkDefinition getPowPartnerLink() {
		List<WebService> additionWebServices = getPowServices();
		PartnerLinkDefinition pownPl = new PartnerLinkDefinition("PL_Pow",
				"pow", "pow_default_endpoint", additionWebServices);
		return pownPl;
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

		WebService wsStandard = new WebService("pow_endpoint_1", category,
				qosStandard, qosMinStandard, qosMaxStandard);

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

		WebService wsSlow = new WebService("pow_endpoint_2", category, qosSlow,
				qosMinSlow, qosMaxSlow);

		wsList.add(wsStandard);
		wsList.add(wsSlow);

		return wsList;
	}

	public QualityProfile getQualityProfile() {
		Map<QualityAttributeEnum, String> cost = new HashMap<QualityAttributeEnum, String>();
		cost.put(QualityAttributeEnum.AVAILABILITY, "0.0");
		cost.put(QualityAttributeEnum.EXECUTION_TIME, "0.0");
		cost.put(QualityAttributeEnum.THROUGHPUT, "0.0");

		Map<QualityAttributeEnum, IQualityAttribute> qosRequirements = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		qosRequirements.put(QualityAttributeEnum.AVAILABILITY,
				new AvailabilityQualityAttribute("0.1"));
		qosRequirements.put(QualityAttributeEnum.EXECUTION_TIME,
				new ExecutionTimeQualityAttribute("4.1"));
		qosRequirements.put(QualityAttributeEnum.THROUGHPUT,
				new ThroughputQualityAttribute("11"));

		QualityProfile profile = new QualityProfile(qosRequirements, cost);
		return profile;
	}
}
