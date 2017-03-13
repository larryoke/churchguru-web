package com.laotek.churchguru.web.server.handler.churchmobileapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppAction;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppResult;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateType;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class UpdateChurchAppLabelShowFlagHandler extends AbstractCommandHandler
	implements ActionHandler<UpdateChurchAppAction, UpdateChurchAppResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public UpdateChurchAppResult execute(UpdateChurchAppAction action, ExecutionContext context)
	    throws DispatchException {

	if (UpdateType.LABEL.equals(action.getUpdateType())) {
	    organisationDao.updateChurchAppLabel(action.getClientSessionId(), action.getChurchAppTopicEnum(),
		    action.getValue());

	} else if (UpdateType.SHOW.equals(action.getUpdateType())) {
	    organisationDao.updateChurchAppLabelFlagShow(action.getClientSessionId(), action.getChurchAppTopicEnum(),
		    Boolean.parseBoolean(action.getValue()));

	} else if (UpdateType.SOCIAL_MEDIA.equals(action.getUpdateType())) {
	    organisationDao.updateChurchAppSocialMediaUrl(action.getClientSessionId(), action.getChurchAppTopicEnum(),
		    action.getValue());
	}
	return new UpdateChurchAppResult();
    }

    @Override
    public Class<UpdateChurchAppAction> getActionType() {
	return UpdateChurchAppAction.class;
    }

    @Override
    public void rollback(UpdateChurchAppAction action, UpdateChurchAppResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
