package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.UserRoleName;

public class UpdateRolePermissionAction implements Action<UpdateRolePermissionResult>{
	private UserRoleName adminRoleName;
	
	public UserRoleName getAdminRoleName() {
		return adminRoleName;
	}
	public void setAdminRoleName(UserRoleName adminRoleName) {
		this.adminRoleName = adminRoleName;
	}
}
