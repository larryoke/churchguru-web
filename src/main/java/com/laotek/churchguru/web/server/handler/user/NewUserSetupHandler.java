package com.laotek.churchguru.web.server.handler.user;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupAction;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class NewUserSetupHandler extends AbstractCommandHandler implements
	ActionHandler<NewUserSetupAction, NewUserSetupResult> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public NewUserSetupResult execute(NewUserSetupAction action,
	    ExecutionContext context) throws DispatchException {

	String identifier = action.getIdentifier();
	String username = action.getUsername();
	String password = action.getPassword();
	userDao.completeNewUserSetup(identifier, username, password);

	String clientSessionId = RandomStringUtils.random(10, true, true);
	userDao.updateUserAfterLogin(username, clientSessionId);

	NewUserSetupResult newUserSetupResult = new NewUserSetupResult();
	newUserSetupResult.setClientSessionId(clientSessionId);
	return newUserSetupResult;
    }

    @Override
    public Class<NewUserSetupAction> getActionType() {
	return NewUserSetupAction.class;
    }

    @Override
    public void rollback(NewUserSetupAction action, NewUserSetupResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
