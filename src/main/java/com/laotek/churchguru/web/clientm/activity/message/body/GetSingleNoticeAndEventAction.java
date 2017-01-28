package com.laotek.churchguru.web.clientm.activity.message.body;

import net.customware.gwt.dispatch.shared.Action;

public class GetSingleNoticeAndEventAction implements Action<GetSingleNoticeAndEventResult> {

    private long id;

    public GetSingleNoticeAndEventAction() {
    }

    public GetSingleNoticeAndEventAction(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }
}
