package com.laotek.churchguru.web.client.activity.password;

import net.customware.gwt.dispatch.shared.Result;

public class PasswordResetValidatorResult implements Result {
    private String orgName;
    private String username;

    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }
}
