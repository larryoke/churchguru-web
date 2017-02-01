package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class VideoMessagesPlace extends Place {
    private String name;

    public VideoMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<VideoMessagesPlace> {
	@Override
	public String getToken(VideoMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public VideoMessagesPlace getPlace(String token) {
	    return new VideoMessagesPlace(token);
	}
    }
}
