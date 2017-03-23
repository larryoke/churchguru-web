package com.laotek.churchguru.web.client.activity.audio;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

public class SubmitAudioMessageResult implements Result {

    private MediaMessageDto message;

    public SubmitAudioMessageResult() {
    }

    public SubmitAudioMessageResult(MediaMessageDto message) {
	this.message = message;
    }

    public MediaMessageDto getMessage() {
	return message;
    }

}
