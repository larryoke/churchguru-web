package com.laotek.churchguru.web.server.handler.user;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.web.client.activity.user.GetUserDetailsAction;
import com.laotek.churchguru.web.client.activity.user.GetUserDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetUserDetailsHandler extends AbstractCommandHandler implements
	ActionHandler<GetUserDetailsAction, GetUserDetailsResult> {

    @Autowired
    private UserDao userService;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetUserDetailsResult execute(GetUserDetailsAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation organisation = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	List<User> users = userService.getUsers(organisation);
	GetUserDetailsResult result = new GetUserDetailsResult();
	result.setDtos(convert(users));
	return result;
    }

    @Override
    public Class<GetUserDetailsAction> getActionType() {
	return GetUserDetailsAction.class;
    }

    @Override
    public void rollback(GetUserDetailsAction action,
	    GetUserDetailsResult result, ExecutionContext context)
	    throws DispatchException {

    }

}
