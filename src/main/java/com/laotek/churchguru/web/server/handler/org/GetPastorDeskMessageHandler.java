package com.laotek.churchguru.web.server.handler.org;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskAction;
import com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetPastorDeskMessageHandler extends AbstractCommandHandler
	implements ActionHandler<PastorDeskAction, PastorDeskResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public PastorDeskResult execute(PastorDeskAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao.getOrganisation(1L);
	return new PastorDeskResult(org.getMessageFromPastorsDesk());
    }

    @Override
    public Class<PastorDeskAction> getActionType() {
	return PastorDeskAction.class;
    }

    @Override
    public void rollback(PastorDeskAction action, PastorDeskResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
