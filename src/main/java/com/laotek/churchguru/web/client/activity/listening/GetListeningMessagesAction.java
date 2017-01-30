package com.laotek.churchguru.web.client.activity.listening;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetListeningMessagesAction extends AbstractDispatchAction implements
	Action<GetListeningMessagesResult>, HasOrganisationViewRole {

    public GetListeningMessagesAction() {
    }
}
