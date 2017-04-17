package com.laotek.churchguru.web.clientm.activity.message.category;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MessageCategoryPlace extends Place {
    private String name;

    public MessageCategoryPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MessageCategoryPlace> {
	@Override
	public String getToken(MessageCategoryPlace place) {
	    return place.getName();
	}

	@Override
	public MessageCategoryPlace getPlace(String token) {
	    return new MessageCategoryPlace(token);
	}
    }
}
