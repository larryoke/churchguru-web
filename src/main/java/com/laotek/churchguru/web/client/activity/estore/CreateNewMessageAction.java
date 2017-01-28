package com.laotek.churchguru.web.client.activity.estore;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class CreateNewMessageAction extends AbstractDispatchAction implements
	Action<CreateNewMessageResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewMessageAction(String title) {
	this.title = title;
    }

    public CreateNewMessageAction() {
    }

    public String getTitle() {
	return title;
    }
}
