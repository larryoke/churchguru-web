package com.laotek.churchguru.web.server.handler.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.web.client.activity.user.GetUserProfileOptionsAction;
import com.laotek.churchguru.web.client.activity.user.GetUserProfileOptionsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetUserProfileOptionsHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetUserProfileOptionsAction, GetUserProfileOptionsResult> {

    @Autowired
    private UserRoleDao userRoleService;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetUserProfileOptionsResult execute(
	    GetUserProfileOptionsAction action, ExecutionContext context)
	    throws DispatchException {

	Organisation organisation = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	List<UserProfile> profiles = userRoleService
		.getUserProfiles(organisation);
	Map<String, String> options = new HashMap<String, String>();
	for (UserProfile profile : profiles) {
	    options.put(profile.getIdentifier(), profile.getName());
	}
	return new GetUserProfileOptionsResult(options);
    }

    @Override
    public Class<GetUserProfileOptionsAction> getActionType() {
	return GetUserProfileOptionsAction.class;
    }

    @Override
    public void rollback(GetUserProfileOptionsAction action,
	    GetUserProfileOptionsResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
