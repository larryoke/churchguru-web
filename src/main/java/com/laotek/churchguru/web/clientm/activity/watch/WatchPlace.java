package com.laotek.churchguru.web.clientm.activity.watch;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WatchPlace extends Place {
    private String name;

    public WatchPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<WatchPlace> {
	@Override
	public String getToken(WatchPlace place) {
	    return place.getName();
	}

	@Override
	public WatchPlace getPlace(String token) {
	    return new WatchPlace(token);
	}
    }
}
