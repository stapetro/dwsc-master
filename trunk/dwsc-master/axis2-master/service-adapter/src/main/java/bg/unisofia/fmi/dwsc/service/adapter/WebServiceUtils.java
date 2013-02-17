package bg.unisofia.fmi.dwsc.service.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.calculator.BestQosCalculator;
import bg.unisofia.fmi.dwsc.yani.calculator.QosCalculator;
import bg.unisofia.fmi.dwsc.yani.calculator.WorstQosCalculator;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.RawQos;

public class WebServiceUtils {

	private List<RawQos> rawQosList;

	public WebServiceUtils(List<RawQos> rawQosList) {
		this.rawQosList = rawQosList;
		System.out.println("----> " + rawQosList.size());
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getQualityMap() {
		QualityAttributeEnum[] typeValues = QualityAttributeEnum.values();
		QosCalculator qosCalc = new QosCalculator();

		Map<QualityAttributeEnum, IQualityAttribute> qualityMap = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		for (QualityAttributeEnum type : typeValues) {
			IQualityAttribute qualityAttribute = qosCalc.getQuality(type,
					rawQosList);
			qualityMap.put(type, qualityAttribute);
		}

		return qualityMap;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getWorstQualityMap() {
		QualityAttributeEnum[] typeValues = QualityAttributeEnum.values();
		WorstQosCalculator qosCalc = new WorstQosCalculator();

		Map<QualityAttributeEnum, IQualityAttribute> worstQualityMap = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		for (QualityAttributeEnum type : typeValues) {
			IQualityAttribute qualityAttribute = qosCalc.getQuality(type,
					rawQosList);
			worstQualityMap.put(type, qualityAttribute);
		}

		return worstQualityMap;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getBestQualityMap() {
		QualityAttributeEnum[] typeValues = QualityAttributeEnum.values();
		BestQosCalculator qosCalc = new BestQosCalculator();

		Map<QualityAttributeEnum, IQualityAttribute> bestQualityMap = new HashMap<QualityAttributeEnum, IQualityAttribute>();
		for (QualityAttributeEnum type : typeValues) {
			IQualityAttribute qualityAttribute = qosCalc.getQuality(type,
					rawQosList);
			bestQualityMap.put(type, qualityAttribute);
		}

		return bestQualityMap;
	}

}
