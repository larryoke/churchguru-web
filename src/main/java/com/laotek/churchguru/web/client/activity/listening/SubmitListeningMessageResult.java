package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;

public class SubmitListeningMessageResult implements Result {

    private AudioMessageMessageDto message;

    public SubmitListeningMessageResult() {
    }

    public SubmitListeningMessageResult(AudioMessageMessageDto message) {
	this.message = message;
    }

    public AudioMessageMessageDto getMessage() {
	return message;
    }

}
