package com.laotek.churchguru.web.client.activity.media.watching;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class CreateNewWatchingMessageAction extends AbstractDispatchAction implements
	Action<CreateNewWatchingMessageResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewWatchingMessageAction(String title) {
	this.title = title;
    }

    public CreateNewWatchingMessageAction() {
    }

    public String getTitle() {
	return title;
    }
}
