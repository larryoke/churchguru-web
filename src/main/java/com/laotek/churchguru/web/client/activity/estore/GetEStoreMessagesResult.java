package com.laotek.churchguru.web.client.activity.estore;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;

public class GetEStoreMessagesResult implements Result {

    private List<EStoreMessageDto> messages;

    public GetEStoreMessagesResult() {
    }

    public GetEStoreMessagesResult(List<EStoreMessageDto> messages) {
	this.messages = messages;
    }

    public List<EStoreMessageDto> getMessages() {
	return messages;
    }

}
