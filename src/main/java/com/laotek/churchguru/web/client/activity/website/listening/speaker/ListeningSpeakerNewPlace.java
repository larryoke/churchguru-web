package com.laotek.churchguru.web.client.activity.website.listening.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningSpeakerNewPlace extends Place {
    private String name;

    public ListeningSpeakerNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningSpeakerNewPlace> {
	@Override
	public String getToken(ListeningSpeakerNewPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningSpeakerNewPlace getPlace(String token) {
	    return new ListeningSpeakerNewPlace(token);
	}
    }
}
