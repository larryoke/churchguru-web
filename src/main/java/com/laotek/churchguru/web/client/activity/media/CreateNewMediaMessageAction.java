package com.laotek.churchguru.web.client.activity.media;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class CreateNewMediaMessageAction extends AbstractDispatchAction
	implements Action<CreateNewMediaMessageResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewMediaMessageAction(String title) {
	this.title = title;
    }

    public CreateNewMediaMessageAction() {
    }

    public String getTitle() {
	return title;
    }
}
