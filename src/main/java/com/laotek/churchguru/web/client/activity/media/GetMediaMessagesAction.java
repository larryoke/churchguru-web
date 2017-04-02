package com.laotek.churchguru.web.client.activity.media;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetMediaMessagesAction extends AbstractDispatchAction implements
	Action<GetMediaMessagesResult>, HasOrganisationViewRole {

    public GetMediaMessagesAction() {
    }
}
