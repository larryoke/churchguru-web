package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetListeningMessageAction extends AbstractDispatchAction implements
	Action<GetListeningMessageResult>, HasOrganisationViewRole {

    private String identifier;

    public GetListeningMessageAction(String identifier) {
	this.identifier = identifier;
    }

    public GetListeningMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
