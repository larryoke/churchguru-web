package com.laotek.churchguru.web.client.activity.audio;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewAudioMessageResult implements Result {

    private String newMessageID;

    public CreateNewAudioMessageResult() {
    }

    public CreateNewAudioMessageResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
