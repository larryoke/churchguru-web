package com.laotek.churchguru.web.clientm.activity.message.title;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MessageTitlePlace extends Place {
    private String categoryIdentifier;

    public MessageTitlePlace(String token) {
	this.categoryIdentifier = token;
    }

    public String getCategoryIdentifier() {
	return categoryIdentifier;
    }

    public static class Tokenizer implements PlaceTokenizer<MessageTitlePlace> {
	@Override
	public String getToken(MessageTitlePlace place) {
	    return place.getCategoryIdentifier();
	}

	@Override
	public MessageTitlePlace getPlace(String token) {
	    return new MessageTitlePlace(token);
	}
    }
}
