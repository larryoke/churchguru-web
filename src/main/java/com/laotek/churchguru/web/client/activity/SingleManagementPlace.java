package com.laotek.churchguru.web.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

@Deprecated
public class SingleManagementPlace extends Place {

    private String userIdentifier;

    public SingleManagementPlace(String userIdentifier) {
	this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifier() {
	return userIdentifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<SingleManagementPlace> {
	@Override
	public String getToken(SingleManagementPlace place) {
	    return String.valueOf(place.getUserIdentifier());
	}

	@Override
	public SingleManagementPlace getPlace(String token) {
	    return new SingleManagementPlace(token);
	}
    }
}
