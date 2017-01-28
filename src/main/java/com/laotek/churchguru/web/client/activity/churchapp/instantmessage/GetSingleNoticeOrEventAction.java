package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;

public class GetSingleNoticeOrEventAction extends AbstractDispatchAction
	implements Action<GetSingleNoticeOrEventResult> {

    private int id;
    private String identifier;

    public GetSingleNoticeOrEventAction() {
    }

    public GetSingleNoticeOrEventAction(int id, String identifier) {
	this.setId(id);
	this.setIdentifier(identifier);
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
