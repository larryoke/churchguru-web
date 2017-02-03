package com.laotek.churchguru.web.client.activity.media.youtube;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class YoutubeVideoNewPlace extends Place {
    private String identifier;

    public YoutubeVideoNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<YoutubeVideoNewPlace> {
	@Override
	public String getToken(YoutubeVideoNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public YoutubeVideoNewPlace getPlace(String token) {
	    return new YoutubeVideoNewPlace(token);
	}
    }
}
