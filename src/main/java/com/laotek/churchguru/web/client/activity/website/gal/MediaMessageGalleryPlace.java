package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageGalleryPlace extends Place {
    private String name;

    public MediaMessageGalleryPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageGalleryPlace> {
	@Override
	public String getToken(MediaMessageGalleryPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageGalleryPlace getPlace(String token) {
	    return new MediaMessageGalleryPlace(token);
	}
    }
}
