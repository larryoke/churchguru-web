package com.laotek.churchguru.web.client.activity.website.audio.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageCustomersPlace extends Place {
    private String name;

    public AudioMessageCustomersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageCustomersPlace> {
	@Override
	public String getToken(AudioMessageCustomersPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageCustomersPlace getPlace(String token) {
	    return new AudioMessageCustomersPlace(token);
	}
    }
}
