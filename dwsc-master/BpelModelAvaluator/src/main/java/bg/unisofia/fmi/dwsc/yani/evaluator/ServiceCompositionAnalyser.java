package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.WebService;

public class ServiceCompositionAnalyser {

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
}
