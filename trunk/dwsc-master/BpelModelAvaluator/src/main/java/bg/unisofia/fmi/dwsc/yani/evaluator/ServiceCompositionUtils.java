package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.evaluator.service.QoSCalculator;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.WebService;
import bg.unisofia.fmi.dwsc.yani.model.qos.AvailabilityQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.ExecutionTimeQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;
import bg.unisofia.fmi.dwsc.yani.model.qos.ThroughputQualityAttribute;

public class ServiceCompositionUtils {

	/**
	 * Generate a WebService matrix with all combinations that can be made based
	 * on the partner links
	 * 
	 * @param plDefinitionList
	 * @return matrix with all web service combinations
	 */
	public WebService[][] getServiceCompositionMatrix(
			List<PartnerLinkDefinition> plDefinitionList) {
		int[] matrixSize = getMatrixSize(plDefinitionList);
		int rowsCount = matrixSize[0];
		int columnsCount = matrixSize[1];

		int combinationsByNow = 1;
		int totalCombinations = rowsCount;
		WebService[][] webServiceMatrix = new WebService[rowsCount][columnsCount];

		for (int i = 0; i < plDefinitionList.size(); i++) {
			List<WebService> wsList = plDefinitionList.get(i)
					.getAssiciatedServiceList();

			combinationsByNow *= wsList.size();
			int repeatInterval = totalCombinations / combinationsByNow;

			updateMatrix(wsList, webServiceMatrix, i, repeatInterval);
		}

		return webServiceMatrix;
	}

	public void updateMatrix(List<WebService> wsList,
			WebService[][] webServiceMatrix, int columnNumber,
			int repeatInterval) {
		int wsIndex = -1;
		int wsCount = wsList.size();
		for (int i = 0; i < webServiceMatrix.length; i++) {
			if (i % repeatInterval == 0) {
				wsIndex++;
			}
			webServiceMatrix[i][columnNumber] = wsList.get(wsIndex % wsCount);
		}
	}

	/**
	 * Define how many combinations we can generate based on the partner links
	 * and their services.
	 * 
	 * @param plDefinitionList
	 * @return 2-element array. 1st defines how many rows we need for all
	 *         combinations, 2nd how many columns
	 */
	public int[] getMatrixSize(List<PartnerLinkDefinition> plDefinitionList) {
		int rowsCount = 1;
		for (int i = 0; i < plDefinitionList.size(); i++) {
			PartnerLinkDefinition plDef = plDefinitionList.get(i);
			int serviceCount = plDef.getAssiciatedServiceList().size();
			rowsCount *= serviceCount;
		}

		int columnCount = plDefinitionList.size();
		int[] size = { rowsCount, columnCount };

		return size;
	}

	public List<Map<QualityAttributeEnum, IQualityAttribute>> getServiceAggregatedQuality(
			WebService[][] webServiceMatrix) {
		List<Map<QualityAttributeEnum, IQualityAttribute>> qualityList = new LinkedList<Map<QualityAttributeEnum, IQualityAttribute>>();

		QoSCalculator qosCalc = new QoSCalculator();

		for (int row = 0; row < webServiceMatrix.length; row++) {

			Map<QualityAttributeEnum, IQualityAttribute> aggregatedQualityMap = new HashMap<QualityAttributeEnum, IQualityAttribute>();
			aggregatedQualityMap.put(QualityAttributeEnum.AVAILABILITY,
					new AvailabilityQualityAttribute("1"));
			aggregatedQualityMap.put(QualityAttributeEnum.EXECUTION_TIME,
					new ExecutionTimeQualityAttribute("0"));
			aggregatedQualityMap.put(QualityAttributeEnum.THROUGHPUT,
					new ThroughputQualityAttribute("" + Integer.MAX_VALUE));

			for (int column = 0; column < webServiceMatrix[row].length; column++) {
				WebService webService = webServiceMatrix[row][column];
				Map<QualityAttributeEnum, IQualityAttribute> serviceQos = webService
						.getQos();
				aggregatedQualityMap = qosCalc.aggregateQoS(
						aggregatedQualityMap, serviceQos);

			}
			qualityList.add(aggregatedQualityMap);
		}
		return qualityList;
	}

	public List<Map<QualityAttributeEnum, IQualityAttribute>> getServiceAggregatedQualityForWorstValues(
			WebService[][] webServiceMatrix) {
		List<Map<QualityAttributeEnum, IQualityAttribute>> qualityList = new LinkedList<Map<QualityAttributeEnum, IQualityAttribute>>();

		QoSCalculator qosCalc = new QoSCalculator();

		for (int row = 0; row < webServiceMatrix.length; row++) {

			Map<QualityAttributeEnum, IQualityAttribute> aggregatedQualityMap = new HashMap<QualityAttributeEnum, IQualityAttribute>();
			aggregatedQualityMap.put(QualityAttributeEnum.AVAILABILITY,
					new AvailabilityQualityAttribute("1"));
			aggregatedQualityMap.put(QualityAttributeEnum.EXECUTION_TIME,
					new ExecutionTimeQualityAttribute("0"));
			aggregatedQualityMap.put(QualityAttributeEnum.THROUGHPUT,
					new ThroughputQualityAttribute("" + Integer.MAX_VALUE));

			for (int column = 0; column < webServiceMatrix[row].length; column++) {
				WebService webService = webServiceMatrix[row][column];
				Map<QualityAttributeEnum, IQualityAttribute> serviceQos = webService
						.getWorstQos();
				aggregatedQualityMap = qosCalc.aggregateQoS(
						aggregatedQualityMap, serviceQos);

			}
			qualityList.add(aggregatedQualityMap);
		}
		return qualityList;
	}

	public int getIndexOfBestComposition(List<Map<QualityAttributeEnum, IQualityAttribute>> aggregatedQos,List<Map<QualityAttributeEnum, IQualityAttribute>> worstQos, QualityProfile profile){
		
		//TODO implement
		return 0;
	}}
