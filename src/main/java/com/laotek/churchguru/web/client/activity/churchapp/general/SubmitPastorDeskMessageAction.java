package com.laotek.churchguru.web.client.activity.churchapp.general;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;

public class SubmitPastorDeskMessageAction extends AbstractDispatchAction
	implements Action<SubmitPastorDeskMessageResult>,
	HasOrganisationCrudRole {
    private String message;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
