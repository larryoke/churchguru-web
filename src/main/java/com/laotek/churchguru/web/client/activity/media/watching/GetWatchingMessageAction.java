package com.laotek.churchguru.web.client.activity.media.watching;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetWatchingMessageAction extends AbstractDispatchAction implements
	Action<GetWatchingMessageResult>, HasOrganisationViewRole {

    private String identifier;

    public GetWatchingMessageAction(String identifier) {
	this.identifier = identifier;
    }

    public GetWatchingMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
