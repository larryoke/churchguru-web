package com.laotek.churchguru.web.client.activity.user;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.UserDto;

public class GetUserDetailsResult implements Result{
	private List<UserDto> dtos = new ArrayList<UserDto>();

	public List<UserDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<UserDto> dtos) {
		this.dtos = dtos;
	}
	
}
