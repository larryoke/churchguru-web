package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.UserRoleDto;

public class GetUserRoleResult implements Result{

	private List<UserRoleDto> dtos;

	public List<UserRoleDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<UserRoleDto> dtos) {
		this.dtos = dtos;
	}
}
