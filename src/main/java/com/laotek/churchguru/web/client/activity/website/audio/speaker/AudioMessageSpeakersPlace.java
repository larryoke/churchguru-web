package com.laotek.churchguru.web.client.activity.website.audio.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageSpeakersPlace extends Place {
    private String name;

    public AudioMessageSpeakersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageSpeakersPlace> {
	@Override
	public String getToken(AudioMessageSpeakersPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageSpeakersPlace getPlace(String token) {
	    return new AudioMessageSpeakersPlace(token);
	}
    }
}
