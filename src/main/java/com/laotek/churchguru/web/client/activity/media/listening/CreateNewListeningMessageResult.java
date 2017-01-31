package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewMessageResult implements Result {

    private String newMessageID;

    public CreateNewMessageResult() {
    }

    public CreateNewMessageResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
