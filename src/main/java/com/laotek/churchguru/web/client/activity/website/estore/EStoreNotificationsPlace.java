package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreNotificationsPlace extends Place {
    private String name;

    public EStoreNotificationsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreNotificationsPlace> {
	@Override
	public String getToken(EStoreNotificationsPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreNotificationsPlace getPlace(String token) {
	    return new EStoreNotificationsPlace(token);
	}
    }
}
