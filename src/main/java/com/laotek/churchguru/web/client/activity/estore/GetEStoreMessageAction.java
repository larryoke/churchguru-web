package com.laotek.churchguru.web.client.activity.estore;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetEStoreMessageAction extends AbstractDispatchAction implements
	Action<GetEStoreMessageResult>, HasOrganisationViewRole {

    private String identifier;

    public GetEStoreMessageAction(String identifier) {
	this.identifier = identifier;
    }

    public GetEStoreMessageAction() {
    }

    public String getIdentifier() {
	return identifier;
    }
}
