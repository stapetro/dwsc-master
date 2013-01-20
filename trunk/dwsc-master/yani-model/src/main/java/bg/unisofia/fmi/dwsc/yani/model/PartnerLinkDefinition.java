package bg.unisofia.fmi.dwsc.yani.model;

import java.util.List;

public class PartnerLinkDefinition {
	private String name;
	private String category;
	private String endpointReference;
	private List<WebService> assiciatedServiceList;
	
	public PartnerLinkDefinition(String name, String category,
			String endpointReference, List<WebService> assiciatedServiceList) {
		this.name = name;
		this.category = category;
		this.endpointReference = endpointReference;
		this.assiciatedServiceList = assiciatedServiceList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEndpointReference() {
		return endpointReference;
	}
	public void setEndpointReference(String endpointReference) {
		this.endpointReference = endpointReference;
	}
	public List<WebService> getAssiciatedServiceList() {
		return assiciatedServiceList;
	}
	public void setAssiciatedServiceList(List<WebService> assiciatedServiceList) {
		this.assiciatedServiceList = assiciatedServiceList;
	}
}
