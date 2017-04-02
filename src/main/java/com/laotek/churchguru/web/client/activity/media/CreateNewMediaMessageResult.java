package com.laotek.churchguru.web.client.activity.media;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewMediaMessageResult implements Result {

    private String newMessageID;

    public CreateNewMediaMessageResult() {
    }

    public CreateNewMediaMessageResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
