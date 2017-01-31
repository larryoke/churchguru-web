package com.laotek.churchguru.web.client.activity.website.listening.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningCustomersPlace extends Place {
    private String name;

    public ListeningCustomersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningCustomersPlace> {
	@Override
	public String getToken(ListeningCustomersPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningCustomersPlace getPlace(String token) {
	    return new ListeningCustomersPlace(token);
	}
    }
}
