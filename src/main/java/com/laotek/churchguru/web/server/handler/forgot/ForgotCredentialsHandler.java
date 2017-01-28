package com.laotek.churchguru.web.server.handler.forgot;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.web.client.activity.user.ForgotCredentialAction;
import com.laotek.churchguru.web.client.activity.user.ForgotCredentialResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class ForgotCredentialsHandler extends AbstractCommandHandler implements
	ActionHandler<ForgotCredentialAction, ForgotCredentialResult> {

    @Autowired
    private UserDao userDao;

    @Override
    public ForgotCredentialResult execute(ForgotCredentialAction action,
	    ExecutionContext context) throws DispatchException {

	if (!StringUtils.isEmpty(action.getEmailAddress())) {
	    userDao.createUsernameReminder(action.getEmailAddress());

	} else if (!StringUtils.isEmpty(action.getUsername())) {
	    userDao.createPasswordReset(action.getUsername());

	}

	return new ForgotCredentialResult();
    }

    @Override
    public Class<ForgotCredentialAction> getActionType() {
	return ForgotCredentialAction.class;
    }

    @Override
    public void rollback(ForgotCredentialAction action,
	    ForgotCredentialResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
