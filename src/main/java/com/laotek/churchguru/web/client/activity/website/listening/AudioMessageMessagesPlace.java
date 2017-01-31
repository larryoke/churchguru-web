package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageMessagesPlace extends Place {
    private String name;

    public AudioMessageMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageMessagesPlace> {
	@Override
	public String getToken(AudioMessageMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageMessagesPlace getPlace(String token) {
	    return new AudioMessageMessagesPlace(token);
	}
    }
}
