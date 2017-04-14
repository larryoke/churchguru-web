package com.laotek.churchguru.web.client.activity.website.media.play;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessagePlayPlace extends Place {
    private String identity;

    public MediaMessagePlayPlace(String identity) {
	this.identity = identity;
    }

    public String getIdentity() {
	return identity;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessagePlayPlace> {
	@Override
	public String getToken(MediaMessagePlayPlace place) {
	    return place.getIdentity();
	}

	@Override
	public MediaMessagePlayPlace getPlace(String token) {
	    return new MediaMessagePlayPlace(token);
	}
    }
}
