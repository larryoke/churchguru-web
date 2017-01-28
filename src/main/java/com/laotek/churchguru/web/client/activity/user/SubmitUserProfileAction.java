package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.UserProfileDto;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;

public class SubmitUserProfileAction extends AbstractDispatchAction implements
	Action<SubmitUserDetailsResult>, HasUserCrudRole {
    private UserProfileDto newUserProfileDto;

    public UserProfileDto getNewUserProfileDto() {
	return newUserProfileDto;
    }

    public void setNewUserProfileDto(UserProfileDto newUserProfileDto) {
	this.newUserProfileDto = newUserProfileDto;
    }

}
