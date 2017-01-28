package com.laotek.churchguru.web.clientm.activity.website;

import net.customware.gwt.dispatch.shared.Result;

public class WebsiteUrlResult implements Result {

    public WebsiteUrlResult() {
    }

    public WebsiteUrlResult(String message) {
	super();
	this.message = message;
    }

    private String message;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
