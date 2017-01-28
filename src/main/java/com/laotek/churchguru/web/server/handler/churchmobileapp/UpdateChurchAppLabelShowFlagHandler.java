package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelFlagAction;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelFlagResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class UpdateChurchAppLabelShowFlagHandler extends AbstractCommandHandler
	implements
	ActionHandler<UpdateChurchAppLabelFlagAction, UpdateChurchAppLabelFlagResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public UpdateChurchAppLabelFlagResult execute(
	    UpdateChurchAppLabelFlagAction action, ExecutionContext context)
	    throws DispatchException {

	organisationDao.updateChurchAppLabelFlagShow(
		action.getClientSessionId(), action.getChurchAppTopicEnum(),
		action.isValue());
	return new UpdateChurchAppLabelFlagResult();
    }

    @Override
    public Class<UpdateChurchAppLabelFlagAction> getActionType() {
	return UpdateChurchAppLabelFlagAction.class;
    }

    @Override
    public void rollback(UpdateChurchAppLabelFlagAction action,
	    UpdateChurchAppLabelFlagResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
