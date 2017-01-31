package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageGalleryPlace extends Place {
    private String name;

    public AudioMessageGalleryPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageGalleryPlace> {
	@Override
	public String getToken(AudioMessageGalleryPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageGalleryPlace getPlace(String token) {
	    return new AudioMessageGalleryPlace(token);
	}
    }
}
