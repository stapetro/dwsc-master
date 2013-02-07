package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.evaluator.service.ServiceSelector;
import bg.unisofia.fmi.dwsc.yani.interfaces.IModelEvaluator;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

public class ModelEvaluator implements IModelEvaluator {

	public void updateModelForComposition(
			List<PartnerLinkDefinition> plDefinitionList,
			QualityProfile qualityProfile) {
		if (plDefinitionList == null) {
			return;
		}

		ServiceSelector serviceSelector = new ServiceSelector();
		serviceSelector.updateServicesForPlDefinitions(plDefinitionList);

	}

}
