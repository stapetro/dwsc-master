package bg.unisofia.fmi.dwsc.yani.evaluator;

import java.util.List;

import bg.unisofia.fmi.dwsc.yani.evaluator.service.ServiceSelector;
import bg.unisofia.fmi.dwsc.yani.model.PartnerLinkDefinition;
import bg.unisofia.fmi.dwsc.yani.model.QualityProfile;

public class ModelEvaluator {

	private QualityProfile qualityProfile;

	public ModelEvaluator(QualityProfile qualityProfile) {
		this.qualityProfile = qualityProfile;
	}

	public void updateModelForAdaptation(
			List<PartnerLinkDefinition> plDefinitionList) {
		if (plDefinitionList == null) {
			return;
		}

		ServiceSelector serviceSelector = new ServiceSelector();
		serviceSelector.updateServicesForPlDefinitions(plDefinitionList);

	}

	public QualityProfile getQualityProfile() {
		return qualityProfile;
	}

	public void setQualityProfile(QualityProfile qualityProfile) {
		this.qualityProfile = qualityProfile;
	}

}
