package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AllUserPlace extends Place {

    private String name;

    public AllUserPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AllUserPlace> {
	@Override
	public String getToken(AllUserPlace place) {
	    return place.getName();
	}

	@Override
	public AllUserPlace getPlace(String token) {
	    return new AllUserPlace(token);
	}
    }
}
