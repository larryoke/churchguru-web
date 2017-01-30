package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningCustomerDetailsPlace extends Place {
    private String name;

    public ListeningCustomerDetailsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningCustomerDetailsPlace> {
	@Override
	public String getToken(ListeningCustomerDetailsPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningCustomerDetailsPlace getPlace(String token) {
	    return new ListeningCustomerDetailsPlace(token);
	}
    }
}
