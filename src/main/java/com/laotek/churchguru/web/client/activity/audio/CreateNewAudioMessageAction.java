package com.laotek.churchguru.web.client.activity.audio;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class CreateNewAudioMessageAction extends AbstractDispatchAction
	implements Action<CreateNewAudioMessageResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewAudioMessageAction(String title) {
	this.title = title;
    }

    public CreateNewAudioMessageAction() {
    }

    public String getTitle() {
	return title;
    }
}
