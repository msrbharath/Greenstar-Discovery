package com.cognizant.outreach.microservices.gateway.client;

import java.util.List;

public class User {

	private String roleName;

	private String apiToken;

	private List<String> uiMenuList;
	
	private String userId;

	/**
	 * @return
	 */
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getApiToken() {
		return apiToken;
	}

	public void setApiToken(String apiToken) {
		this.apiToken = apiToken;
	}

	public List<String> getUiMenuList() {
		return uiMenuList;
	}

	public void setUiMenuList(List<String> uiMenuList) {
		this.uiMenuList = uiMenuList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
