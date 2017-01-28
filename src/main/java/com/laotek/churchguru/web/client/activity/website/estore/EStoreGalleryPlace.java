package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreGalleryPlace extends Place {
    private String name;

    public EStoreGalleryPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreGalleryPlace> {
	@Override
	public String getToken(EStoreGalleryPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreGalleryPlace getPlace(String token) {
	    return new EStoreGalleryPlace(token);
	}
    }
}
