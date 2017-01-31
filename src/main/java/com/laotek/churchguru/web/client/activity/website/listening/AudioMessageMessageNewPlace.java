package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageMessageNewPlace extends Place {
    private String identifier;

    public AudioMessageMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<AudioMessageMessageNewPlace> {
	@Override
	public String getToken(AudioMessageMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public AudioMessageMessageNewPlace getPlace(String token) {
	    return new AudioMessageMessageNewPlace(token);
	}
    }
}
