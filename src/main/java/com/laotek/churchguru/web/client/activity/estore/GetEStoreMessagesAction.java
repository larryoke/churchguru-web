package com.laotek.churchguru.web.client.activity.estore;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetEStoreMessagesAction extends AbstractDispatchAction implements
	Action<GetEStoreMessagesResult>, HasOrganisationViewRole {

    public GetEStoreMessagesAction() {
    }
}
