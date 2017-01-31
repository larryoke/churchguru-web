package com.laotek.churchguru.web.client.activity.website.listening.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningCategoriesPlace extends Place {
    private String name;

    public ListeningCategoriesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningCategoriesPlace> {
	@Override
	public String getToken(ListeningCategoriesPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningCategoriesPlace getPlace(String token) {
	    return new ListeningCategoriesPlace(token);
	}
    }
}
