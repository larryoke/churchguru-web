package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

public class NewUserSetupValidationAction implements
	Action<NewUserSetupValidationResult> {

    private String identifier;

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }
}
