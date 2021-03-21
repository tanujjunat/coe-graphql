package com.graphql.coe;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerFilter {
	
	private String firstnameContains;
    private String lastnameContains;
    private String customerId;

    @JsonProperty("firstname_contains")
    public String getFirstnameContains() {
		return firstnameContains;
	}

	public void setFirstnameContains(String firstnameContains) {
		this.firstnameContains = firstnameContains;
	}

	@JsonProperty("lastname_contains")
	public String getLastnameContains() {
		return lastnameContains;
	}

	public void setLastnameContains(String lastnameContains) {
		this.lastnameContains = lastnameContains;
	}

	@JsonProperty("customerId")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
