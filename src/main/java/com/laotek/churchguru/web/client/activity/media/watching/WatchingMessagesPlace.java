package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WatchingMessagesPlace extends Place {
    private String name;

    public WatchingMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<WatchingMessagesPlace> {
	@Override
	public String getToken(WatchingMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public WatchingMessagesPlace getPlace(String token) {
	    return new WatchingMessagesPlace(token);
	}
    }
}
