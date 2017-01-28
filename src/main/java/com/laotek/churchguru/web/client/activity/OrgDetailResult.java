package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Result;

public class OrgDetailResult implements Result{
	private String orgName;
	
	public OrgDetailResult(String orgName) {
		this.orgName = orgName;
	}

	public OrgDetailResult() {
	}

	public String getOrgName() {
		return orgName;
	}
}
