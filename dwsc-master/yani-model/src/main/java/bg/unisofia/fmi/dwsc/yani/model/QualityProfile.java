package bg.unisofia.fmi.dwsc.yani.model;

import java.util.Map;

public class QualityProfile {
	private Map<QualityAttributesEnum, String> qualityProfile;

	public QualityProfile(Map<QualityAttributesEnum, String> qualityProfile) {
		this.qualityProfile = qualityProfile;
	}

	public Map<QualityAttributesEnum, String> getQualityProfile() {
		return qualityProfile;
	}

	public void setQualityProfile(
			Map<QualityAttributesEnum, String> qualityProfile) {
		this.qualityProfile = qualityProfile;
	}

}
