package com.laotek.churchguru.web.client.activity.website.audio;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageNewPlace extends Place {
    private String identifier;

    public AudioMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<AudioMessageNewPlace> {
	@Override
	public String getToken(AudioMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public AudioMessageNewPlace getPlace(String token) {
	    return new AudioMessageNewPlace(token);
	}
    }
}
