package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageGalleryNewItemPlace extends Place {
    private String name;

    public AudioMessageGalleryNewItemPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageGalleryNewItemPlace> {
	@Override
	public String getToken(AudioMessageGalleryNewItemPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageGalleryNewItemPlace getPlace(String token) {
	    return new AudioMessageGalleryNewItemPlace(token);
	}
    }
}
