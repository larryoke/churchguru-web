package com.laotek.churchguru.web.client.activity.listening;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;

public class GetListeningMessagesResult implements Result {

    private List<ListeningMessageDto> messages;

    public GetListeningMessagesResult() {
    }

    public GetListeningMessagesResult(List<ListeningMessageDto> messages) {
	this.messages = messages;
    }

    public List<ListeningMessageDto> getMessages() {
	return messages;
    }

}
