package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreSpeakersPlace extends Place {
    private String name;

    public EStoreSpeakersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreSpeakersPlace> {
	@Override
	public String getToken(EStoreSpeakersPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreSpeakersPlace getPlace(String token) {
	    return new EStoreSpeakersPlace(token);
	}
    }
}
