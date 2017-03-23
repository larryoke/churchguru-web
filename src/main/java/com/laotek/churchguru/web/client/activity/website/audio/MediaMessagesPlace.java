package com.laotek.churchguru.web.client.activity.website.audio;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessagesPlace extends Place {
    private String name;

    public MediaMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessagesPlace> {
	@Override
	public String getToken(MediaMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessagesPlace getPlace(String token) {
	    return new MediaMessagesPlace(token);
	}
    }
}
