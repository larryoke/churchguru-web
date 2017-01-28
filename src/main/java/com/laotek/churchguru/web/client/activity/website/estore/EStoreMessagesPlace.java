package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EStoreMessagesPlace extends Place {
    private String name;

    public EStoreMessagesPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<EStoreMessagesPlace> {
	@Override
	public String getToken(EStoreMessagesPlace place) {
	    return place.getName();
	}

	@Override
	public EStoreMessagesPlace getPlace(String token) {
	    return new EStoreMessagesPlace(token);
	}
    }
}
