package com.laotek.churchguru.web.client.activity.listening;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;

public class GetListeningMessagesResult implements Result {

    private List<AudioMessageMessageDto> messages;

    public GetListeningMessagesResult() {
    }

    public GetListeningMessagesResult(List<AudioMessageMessageDto> messages) {
	this.messages = messages;
    }

    public List<AudioMessageMessageDto> getMessages() {
	return messages;
    }

}
