package com.laotek.churchguru.web.clientm.activity.pastorsdesk;

import net.customware.gwt.dispatch.shared.Result;

public class PastorDeskResult implements Result {

    public PastorDeskResult() {
    }

    public PastorDeskResult(String message) {
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
