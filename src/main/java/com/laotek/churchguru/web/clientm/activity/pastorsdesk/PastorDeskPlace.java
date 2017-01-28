package com.laotek.churchguru.web.clientm.activity.pastorsdesk;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PastorDeskPlace extends Place {
    private String name;

    public PastorDeskPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<PastorDeskPlace> {
	@Override
	public String getToken(PastorDeskPlace place) {
	    return place.getName();
	}

	@Override
	public PastorDeskPlace getPlace(String token) {
	    return new PastorDeskPlace(token);
	}
    }
}
