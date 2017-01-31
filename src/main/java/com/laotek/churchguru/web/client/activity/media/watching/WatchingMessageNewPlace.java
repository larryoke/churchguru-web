package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WatchingMessageNewPlace extends Place {
    private String identifier;

    public WatchingMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<WatchingMessageNewPlace> {
	@Override
	public String getToken(WatchingMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public WatchingMessageNewPlace getPlace(String token) {
	    return new WatchingMessageNewPlace(token);
	}
    }
}
