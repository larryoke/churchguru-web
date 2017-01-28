package com.laotek.churchguru.web.client.activity.estore;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;

public class SubmitEStoreMessageResult implements Result {

    private EStoreMessageDto message;

    public SubmitEStoreMessageResult() {
    }

    public SubmitEStoreMessageResult(EStoreMessageDto message) {
	this.message = message;
    }

    public EStoreMessageDto getMessage() {
	return message;
    }

}
