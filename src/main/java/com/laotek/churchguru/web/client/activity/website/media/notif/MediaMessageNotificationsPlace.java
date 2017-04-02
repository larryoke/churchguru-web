package com.laotek.churchguru.web.client.activity.website.media.notif;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessageNotificationsPlace extends Place {
    private String name;

    public MediaMessageNotificationsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessageNotificationsPlace> {
	@Override
	public String getToken(MediaMessageNotificationsPlace place) {
	    return place.getName();
	}

	@Override
	public MediaMessageNotificationsPlace getPlace(String token) {
	    return new MediaMessageNotificationsPlace(token);
	}
    }
}
