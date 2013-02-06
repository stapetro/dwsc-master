package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

/**
 * Class representing quality profile for a BPEL Process
 * 
 * @author krasimir.baylov
 * 
 */
public class QualityProfile {
	private Map<QualityAttributeEnum, String> qualityRequirements;
	private Map<QualityAttributeEnum, String> qualityCost;

	public QualityProfile(
			Map<QualityAttributeEnum, String> qualityRequirements,
			Map<QualityAttributeEnum, String> qualityCost) {
		this.qualityRequirements = qualityRequirements;
		this.qualityCost = qualityCost;
	}

	public Map<QualityAttributeEnum, String> getQualityRequirements() {
		return qualityRequirements;
	}

	public void setQualityRequirements(
			Map<QualityAttributeEnum, String> qualityRequirements) {
		this.qualityRequirements = qualityRequirements;
	}

	public Map<QualityAttributeEnum, String> getQualityCost() {
		return qualityCost;
	}

	public void setQualityCost(Map<QualityAttributeEnum, String> qualityCost) {
		this.qualityCost = qualityCost;
	}

}
