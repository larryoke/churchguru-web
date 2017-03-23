package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageCategoriesPlace extends Place {
    private String name;

    public MediaMessageCategoriesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageCategoriesPlace> {
	@Override
	public String getToken(MediaMessageCategoriesPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageCategoriesPlace getPlace(String token) {
	    return new MediaMessageCategoriesPlace(token);
	}
    }
}
