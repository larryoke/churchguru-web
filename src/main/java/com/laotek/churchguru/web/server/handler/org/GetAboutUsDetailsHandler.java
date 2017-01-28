package com.laotek.churchguru.web.server.handler.org;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsDetailsAction;
import com.laotek.churchguru.web.clientm.activity.aboutus.AboutUseDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetAboutUsDetailsHandler extends AbstractCommandHandler implements
	ActionHandler<AboutUsDetailsAction, AboutUseDetailsResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public AboutUseDetailsResult execute(AboutUsDetailsAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao.getOrganisation(1L);
	return new AboutUseDetailsResult(org.getAboutUsMessage(),
		org.getAboutPastorMessage(), org.getServiceTimes(),
		org.getFullAddress(), org.getOrgName(), org.getWebsiteUrl(),
		org.getGoogleApiUrl());
    }

    @Override
    public Class<AboutUsDetailsAction> getActionType() {
	return AboutUsDetailsAction.class;
    }

    @Override
    public void rollback(AboutUsDetailsAction action,
	    AboutUseDetailsResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
