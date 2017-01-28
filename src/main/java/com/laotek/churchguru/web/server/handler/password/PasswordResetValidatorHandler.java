package com.laotek.churchguru.web.server.handler.password;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.shared.exceptions.PasswordResetException;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.web.client.activity.password.PasswordResetValidatorAction;
import com.laotek.churchguru.web.client.activity.password.PasswordResetValidatorResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class PasswordResetValidatorHandler extends AbstractCommandHandler
	implements
	ActionHandler<PasswordResetValidatorAction, PasswordResetValidatorResult> {

    @Autowired
    private UserDao userDao;

    @Override
    public PasswordResetValidatorResult execute(
	    PasswordResetValidatorAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getPasswordIdentifier();
	if (!userDao.isPasswordResetIdentifierValid(identifier)) {
	    throw new PasswordResetException();
	}

	User user = userDao.getPasswordResetUser(identifier);
	PasswordResetValidatorResult result = new PasswordResetValidatorResult();
	result.setOrgName(user.getOrganisation().getOrgName());
	result.setUsername(user.getUsername());
	return result;
    }

    @Override
    public Class<PasswordResetValidatorAction> getActionType() {
	return PasswordResetValidatorAction.class;
    }

    @Override
    public void rollback(PasswordResetValidatorAction action,
	    PasswordResetValidatorResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
