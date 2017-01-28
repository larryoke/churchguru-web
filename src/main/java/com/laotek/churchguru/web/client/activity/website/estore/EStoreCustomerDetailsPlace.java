package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreCustomerDetailsPlace extends Place {
    private String name;

    public EStoreCustomerDetailsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreCustomerDetailsPlace> {
	@Override
	public String getToken(EStoreCustomerDetailsPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreCustomerDetailsPlace getPlace(String token) {
	    return new EStoreCustomerDetailsPlace(token);
	}
    }
}
