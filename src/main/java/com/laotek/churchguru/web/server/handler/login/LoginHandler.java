package com.laotek.churchguru.web.server.handler.login;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.web.client.activity.user.LoginAction;
import com.laotek.churchguru.web.client.activity.user.LoginResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class LoginHandler extends AbstractCommandHandler
implements ActionHandler<LoginAction, LoginResult>{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAuditDao userAuditService;
	
	@Override
	public LoginResult execute(LoginAction action, ExecutionContext context)
			throws DispatchException {
		String username = action.getUsername();
		String password = action.getPassword();
		String clientSessionId = RandomStringUtils.random(10, true, true);
		
		userDao.login(username, password);			
		userDao.updateUserAfterLogin(username, clientSessionId);
		userAuditService.audit(clientSessionId, UserAuditTypeName.LOGIN, "Logged in");

		LoginResult result = new LoginResult(clientSessionId);
		return result;
	}

	@Override
	public Class<LoginAction> getActionType() {
		return LoginAction.class;
	}

	@Override
	public void rollback(LoginAction action, LoginResult result,
			ExecutionContext context) throws DispatchException {		
	}

}
