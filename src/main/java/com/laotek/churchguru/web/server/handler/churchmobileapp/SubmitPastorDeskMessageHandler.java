package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.churchapp.general.SubmitPastorDeskMessageAction;
import com.laotek.churchguru.web.client.activity.churchapp.general.SubmitPastorDeskMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class SubmitPastorDeskMessageHandler extends AbstractCommandHandler
	implements
	ActionHandler<SubmitPastorDeskMessageAction, SubmitPastorDeskMessageResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public SubmitPastorDeskMessageResult execute(
	    SubmitPastorDeskMessageAction action, ExecutionContext context)
	    throws DispatchException {

	organisationDao.updatePastorDeskMessage(action.getClientSessionId(),
		action.getMessage());
	return new SubmitPastorDeskMessageResult();
    }

    @Override
    public Class<SubmitPastorDeskMessageAction> getActionType() {
	return SubmitPastorDeskMessageAction.class;
    }

    @Override
    public void rollback(SubmitPastorDeskMessageAction arg0,
	    SubmitPastorDeskMessageResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
