package com.laotek.churchguru.web.client.activity.audio;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.AudioMessageDto;

public class GetAudioMessagesResult implements Result {

    private List<AudioMessageDto> messages;

    public GetAudioMessagesResult() {
    }

    public GetAudioMessagesResult(List<AudioMessageDto> messages) {
	this.messages = messages;
    }

    public List<AudioMessageDto> getMessages() {
	return messages;
    }

}
