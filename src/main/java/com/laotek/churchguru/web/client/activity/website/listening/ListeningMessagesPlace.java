package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningMessagesPlace extends Place {
    private String name;

    public ListeningMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningMessagesPlace> {
	@Override
	public String getToken(ListeningMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningMessagesPlace getPlace(String token) {
	    return new ListeningMessagesPlace(token);
	}
    }
}
