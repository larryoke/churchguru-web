package com.laotek.churchguru.web.clientm.activity.message;

import net.customware.gwt.dispatch.shared.Action;

public class GetMessageMobAction implements Action<GetMessageMobResult> {
    public GetMessageMobAction() {
	super();
    }

    public GetMessageMobAction(String identifier) {
	super();
	this.identifier = identifier;
    }

    private String identifier;

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }
}
