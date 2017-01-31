package com.laotek.churchguru.web.client.activity.media.watching;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewWatchingMessageResult implements Result {

    private String newMessageID;

    public CreateNewWatchingMessageResult() {
    }

    public CreateNewWatchingMessageResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
