package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelAction;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class UpdateChurchAppLabelHandler extends AbstractCommandHandler
	implements
	ActionHandler<UpdateChurchAppLabelAction, UpdateChurchAppLabelResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public UpdateChurchAppLabelResult execute(
	    UpdateChurchAppLabelAction action, ExecutionContext context)
	    throws DispatchException {

	organisationDao.updateChurchAppLabel(action.getClientSessionId(),
		action.getChurchAppTopicEnum(), action.getValue());
	return new UpdateChurchAppLabelResult();
    }

    @Override
    public Class<UpdateChurchAppLabelAction> getActionType() {
	return UpdateChurchAppLabelAction.class;
    }

    @Override
    public void rollback(UpdateChurchAppLabelAction arg0,
	    UpdateChurchAppLabelResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
