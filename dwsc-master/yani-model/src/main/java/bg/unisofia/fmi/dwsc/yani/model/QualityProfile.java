package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

public class QualityProfile {
	private Map<QualityAttributeEnum, String> qualityRequirements;

	public QualityProfile(Map<QualityAttributeEnum, String> qualityRequirements) {
		this.qualityRequirements = qualityRequirements;
	}

	public Map<QualityAttributeEnum, String> getQualityRequirements() {
		return qualityRequirements;
	}

	public void setQualityRequirements(
			Map<QualityAttributeEnum, String> qualityRequirements) {
		this.qualityRequirements = qualityRequirements;
	}

}
