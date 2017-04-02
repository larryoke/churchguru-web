package com.laotek.churchguru.web.client.activity.user;

import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.UserDto;

import net.customware.gwt.dispatch.shared.Result;

public class UserReloadResult implements Result {
    private OrganisationDto organisationDto;
    private UserDto userDto;

    public UserReloadResult(OrganisationDto organisationDto, UserDto userDto) {
	this.userDto = userDto;
	this.organisationDto = organisationDto;
    }

    public UserReloadResult() {
    }

    public UserDto getUserDto() {
	return userDto;
    }

    public OrganisationDto getOrganisationDto() {
	return organisationDto;
    }

    public void setOrganisationDto(OrganisationDto organisationDto) {
	this.organisationDto = organisationDto;
    }

}
