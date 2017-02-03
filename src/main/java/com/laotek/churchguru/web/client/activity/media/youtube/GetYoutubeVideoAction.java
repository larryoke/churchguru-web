package com.laotek.churchguru.web.client.activity.media.youtube;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetYoutubeVideoAction extends AbstractDispatchAction implements
	Action<GetYoutubeVideoResult>, HasOrganisationViewRole {

    private String identifier;

    public GetYoutubeVideoAction(String identifier) {
	this.identifier = identifier;
    }

    public GetYoutubeVideoAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
