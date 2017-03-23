package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageGalleryNewItemPlace extends Place {
    private String name;

    public MediaMessageGalleryNewItemPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageGalleryNewItemPlace> {
	@Override
	public String getToken(MediaMessageGalleryNewItemPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageGalleryNewItemPlace getPlace(String token) {
	    return new MediaMessageGalleryNewItemPlace(token);
	}
    }
}
