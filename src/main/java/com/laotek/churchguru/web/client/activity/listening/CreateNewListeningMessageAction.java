package com.laotek.churchguru.web.client.activity.listening;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class CreateNewListeningMessageAction extends AbstractDispatchAction
	implements Action<CreateNewListeningMessageResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewListeningMessageAction(String title) {
	this.title = title;
    }

    public CreateNewListeningMessageAction() {
    }

    public String getTitle() {
	return title;
    }
}
