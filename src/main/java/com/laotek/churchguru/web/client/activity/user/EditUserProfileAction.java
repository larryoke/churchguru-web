package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.UserProfileDto;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;

public class EditUserProfileAction extends AbstractDispatchAction implements
	Action<EditUserProfileResult>, HasUserCrudRole {
    private UserProfileDto userProfileDto;

    public UserProfileDto getProfileUserDto() {
	return userProfileDto;
    }

    public void setUserProfileDto(UserProfileDto userProfileDto) {
	this.userProfileDto = userProfileDto;
    }
}
