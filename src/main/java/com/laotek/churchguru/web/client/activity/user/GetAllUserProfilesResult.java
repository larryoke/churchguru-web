package com.laotek.churchguru.web.client.activity.user;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.UserProfileDto;

public class GetAllUserProfilesResult implements Result {

    private List<UserProfileDto> profiles = new ArrayList<UserProfileDto>();

    public GetAllUserProfilesResult() {
    }

    public GetAllUserProfilesResult(List<UserProfileDto> profiles) {
	this.setProfiles(profiles);
    }

    public List<UserProfileDto> getProfiles() {
	return profiles;
    }

    public void setProfiles(List<UserProfileDto> profiles) {
	this.profiles = profiles;
    }

}
