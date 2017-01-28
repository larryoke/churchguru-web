package com.laotek.churchguru.web.shared.instantmessage;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MessageDto implements IsSerializable {

    private int messageId;
    private String title;
    private String body;
    private String eventDateAsStr;
    private String identifier;

    public MessageDto(int messageId, String identifier, String title,
	    String body, String eventDateAsStr) {
	this.messageId = messageId;
	this.identifier = identifier;
	this.title = title;
	this.body = body;
	this.setEventDateAsStr(eventDateAsStr);
    }

    public int getMessageId() {
	return messageId;
    }

    public MessageDto() {
	super();
    }

    public String getTitle() {
	return title;
    }

    public String getBody() {
	return body;
    }

    public String getEventDateAsStr() {
	return eventDateAsStr;
    }

    public void setEventDateAsStr(String eventDateAsStr) {
	this.eventDateAsStr = eventDateAsStr;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }
}
