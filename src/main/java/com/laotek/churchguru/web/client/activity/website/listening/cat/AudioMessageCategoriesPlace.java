package com.laotek.churchguru.web.client.activity.website.listening.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageCategoriesPlace extends Place {
    private String name;

    public AudioMessageCategoriesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageCategoriesPlace> {
	@Override
	public String getToken(AudioMessageCategoriesPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageCategoriesPlace getPlace(String token) {
	    return new AudioMessageCategoriesPlace(token);
	}
    }
}
