package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Result;

public class NewUserSetupResult implements Result {

    private String redirectUrl;
    private String clientSessionId;

    public String getRedirectUrl() {
	return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
	this.redirectUrl = redirectUrl;
    }

    public String getClientSessionId() {
	return clientSessionId;
    }

    public void setClientSessionId(String clientSessionId) {
	this.clientSessionId = clientSessionId;
    }

}
