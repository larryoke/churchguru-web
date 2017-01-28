package com.laotek.churchguru.web.server.handler.user;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.web.client.activity.user.UpdateRolePermissionAction;
import com.laotek.churchguru.web.client.activity.user.UpdateRolePermissionResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class UpdateRolePermissionHandler extends AbstractCommandHandler
implements ActionHandler<UpdateRolePermissionAction, UpdateRolePermissionResult> {

	@Autowired
	private UserDao userService;

	@Override
	public UpdateRolePermissionResult execute(UpdateRolePermissionAction action,
			ExecutionContext context) throws DispatchException {
		UpdateRolePermissionResult result = new UpdateRolePermissionResult();
		
		return result;
	}

	@Override
	public Class<UpdateRolePermissionAction> getActionType() {
		return UpdateRolePermissionAction.class;
	}

	@Override
	public void rollback(UpdateRolePermissionAction arg0,
			UpdateRolePermissionResult result, ExecutionContext context)
			throws DispatchException {
	}

}
