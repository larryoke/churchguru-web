package com.laotek.churchguru.web.server.handler.org;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.client.activity.GetOrgDetailAction;
import com.laotek.churchguru.web.client.activity.GetOrgDetailResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.OrganisationDto;

@Component
public class GetOrgDetailHandler extends AbstractCommandHandler implements
	ActionHandler<GetOrgDetailAction, GetOrgDetailResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetOrgDetailResult execute(GetOrgDetailAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());
	OrganisationDto dto = map(org);
	return new GetOrgDetailResult(dto);
    }

    @Override
    public Class<GetOrgDetailAction> getActionType() {
	return GetOrgDetailAction.class;
    }

    @Override
    public void rollback(GetOrgDetailAction action, GetOrgDetailResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
