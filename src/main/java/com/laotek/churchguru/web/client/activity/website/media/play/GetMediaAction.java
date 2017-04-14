package com.laotek.churchguru.web.client.activity.website.media.play;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetMediaAction extends AbstractDispatchAction implements
	Action<GetMediaResult>, HasOrganisationViewRole {

    private String identifier;

    public GetMediaAction(String identifier) {
	this.identifier = identifier;
    }

    public GetMediaAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
