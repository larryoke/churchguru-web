package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;

public class EditUserDetailsAction extends AbstractDispatchAction implements
	Action<EditUserDetailsResult>, HasUserCrudRole {
    private UserDto currentUserDto;

    public UserDto getCurrentUserDto() {
	return currentUserDto;
    }

    public void setCurrentUserDto(UserDto currentUserDto) {
	this.currentUserDto = currentUserDto;
    }
}
