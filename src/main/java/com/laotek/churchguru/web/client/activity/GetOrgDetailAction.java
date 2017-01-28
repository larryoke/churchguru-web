package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;

public class GetOrgDetailAction extends AbstractDispatchAction implements
	Action<GetOrgDetailResult>, HasOrganisationViewRole {

    public GetOrgDetailAction() {
    }
}
