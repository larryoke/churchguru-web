package com.laotek.churchguru.web.client.activity.website.audio.notif;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageNotificationsPlace extends Place {
    private String name;

    public AudioMessageNotificationsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageNotificationsPlace> {
	@Override
	public String getToken(AudioMessageNotificationsPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageNotificationsPlace getPlace(String token) {
	    return new AudioMessageNotificationsPlace(token);
	}
    }
}
