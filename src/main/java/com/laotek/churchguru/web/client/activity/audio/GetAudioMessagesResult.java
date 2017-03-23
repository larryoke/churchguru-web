package com.laotek.churchguru.web.client.activity.audio;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

public class GetAudioMessagesResult implements Result {

    private List<MediaMessageDto> messages;

    public GetAudioMessagesResult() {
    }

    public GetAudioMessagesResult(List<MediaMessageDto> messages) {
	this.messages = messages;
    }

    public List<MediaMessageDto> getMessages() {
	return messages;
    }

}
