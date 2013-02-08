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

	/**
	 * get a representation of the normalized quality for the provided QoS
	 * characterisstics
	 * 
	 * @param qos
	 * @param worstQoS
	 * @param profile
	 * @return
	 */
	public Map<QualityAttributeEnum, Double> getNormalizedQuality(
			Map<QualityAttributeEnum, IQualityAttribute> qos,
			Map<QualityAttributeEnum, IQualityAttribute> worstQoS,
			Map<QualityAttributeEnum, IQualityAttribute> bestQoS,
			QualityProfile profile) {
		Map<QualityAttributeEnum, Double> normalizedQualityMap = new HashMap<QualityAttributeEnum, Double>();

		Set<QualityAttributeEnum> qosTypeSet = qos.keySet();
		for (QualityAttributeEnum type : qosTypeSet) {
			String avgQos = qos.get(type).getQos();
			String worstQos = worstQoS.get(type).getQos();
			String bestQos = bestQoS.get(type).getQos();
			String requiredQos = profile.getQualityRequirements().get(type)
					.getQos();

			double avgQosDouble = Double.parseDouble(avgQos);
			double worstQosDouble = Double.parseDouble(worstQos);
			double bestQosDouble = Double.parseDouble(bestQos);
			double requiredQosDouble = Double.parseDouble(requiredQos);

			boolean isUpperBound = qos.get(type).isUpperBound();
			double normalizedQuality = 0;
			if (isUpperBound == true) {
				normalizedQuality = (avgQosDouble - requiredQosDouble)
						/ Math.abs((bestQosDouble - worstQosDouble));
			} else {
				normalizedQuality = (requiredQosDouble - avgQosDouble)
						/ Math.abs((bestQosDouble - worstQosDouble));
			}

			// TODO delete printing
			// String print = String
			// .format("avg %f ; best: %f ; worst: %f ; required : %f ==> norm %f",
			// avgQosDouble, bestQosDouble, worstQosDouble,
			// requiredQosDouble, normalizedQuality);
			// System.out.println(":::::::> " + print);

			normalizedQualityMap.put(type, normalizedQuality);
		}

		return normalizedQualityMap;
	}

	public double getQualityRating(
			Map<QualityAttributeEnum, Double> normalizedQuality,
			QualityProfile profile) {
		double rating = 0;

		Map<QualityAttributeEnum, String> costsMap = profile.getQualityCost();

		Set<QualityAttributeEnum> qosTypeSet = normalizedQuality.keySet();
		for (QualityAttributeEnum type : qosTypeSet) {

			double normValue = normalizedQuality.get(type);
			double cost = Double.parseDouble(costsMap.get(type));
			if (cost > 0) {
				rating += normValue * cost;
			} else {
				rating += normValue;
			}
		}

		int qualityElementsSize = normalizedQuality.size();
		rating = rating / qualityElementsSize;

		return rating;
	}
}
