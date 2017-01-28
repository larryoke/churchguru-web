package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.shared.enums.Country;
import com.laotek.churchguru.web.client.activity.churchapp.general.SubmitAboutUsOrgDetailsAction;
import com.laotek.churchguru.web.client.activity.churchapp.general.SubmitAboutUsOrgDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class SubmitAboutUsOrgDetailsHandler extends AbstractCommandHandler
	implements
	ActionHandler<SubmitAboutUsOrgDetailsAction, SubmitAboutUsOrgDetailsResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public SubmitAboutUsOrgDetailsResult execute(
	    SubmitAboutUsOrgDetailsAction action, ExecutionContext context)
	    throws DispatchException {

	organisationDao.updateAboutUseDetails(action.getClientSessionId(),
		action.getOrgName(), action.getAdminEmailAddress(),
		action.getPrayerRequestEmailAddress(),
		action.getAboutUsMessage(), action.getAboutPastorMessage(),
		action.getServiceTimes(), action.getAdressLine1(),
		action.getAdressLine2(), action.getPostcode(),
		Country.UNITED_KINGDOM, action.getWebsiteUrl());

	return new SubmitAboutUsOrgDetailsResult();
    }

    @Override
    public Class<SubmitAboutUsOrgDetailsAction> getActionType() {
	return SubmitAboutUsOrgDetailsAction.class;
    }

    @Override
    public void rollback(SubmitAboutUsOrgDetailsAction arg0,
	    SubmitAboutUsOrgDetailsResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
