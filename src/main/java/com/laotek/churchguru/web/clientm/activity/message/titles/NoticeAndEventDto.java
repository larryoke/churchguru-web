package com.laotek.churchguru.web.clientm.activity.message.titles;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NoticeAndEventDto implements Serializable, IsSerializable {

    private static final long serialVersionUID = 1L;
    private String title;
    private String body;
    private String createdTimeDesc;
    private long id;
    private String eventDate = "";

    public NoticeAndEventDto(String body, String title, int id, String eventDate) {
	this.title = title;
	this.id = id;
	this.body = body;
	this.eventDate = eventDate;
    }

    public NoticeAndEventDto(String title, int id) {
	this.title = title;
	this.id = id;
    }

    public NoticeAndEventDto() {
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
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

    public String getEventDate() {
	return eventDate;
    }

    public void setEventDate(String eventDate) {
	this.eventDate = eventDate;
    }
}
