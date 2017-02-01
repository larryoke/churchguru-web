package com.laotek.churchguru.web.client.activity.media.watching;

import com.laotek.churchguru.web.shared.watching.VideoMessageDto;

import net.customware.gwt.dispatch.shared.Result;

public class SubmitVideoMessageResult implements Result {

    private VideoMessageDto message;

    public SubmitVideoMessageResult() {
    }

    public SubmitVideoMessageResult(VideoMessageDto message) {
	this.message = message;
    }

    public VideoMessageDto getMessage() {
	return message;
    }

}
