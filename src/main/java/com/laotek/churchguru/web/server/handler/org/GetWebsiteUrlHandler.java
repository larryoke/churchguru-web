package com.laotek.churchguru.web.server.handler.org;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlAction;
import com.laotek.churchguru.web.clientm.activity.website.WebsiteUrlResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetWebsiteUrlHandler extends AbstractCommandHandler implements
	ActionHandler<WebsiteUrlAction, WebsiteUrlResult> {

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public WebsiteUrlResult execute(WebsiteUrlAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation org = organisationDao.getOrganisation(1L);
	return new WebsiteUrlResult(org.getWebsiteUrl());
    }

    @Override
    public Class<WebsiteUrlAction> getActionType() {
	return WebsiteUrlAction.class;
    }

    @Override
    public void rollback(WebsiteUrlAction action, WebsiteUrlResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
