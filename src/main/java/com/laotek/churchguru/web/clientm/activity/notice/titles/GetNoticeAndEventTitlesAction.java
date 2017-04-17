package com.laotek.churchguru.web.clientm.activity.notice.titles;

import net.customware.gwt.dispatch.shared.Action;

public class GetNoticeAndEventTitlesAction implements Action<GetNoticeAndEventTitlesResult> {

    private int index;
    private int counter;

    public GetNoticeAndEventTitlesAction() {
    }

    public GetNoticeAndEventTitlesAction(int index, int counter) {
	this.index = index;
	this.counter = counter;
    }

    public int getIndex() {
	return index;
    }

    public int getCounter() {
	return counter;
    }
}
