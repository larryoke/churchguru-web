package com.laotek.churchguru.web.clientm.activity.facebook;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FacebookPlace extends Place {
    private String name;

    public FacebookPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<FacebookPlace> {
	@Override
	public String getToken(FacebookPlace place) {
	    return place.getName();
	}

	@Override
	public FacebookPlace getPlace(String token) {
	    return new FacebookPlace(token);
	}
    }
}
