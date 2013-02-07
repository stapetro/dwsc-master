package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.evaluator.service.ServiceSelector;
import bg.unisofia.fmi.dwsc.yani.interfaces.IModelEvaluator;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;
import bg.unisofia.fmi.dwsc.yani.model.WebService;

public class ModelEvaluator implements IModelEvaluator {

	public void updateModelForComposition(
			List<PartnerLinkDefinition> plDefinitionList,
			QualityProfile qualityProfile) {
		if (plDefinitionList == null) {
			return;
		}

		ServiceSelector serviceSelector = new ServiceSelector();
		serviceSelector.updateServicesForPlDefinitions(plDefinitionList);

		CompositionAnalyser analyser = new CompositionAnalyser();
		List<WebService> bestComposition = analyser
				.getBestCompositionByQuality(plDefinitionList, qualityProfile);
		updateBpelEndpointReferences(plDefinitionList, bestComposition);

	}

	private void updateBpelEndpointReferences(
			List<PartnerLinkDefinition> plDefinitionList,
			List<WebService> wsList) {
		if (plDefinitionList.size() != wsList.size()) {
			return;
		}

		for (int i = 0; i < plDefinitionList.size(); i++) {
			PartnerLinkDefinition plDefinition = plDefinitionList.get(i);
			String endpointAddress = wsList.get(i).getEndPoint();
			plDefinition.setEndpointReference(endpointAddress);
			
			//TODO delete the prints
			System.out.println(":::::::> " + endpointAddress);
		}
	}

}
