package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;

public class NoticeOrEventDto implements IsSerializable {

    private static final long serialVersionUID = 1L;
    private String subject;
    private String body;
    private String createdTimeDesc;
    private long id;
    private String identifier;
    private String eventDateAsStr;
    private Date eventDate;
    private EventTime eventTime;
    private boolean hasPicture;
    private BrowseMessagesType messageType;

    public NoticeOrEventDto(int id, String identifier, String title, String body, String eventDateAsStr, Date eventDate,
	    EventTime eventTime, BrowseMessagesType messageType, boolean hasPicture) {
	this.id = id;
	this.identifier = identifier;
	this.subject = title;
	this.body = body;
	this.setEventDateAsStr(eventDateAsStr);
	this.eventDate = eventDate;
	this.eventTime = eventTime;
	this.messageType = messageType;
	this.hasPicture = hasPicture;
    }

    public NoticeOrEventDto() {
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String title) {
	this.subject = title;
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

    public String getEventDateAsStr() {
	return eventDateAsStr;
    }

    public void setEventDateAsStr(String eventDate) {
	this.eventDateAsStr = eventDate;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public boolean hasPicture() {
	return hasPicture;
    }

    public boolean isHasPicture() {
	return hasPicture;
    }

    public void setHasPicture(boolean hasPicture) {
	this.hasPicture = hasPicture;
    }

    public BrowseMessagesType getMessageType() {
	return messageType;
    }

    public void setMessageType(BrowseMessagesType messageType) {
	this.messageType = messageType;
    }

    public EventTime getEventTime() {
	return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
	this.eventTime = eventTime;
    }

    public Date getEventDate() {
	return eventDate;
    }

    public void setEventDate(Date eventDate) {
	this.eventDate = eventDate;
    }
}
