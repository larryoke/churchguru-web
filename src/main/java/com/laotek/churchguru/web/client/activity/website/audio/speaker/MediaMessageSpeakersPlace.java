package com.laotek.churchguru.web.client.activity.website.audio.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageSpeakersPlace extends Place {
    private String name;

    public MediaMessageSpeakersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageSpeakersPlace> {
	@Override
	public String getToken(MediaMessageSpeakersPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageSpeakersPlace getPlace(String token) {
	    return new MediaMessageSpeakersPlace(token);
	}
    }
}
