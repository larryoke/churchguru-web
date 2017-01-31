package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageCategoryNewPlace extends Place {
    private String name;

    public AudioMessageCategoryNewPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageCategoryNewPlace> {
	@Override
	public String getToken(AudioMessageCategoryNewPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageCategoryNewPlace getPlace(String token) {
	    return new AudioMessageCategoryNewPlace(token);
	}
    }
}
