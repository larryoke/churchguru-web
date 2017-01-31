package com.laotek.churchguru.web.client.activity.media.watching;

import java.util.List;

import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetWatchingMessagesResult implements Result {

    private List<WatchingMessageDto> messages;

    public GetWatchingMessagesResult() {
    }

    public GetWatchingMessagesResult(List<WatchingMessageDto> messages) {
	this.messages = messages;
    }

    public List<WatchingMessageDto> getMessages() {
	return messages;
    }

}
