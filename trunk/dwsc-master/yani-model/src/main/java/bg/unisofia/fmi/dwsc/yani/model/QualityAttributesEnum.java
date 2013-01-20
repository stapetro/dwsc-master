package bg.unisofia.fmi.dwsc.yani.model;

public enum QualityAttributesEnum {

	AVAILABILITY("availability"), PERFORMANCE("performance"), THROUGHPUT(
			"throughput");

	private String name;

	private QualityAttributesEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
