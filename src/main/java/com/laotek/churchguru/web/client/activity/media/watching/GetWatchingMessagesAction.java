package com.laotek.churchguru.web.client.activity.media.watching;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

import net.customware.gwt.dispatch.shared.Action;

public class GetWatchingMessagesAction extends AbstractDispatchAction
	implements Action<GetWatchingMessagesResult>, HasOrganisationViewRole {

    public GetWatchingMessagesAction() {
    }
}
