package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListeningNotificationsPlace extends Place {
    private String name;

    public ListeningNotificationsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ListeningNotificationsPlace> {
	@Override
	public String getToken(ListeningNotificationsPlace place) {
	    return place.getName();
	}

	@Override
	public ListeningNotificationsPlace getPlace(String token) {
	    return new ListeningNotificationsPlace(token);
	}
    }
}
