package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreCategoryNewPlace extends Place {
    private String name;

    public EStoreCategoryNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreCategoryNewPlace> {
	@Override
	public String getToken(EStoreCategoryNewPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreCategoryNewPlace getPlace(String token) {
	    return new EStoreCategoryNewPlace(token);
	}
    }
}
