package com.laotek.churchguru.web.client.activity.user;

import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Result;

public class GetUserProfileOptionsResult implements Result {
    public GetUserProfileOptionsResult() {
    }

    public GetUserProfileOptionsResult(Map<String, String> profiles) {
	this.profiles = profiles;
    }

    private Map<String, String> profiles = new HashMap<String, String>();

    public Map<String, String> getProfiles() {
	return profiles;
    }

    public void setProfiles(Map<String, String> profiles) {
	this.profiles = profiles;
    }
}
