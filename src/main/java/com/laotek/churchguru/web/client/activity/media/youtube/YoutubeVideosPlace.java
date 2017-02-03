package com.laotek.churchguru.web.client.activity.media.youtube;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class YoutubeVideosPlace extends Place {
    private String name;

    public YoutubeVideosPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<YoutubeVideosPlace> {
	@Override
	public String getToken(YoutubeVideosPlace place) {
	    return place.getName();
	}

	@Override
	public YoutubeVideosPlace getPlace(String token) {
	    return new YoutubeVideosPlace(token);
	}
    }
}
