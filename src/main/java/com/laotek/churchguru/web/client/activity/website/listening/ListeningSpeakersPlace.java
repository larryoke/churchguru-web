package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningSpeakersPlace extends Place {
    private String name;

    public ListeningSpeakersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningSpeakersPlace> {
	@Override
	public String getToken(ListeningSpeakersPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningSpeakersPlace getPlace(String token) {
	    return new ListeningSpeakersPlace(token);
	}
    }
}
