package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreCustomersPlace extends Place {
    private String name;

    public EStoreCustomersPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreCustomersPlace> {
	@Override
	public String getToken(EStoreCustomersPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreCustomersPlace getPlace(String token) {
	    return new EStoreCustomersPlace(token);
	}
    }
}
