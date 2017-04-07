package com.laotek.churchguru.web.client.activity.media;

import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

import net.customware.gwt.dispatch.shared.Result;

public class SubmitMediaMessageResult implements Result {

    private MediaMessageDto message;

    private String newSpeakerIdentity;

    public SubmitMediaMessageResult() {
    }

    public SubmitMediaMessageResult(MediaMessageDto message) {
	this.message = message;
    }

    public MediaMessageDto getMessage() {
	return message;
    }

    public String getNewSpeakerIdentity() {
	return newSpeakerIdentity;
    }

    public void setNewSpeakerIdentity(String newSpeakerIdentity) {
	this.newSpeakerIdentity = newSpeakerIdentity;
    }

}
