package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreMessageNewPlace extends Place {
    private String identifier;

    public EStoreMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<EStoreMessageNewPlace> {
	@Override
	public String getToken(EStoreMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public EStoreMessageNewPlace getPlace(String token) {
	    return new EStoreMessageNewPlace(token);
	}
    }
}
