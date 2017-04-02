package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessagesLoadingPlace extends Place {
    private String name;

    public MediaMessagesLoadingPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessagesLoadingPlace> {
	@Override
	public String getToken(MediaMessagesLoadingPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessagesLoadingPlace getPlace(String token) {
	    return new MediaMessagesLoadingPlace(token);
	}
    }
}
