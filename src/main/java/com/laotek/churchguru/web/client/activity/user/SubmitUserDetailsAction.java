package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;

public class SubmitUserDetailsAction extends AbstractDispatchAction implements
	Action<SubmitUserDetailsResult>, HasUserCrudRole {
    private UserDto newUserDto;

    public UserDto getNewUserDto() {
	return newUserDto;
    }

    public void setNewUserDto(UserDto newUserDto) {
	this.newUserDto = newUserDto;
    }
}
