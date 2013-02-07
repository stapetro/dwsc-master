package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

import bg.unisofia.fmi.dwsc.yani.model.qos.IQualityAttribute;
import bg.unisofia.fmi.dwsc.yani.model.qos.QualityAttributeEnum;

/**
 * Class representing quality profile for a BPEL Process
 * 
 * @author krasimir.baylov
 * 
 */
public class QualityProfile {
	private Map<QualityAttributeEnum, IQualityAttribute> qualityRequirements;
	private Map<QualityAttributeEnum, String> qualityCost;

	public QualityProfile(
			Map<QualityAttributeEnum, IQualityAttribute> qualityRequirements,
			Map<QualityAttributeEnum, String> qualityCost) {
		this.qualityRequirements = qualityRequirements;
		this.qualityCost = qualityCost;
	}

	public Map<QualityAttributeEnum, IQualityAttribute> getQualityRequirements() {
		return qualityRequirements;
	}

	public void setQualityRequirements(
			Map<QualityAttributeEnum, IQualityAttribute> qualityRequirements) {
		this.qualityRequirements = qualityRequirements;
	}

	public Map<QualityAttributeEnum, String> getQualityCost() {
		return qualityCost;
	}

	public void setQualityCost(Map<QualityAttributeEnum, String> qualityCost) {
		this.qualityCost = qualityCost;
	}

}
