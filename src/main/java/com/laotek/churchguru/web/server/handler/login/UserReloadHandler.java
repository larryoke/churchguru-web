package com.laotek.churchguru.web.server.handler.login;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.web.client.activity.user.UserReloadAction;
import com.laotek.churchguru.web.client.activity.user.UserReloadResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.UserDto;

@Component
public class UserReloadHandler extends AbstractCommandHandler implements
	ActionHandler<UserReloadAction, UserReloadResult> {

    @Autowired
    private UserDao userDao;

    @Override
    public UserReloadResult execute(UserReloadAction action,
	    ExecutionContext context) throws DispatchException {
	String sessionId = action.getSessionId();

	User user = userDao.getUserByClientSessionId(sessionId);
	if (user == null) {
	    throw new ExpiredSessionException();
	}

	Organisation organisation = user.getOrganisation();
	OrganisationDto orgDto = map(organisation);

	List<User> users = new ArrayList<User>();
	users.add(user);
	List<UserDto> userDtos = convert(users);

	UserReloadResult result = new UserReloadResult(orgDto, userDtos.get(0));
	return result;
    }

    @Override
    public Class<UserReloadAction> getActionType() {
	return UserReloadAction.class;
    }

    @Override
    public void rollback(UserReloadAction action, UserReloadResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
