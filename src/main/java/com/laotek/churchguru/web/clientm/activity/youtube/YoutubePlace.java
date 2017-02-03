package com.laotek.churchguru.web.clientm.activity.youtube;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class YoutubePlace extends Place {
    private String name;

    public YoutubePlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<YoutubePlace> {
	@Override
	public String getToken(YoutubePlace place) {
	    return place.getName();
	}

	@Override
	public YoutubePlace getPlace(String token) {
	    return new YoutubePlace(token);
	}
    }
}
