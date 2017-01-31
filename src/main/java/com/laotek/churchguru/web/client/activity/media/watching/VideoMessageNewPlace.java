package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class VideoMessageNewPlace extends Place {
    private String identifier;

    public VideoMessageNewPlace(String token) {
	this.identifier = token;
    }

    public String getIdentifier() {
	return identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<VideoMessageNewPlace> {
	@Override
	public String getToken(VideoMessageNewPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public VideoMessageNewPlace getPlace(String token) {
	    return new VideoMessageNewPlace(token);
	}
    }
}
