package com.laotek.churchguru.web.clientm.activity.twitter;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TwitterPlace extends Place {
    private String name;

    public TwitterPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<TwitterPlace> {
	@Override
	public String getToken(TwitterPlace place) {
	    return place.getName();
	}

	@Override
	public TwitterPlace getPlace(String token) {
	    return new TwitterPlace(token);
	}
    }
}
