package bg.unisofia.fmi.dwsc.yani.model.qos;

public enum QualityAttributeEnum {

	EXECUTION_TIME("executionTime"), AVAILABILITY("availability"), THROUGHPUT(
			"throughput");

	private String name;

	private QualityAttributeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static QualityAttributeEnum getQualityAttributeEnumFromString(
			String attributeName) {
		if (attributeName == null) {
			return null;
		}

		QualityAttributeEnum qualitiesArray[] = QualityAttributeEnum.values();
		for (QualityAttributeEnum qualityEnum : qualitiesArray) {
			String qualityName = qualityEnum.getName();
			if (qualityName.equals(attributeName) == true) {
				return qualityEnum;
			}
		}

		return null;
	}

}
