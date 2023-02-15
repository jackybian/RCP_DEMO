package com.ost.jacky.demo.util;

public class QueryCondition {
	public String getPreConditions() {
		return preConditions;
	}

	public void setPreConditions(String preConditions) {
		this.preConditions = preConditions;
	}

	public String getPostConditions() {
		return postConditions;
	}

	public void setPostConditions(String postConditions) {
		this.postConditions = postConditions;
	}

	public String getContainConditions() {
		return containConditions;
	}

	public void setContainConditions(String containConditions) {
		this.containConditions = containConditions;
	}

	private String preConditions;
	
	private String postConditions;
	
	private String containConditions;
}
