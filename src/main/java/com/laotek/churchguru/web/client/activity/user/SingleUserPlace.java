package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SingleUserPlace extends Place {

    private String userIdentifier;

    public SingleUserPlace(String userIdentifier) {
	this.userIdentifier = userIdentifier;
    }

    public String getUserIdentifier() {
	return userIdentifier;
    }

    public static class Tokenizer implements PlaceTokenizer<SingleUserPlace> {
	@Override
	public String getToken(SingleUserPlace place) {
	    return String.valueOf(place.getUserIdentifier());
	}

	@Override
	public SingleUserPlace getPlace(String token) {
	    return new SingleUserPlace(token);
	}
    }
}
