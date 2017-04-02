package com.laotek.churchguru.web.client.activity.website.media.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageSpeakerNewPlace extends Place {
    private String name;

    public MediaMessageSpeakerNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageSpeakerNewPlace> {
	@Override
	public String getToken(MediaMessageSpeakerNewPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageSpeakerNewPlace getPlace(String token) {
	    return new MediaMessageSpeakerNewPlace(token);
	}
    }
}
