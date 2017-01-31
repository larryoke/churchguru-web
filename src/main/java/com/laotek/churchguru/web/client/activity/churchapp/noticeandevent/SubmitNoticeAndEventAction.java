package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.EventTime;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class SubmitNoticeAndEventAction extends AbstractDispatchAction
	implements Action<SubmitNoticeAndEventResult>, HasOrganisationCrudRole {

    private int key;
    private String identifier;
    private String subject;
    private String message;
    private Date eventDate;
    private EventTime eventTime;

    public SubmitNoticeAndEventAction(int key, String identifier,
	    String subject, String message, Date eventDate, EventTime eventTime) {
	super();
	this.setKey(key);
	this.setIdentifier(identifier);
	this.subject = subject;
	this.message = message;
	this.setEventDate(eventDate);
	this.setEventTime(eventTime);
    }

    public int getKey() {
	return key;
    }

    public void setKey(int key) {
	this.key = key;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public SubmitNoticeAndEventAction() {
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public Date getEventDate() {
	return eventDate;
    }

    public void setEventDate(Date eventDate) {
	this.eventDate = eventDate;
    }

    public EventTime getEventTime() {
	return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
	this.eventTime = eventTime;
    }
}
