package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageCategoryNewPlace extends Place {
    private String name;

    public MediaMessageCategoryNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageCategoryNewPlace> {
	@Override
	public String getToken(MediaMessageCategoryNewPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageCategoryNewPlace getPlace(String token) {
	    return new MediaMessageCategoryNewPlace(token);
	}
    }
}
