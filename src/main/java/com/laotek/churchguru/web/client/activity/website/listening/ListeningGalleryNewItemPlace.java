package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningGalleryNewItemPlace extends Place {
    private String name;

    public ListeningGalleryNewItemPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningGalleryNewItemPlace> {
	@Override
	public String getToken(ListeningGalleryNewItemPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningGalleryNewItemPlace getPlace(String token) {
	    return new ListeningGalleryNewItemPlace(token);
	}
    }
}
