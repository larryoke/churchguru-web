package com.laotek.churchguru.web.clientm.activity.message.body;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SingleNoticeAndEventPlace extends Place {
    private String id;

    public SingleNoticeAndEventPlace(String token) {
	this.id = token;
    }

    public String getId() {
	return id;
    }

    public static class Tokenizer implements PlaceTokenizer<SingleNoticeAndEventPlace> {
	@Override
	public String getToken(SingleNoticeAndEventPlace place) {
	    return place.getId();
	}

	@Override
	public SingleNoticeAndEventPlace getPlace(String token) {
	    return new SingleNoticeAndEventPlace(token);
	}
    }
}
