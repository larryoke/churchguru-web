package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreSpeakerNewPlace extends Place {
    private String name;

    public EStoreSpeakerNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreSpeakerNewPlace> {
	@Override
	public String getToken(EStoreSpeakerNewPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreSpeakerNewPlace getPlace(String token) {
	    return new EStoreSpeakerNewPlace(token);
	}
    }
}
