package com.laotek.churchguru.web.client.activity.website.media.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageCustomersPlace extends Place {
    private String name;

    public MediaMessageCustomersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageCustomersPlace> {
	@Override
	public String getToken(MediaMessageCustomersPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageCustomersPlace getPlace(String token) {
	    return new MediaMessageCustomersPlace(token);
	}
    }
}
