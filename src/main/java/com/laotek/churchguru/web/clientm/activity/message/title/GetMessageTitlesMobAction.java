package com.laotek.churchguru.web.clientm.activity.message.title;

import net.customware.gwt.dispatch.shared.Action;

public class GetMessageTitlesMobAction implements Action<GetMessageTitlesMobResult> {
    public GetMessageTitlesMobAction() {
	super();
    }

    public GetMessageTitlesMobAction(String identifier) {
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
