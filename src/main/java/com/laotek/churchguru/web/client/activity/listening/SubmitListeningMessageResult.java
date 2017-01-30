package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;

public class SubmitListeningMessageResult implements Result {

    private ListeningMessageDto message;

    public SubmitListeningMessageResult() {
    }

    public SubmitListeningMessageResult(ListeningMessageDto message) {
	this.message = message;
    }

    public ListeningMessageDto getMessage() {
	return message;
    }

}
