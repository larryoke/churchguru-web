package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningGalleryPlace extends Place {
    private String name;

    public ListeningGalleryPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningGalleryPlace> {
	@Override
	public String getToken(ListeningGalleryPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningGalleryPlace getPlace(String token) {
	    return new ListeningGalleryPlace(token);
	}
    }
}
