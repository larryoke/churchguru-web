package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningCategoryNewPlace extends Place {
    private String name;

    public ListeningCategoryNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningCategoryNewPlace> {
	@Override
	public String getToken(ListeningCategoryNewPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningCategoryNewPlace getPlace(String token) {
	    return new ListeningCategoryNewPlace(token);
	}
    }
}
