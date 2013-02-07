package bg.unisofia.fmi.dwsc.yani.evaluator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.utils.QualityAttributeFactory;

/**
 * Class providing help functions for calculating QoS information for web
 * services
 * 
 * @author krasimir.baylov
 * 
 */
public class QoSCalculator {

	/**
	 * Get aggregated data of the QoS for the provided qualities
	 * 
	 * @param qos1
	 * @param qos2
	 * @return
	 */
	public Map<QualityAttributeEnum, IQualityAttribute> aggregateQoS(
			Map<QualityAttributeEnum, IQualityAttribute> qos1,
			Map<QualityAttributeEnum, IQualityAttribute> qos2) {
		Map<QualityAttributeEnum, IQualityAttribute> totalQos = new HashMap<QualityAttributeEnum, IQualityAttribute>();

		Set<QualityAttributeEnum> qosTypesSet = qos1.keySet();
		for (QualityAttributeEnum type : qosTypesSet) {
			IQualityAttribute attribute1 = qos1.get(type);
			IQualityAttribute attribute2 = qos2.get(type);

			IQualityAttribute aggregatedAttribute = QualityAttributeFactory
					.getQualityAttribute(type, attribute1.getQos());
			aggregatedAttribute.addQoS(attribute2.getQos());

			totalQos.put(type, aggregatedAttribute);
		}

		return totalQos;
	}

	public double getServiceQosRating(
			Map<QualityAttributeEnum, IQualityAttribute> qos, Map<QualityAttributeEnum, IQualityAttribute> worstQoS, QualityProfile profile) {
		
		//TODO implement
		double normalizedQoS = 0;
		
		return 0.0;
	}
}
