package com.laotek.churchguru.web.client.activity.audio;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetAudioMessageAction extends AbstractDispatchAction implements
	Action<GetAudioMessageResult>, HasOrganisationViewRole {

    private String identifier;

    public GetAudioMessageAction(String identifier) {
	this.identifier = identifier;
    }

    public GetAudioMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
