package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewListeningMessageResult implements Result {

    private String newMessageID;

    public CreateNewListeningMessageResult() {
    }

    public CreateNewListeningMessageResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
