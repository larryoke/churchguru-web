package com.laotek.churchguru.web.client.activity.media.watching;

import java.util.List;

import com.laotek.churchguru.web.shared.watching.VideoMessageDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetWatchingMessagesResult implements Result {

    private List<VideoMessageDto> messages;

    public GetWatchingMessagesResult() {
    }

    public GetWatchingMessagesResult(List<VideoMessageDto> messages) {
	this.messages = messages;
    }

    public List<VideoMessageDto> getMessages() {
	return messages;
    }

}
