package com.laotek.churchguru.web.client.activity.media.youtube;

import net.customware.gwt.dispatch.shared.Result;

public class CreateNewYoutubeVideoResult implements Result {

    private String newMessageID;

    public CreateNewYoutubeVideoResult() {
    }

    public CreateNewYoutubeVideoResult(String newMessageID) {
	this.newMessageID = newMessageID;
    }

    public String getNewMessageID() {
	return newMessageID;
    }

}
