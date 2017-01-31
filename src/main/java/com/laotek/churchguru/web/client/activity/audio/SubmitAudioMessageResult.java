package com.laotek.churchguru.web.client.activity.audio;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.AudioMessageDto;

public class SubmitAudioMessageResult implements Result {

    private AudioMessageDto message;

    public SubmitAudioMessageResult() {
    }

    public SubmitAudioMessageResult(AudioMessageDto message) {
	this.message = message;
    }

    public AudioMessageDto getMessage() {
	return message;
    }

}
