package com.laotek.churchguru.web.client.activity.media;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

public class GetMediaMessagesResult implements Result {

    private List<MediaMessageDto> messages;

    public GetMediaMessagesResult() {
    }

    public GetMediaMessagesResult(List<MediaMessageDto> messages) {
	this.messages = messages;
    }

    public List<MediaMessageDto> getMessages() {
	return messages;
    }

}
