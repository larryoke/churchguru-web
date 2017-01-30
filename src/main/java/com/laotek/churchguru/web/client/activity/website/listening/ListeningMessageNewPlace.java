package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningMessageNewPlace extends Place {
    private String identifier;

    public ListeningMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<ListeningMessageNewPlace> {
	@Override
	public String getToken(ListeningMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public ListeningMessageNewPlace getPlace(String token) {
	    return new ListeningMessageNewPlace(token);
	}
    }
}
