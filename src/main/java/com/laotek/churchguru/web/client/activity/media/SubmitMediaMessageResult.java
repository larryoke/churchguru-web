package com.laotek.churchguru.web.client.activity.media;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

public class SubmitMediaMessageResult implements Result {

    private MediaMessageDto message;

    public SubmitMediaMessageResult() {
    }

    public SubmitMediaMessageResult(MediaMessageDto message) {
	this.message = message;
    }

    public MediaMessageDto getMessage() {
	return message;
    }

}
