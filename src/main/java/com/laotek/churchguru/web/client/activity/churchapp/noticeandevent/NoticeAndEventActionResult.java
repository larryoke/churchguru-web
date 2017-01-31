package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import net.customware.gwt.dispatch.shared.Result;

public class NoticeAndEventActionResult implements Result {

    private int id;
    private String identifier;

    public NoticeAndEventActionResult(int id, String identifier) {
	super();
	this.id = id;
	this.identifier = identifier;
    }

    public NoticeAndEventActionResult() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }
}
