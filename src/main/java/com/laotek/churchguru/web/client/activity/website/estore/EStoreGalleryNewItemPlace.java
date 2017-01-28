package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreGalleryNewItemPlace extends Place {
    private String name;

    public EStoreGalleryNewItemPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreGalleryNewItemPlace> {
	@Override
	public String getToken(EStoreGalleryNewItemPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreGalleryNewItemPlace getPlace(String token) {
	    return new EStoreGalleryNewItemPlace(token);
	}
    }
}
