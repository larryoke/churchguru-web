package com.laotek.churchguru.web.client.activity.website.audio.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageCustomerDetailsPlace extends Place {
    private String name;

    public MediaMessageCustomerDetailsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageCustomerDetailsPlace> {
	@Override
	public String getToken(MediaMessageCustomerDetailsPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageCustomerDetailsPlace getPlace(String token) {
	    return new MediaMessageCustomerDetailsPlace(token);
	}
    }
}
