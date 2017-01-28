package com.laotek.churchguru.web.server.handler.org;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.client.activity.OrgDetailAction;
import com.laotek.churchguru.web.client.activity.OrgDetailResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class OrgDetailHandler extends AbstractCommandHandler implements
	ActionHandler<OrgDetailAction, OrgDetailResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public OrgDetailResult execute(OrgDetailAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao.getOrganisation(1L);
	return new OrgDetailResult(org.getOrgName());
    }

    @Override
    public Class<OrgDetailAction> getActionType() {
	return OrgDetailAction.class;
    }

    @Override
    public void rollback(OrgDetailAction action, OrgDetailResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
