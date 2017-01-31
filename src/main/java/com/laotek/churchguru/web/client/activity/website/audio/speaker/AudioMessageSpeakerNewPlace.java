package com.laotek.churchguru.web.client.activity.website.audio.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageSpeakerNewPlace extends Place {
    private String name;

    public AudioMessageSpeakerNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageSpeakerNewPlace> {
	@Override
	public String getToken(AudioMessageSpeakerNewPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageSpeakerNewPlace getPlace(String token) {
	    return new AudioMessageSpeakerNewPlace(token);
	}
    }
}
