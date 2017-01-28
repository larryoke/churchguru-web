package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.UserDto;

public class GetSingleUserDetailsResult implements Result{
	private UserDto dto;

	public UserDto getDto() {
		return dto;
	}

	public void setDto(UserDto dto) {
		this.dto = dto;
	}
	
}

