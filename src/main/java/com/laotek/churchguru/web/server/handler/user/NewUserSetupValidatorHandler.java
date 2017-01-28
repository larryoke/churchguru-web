package com.laotek.churchguru.web.server.handler.user;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupValidationAction;
import com.laotek.churchguru.web.client.activity.user.NewUserSetupValidationResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class NewUserSetupValidatorHandler extends AbstractCommandHandler
	implements
	ActionHandler<NewUserSetupValidationAction, NewUserSetupValidationResult> {

    @Autowired
    private UserDao userDao;

    @Override
    public NewUserSetupValidationResult execute(
	    NewUserSetupValidationAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();
	if (!userDao.isNewUserSetupIdentifierValid(identifier)) {
	    throw new RuntimeException();
	}
	return new NewUserSetupValidationResult();
    }

    @Override
    public Class<NewUserSetupValidationAction> getActionType() {
	return NewUserSetupValidationAction.class;
    }

    @Override
    public void rollback(NewUserSetupValidationAction action,
	    NewUserSetupValidationResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
