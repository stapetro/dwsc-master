package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

public class CompositionAnalyser {

	public List<WebService> getBestCompositionByQuality(
			List<PartnerLinkDefinition> plDefinitionList,
			QualityProfile qualityProfile) {
		ServiceCompositionUtils serviceUtils = new ServiceCompositionUtils();

		WebService[][] serviceCombinations = serviceUtils
				.getServiceCompositionMatrix(plDefinitionList);

		List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedQos = serviceUtils
				.getServiceAggregatedQuality(serviceCombinations);
		List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedWorstQos = serviceUtils
				.getServiceAggregatedQualityForWorstValues(serviceCombinations);
		List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedBestQos = serviceUtils
				.getServiceAggregatedQualityForBestValues(serviceCombinations);
		
		

		int bestSelectionIndex = serviceUtils.getIndexOfBestComposition(
				aggregatedQos, aggregatedWorstQos, aggregatedBestQos, qualityProfile);

		List<WebService> bestServiceComposition = new LinkedList<WebService>();
		for (int i = 0; i < serviceCombinations[bestSelectionIndex].length; i++) {
			bestServiceComposition
					.add(serviceCombinations[bestSelectionIndex][i]);
		}

		return bestServiceComposition;
	}
}
