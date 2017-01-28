package com.laotek.churchguru.web.clientm.activity.give;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GivePlace extends Place {
    private String name;

    public GivePlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<GivePlace> {
	@Override
	public String getToken(GivePlace place) {
	    return place.getName();
	}

	@Override
	public GivePlace getPlace(String token) {
	    return new GivePlace(token);
	}
    }
}
