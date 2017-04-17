package com.laotek.churchguru.web.clientm.activity.message;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MessagePlace extends Place {
    private String messageIdentifier;

    public MessagePlace(String token) {
	this.messageIdentifier = token;
    }

    public String getMessageIdentifier() {
	return messageIdentifier;
    }

    public static class Tokenizer implements PlaceTokenizer<MessagePlace> {
	@Override
	public String getToken(MessagePlace place) {
	    return place.getMessageIdentifier();
	}

	@Override
	public MessagePlace getPlace(String token) {
	    return new MessagePlace(token);
	}
    }
}
