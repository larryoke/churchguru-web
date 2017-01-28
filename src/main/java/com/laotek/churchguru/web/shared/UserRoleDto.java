package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.UserRoleName;

public class UserRoleDto implements IsSerializable{

	private Long id;	
	private UserRoleName adminRoleName;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserRoleName getAdminRoleName() {
		return adminRoleName;
	}
	public void setAdminRoleName(UserRoleName adminRoleName) {
		this.adminRoleName = adminRoleName;
	}
}
