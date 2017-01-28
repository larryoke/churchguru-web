package com.laotek.churchguru.web.server.handler.password;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.web.client.activity.password.PasswordResetAction;
import com.laotek.churchguru.web.client.activity.password.PasswordResetResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class PasswordResetHandler extends AbstractCommandHandler implements
	ActionHandler<PasswordResetAction, PasswordResetResult> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    private UserAuditDao userAuditService;

    @Override
    public PasswordResetResult execute(PasswordResetAction action,
	    ExecutionContext context) throws DispatchException {

	String resetPasswd = action.getPasswordIdentifier();
	String newPasswd = action.getPassword();

	User user = userDao.resetPassword(resetPasswd, newPasswd);
	String username = user.getUsername();
	userDao.login(username, newPasswd);

	String clientSessionId = RandomStringUtils.random(10, true, true);
	userDao.updateUserAfterLogin(username, clientSessionId);
	userAuditService.audit(clientSessionId,
		UserAuditTypeName.RESET_PASSWORD, "Reset Password");
	userAuditService.audit(clientSessionId, UserAuditTypeName.LOGIN,
		"Logged in");

	return new PasswordResetResult();
    }

    @Override
    public Class<PasswordResetAction> getActionType() {
	return PasswordResetAction.class;
    }

    @Override
    public void rollback(PasswordResetAction action,
	    PasswordResetResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
