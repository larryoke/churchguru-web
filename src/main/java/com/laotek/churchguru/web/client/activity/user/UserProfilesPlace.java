package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UserProfilesPlace extends Place {

    private String userIdentifier;

    public UserProfilesPlace(String userIdentifier) {
	this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifier() {
	return userIdentifier;
    }

    public static class Tokenizer implements PlaceTokenizer<UserProfilesPlace> {
	@Override
	public String getToken(UserProfilesPlace place) {
	    return String.valueOf(place.getUserIdentifier());
	}

	@Override
	public UserProfilesPlace getPlace(String token) {
	    return new UserProfilesPlace(token);
	}
    }
}
