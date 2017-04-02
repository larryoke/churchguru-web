package com.laotek.churchguru.web.client.activity.media;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetMediaMessageAction extends AbstractDispatchAction implements
	Action<GetMediaMessageResult>, HasOrganisationViewRole {

    private String identifier;

    public GetMediaMessageAction(String identifier) {
	this.identifier = identifier;
    }

    public GetMediaMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
