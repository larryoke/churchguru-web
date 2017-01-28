package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreCategoriesPlace extends Place {
    private String name;

    public EStoreCategoriesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreCategoriesPlace> {
	@Override
	public String getToken(EStoreCategoriesPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreCategoriesPlace getPlace(String token) {
	    return new EStoreCategoriesPlace(token);
	}
    }
}
