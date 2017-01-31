package com.laotek.churchguru.web.client.activity.media.watching;

import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

import net.customware.gwt.dispatch.shared.Result;

public class SubmitWatchingMessageResult implements Result {

    private WatchingMessageDto message;

    public SubmitWatchingMessageResult() {
    }

    public SubmitWatchingMessageResult(WatchingMessageDto message) {
	this.message = message;
    }

    public WatchingMessageDto getMessage() {
	return message;
    }

}
