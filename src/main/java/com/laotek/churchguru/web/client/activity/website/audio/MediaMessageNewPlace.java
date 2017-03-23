package com.laotek.churchguru.web.client.activity.website.audio;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageNewPlace extends Place {
    private String identifier;

    public MediaMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<MediaMessageNewPlace> {
	@Override
	public String getToken(MediaMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public MediaMessageNewPlace getPlace(String token) {
	    return new MediaMessageNewPlace(token);
	}
    }
}
