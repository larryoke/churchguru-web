package com.laotek.churchguru.web.client.activity.media.youtube;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class CreateNewYoutubeVideoAction extends AbstractDispatchAction implements
	Action<CreateNewYoutubeVideoResult>, HasOrganisationViewRole {

    private String title;

    public CreateNewYoutubeVideoAction(String title) {
	this.title = title;
    }

    public CreateNewYoutubeVideoAction() {
    }

    public String getTitle() {
	return title;
    }
}
