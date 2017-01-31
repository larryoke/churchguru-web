package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.instantmessage.MessageDto;

public class GetCurrentNoticesAndEventsHistoryResult implements Result {

    private List<MessageDto> messages;

    public GetCurrentNoticesAndEventsHistoryResult() {
    }

    public GetCurrentNoticesAndEventsHistoryResult(List<MessageDto> messages) {
	this.messages = messages;
    }

    public List<MessageDto> getMessages() {
	return messages;
    }

    public void setMessages(List<MessageDto> messages) {
	this.messages = messages;
    }
}
