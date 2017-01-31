package com.laotek.churchguru.web.client.activity.website.audio;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessagesPlace extends Place {
    private String name;

    public AudioMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessagesPlace> {
	@Override
	public String getToken(AudioMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessagesPlace getPlace(String token) {
	    return new AudioMessagesPlace(token);
	}
    }
}
