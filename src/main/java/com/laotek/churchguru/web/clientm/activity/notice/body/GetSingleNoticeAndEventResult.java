package com.laotek.churchguru.web.clientm.activity.notice.body;

import net.customware.gwt.dispatch.shared.Result;

public class GetSingleNoticeAndEventResult implements Result {

    private String title;
    private String body;
    private String eventDateAndTime;
    private String createdTimeDesc;

    public GetSingleNoticeAndEventResult() {
    }

    public GetSingleNoticeAndEventResult(String title, String body,
	    String createdTimeDesc, String eventDateAndTime) {
	this.title = title;
	this.body = body;
	this.createdTimeDesc = createdTimeDesc;
	this.eventDateAndTime = eventDateAndTime;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public String getCreatedTimeDesc() {
	return createdTimeDesc;
    }

    public void setCreatedTimeDesc(String createdTimeDesc) {
	this.createdTimeDesc = createdTimeDesc;
    }

    public String getEventDateAndTime() {
	return eventDateAndTime;
    }

    public void setEventDateAndTime(String eventDateAndTime) {
	this.eventDateAndTime = eventDateAndTime;
    }
}
