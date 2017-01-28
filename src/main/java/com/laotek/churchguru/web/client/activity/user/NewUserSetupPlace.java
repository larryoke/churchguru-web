package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewUserSetupPlace extends Place {
    private String identifier;

    public NewUserSetupPlace(String identifier) {
	super();
	this.identifier = identifier;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public static class Tokenizer implements PlaceTokenizer<NewUserSetupPlace> {
	@Override
	public String getToken(NewUserSetupPlace place) {
	    return place.getIdentifier();
	}

	@Override
	public NewUserSetupPlace getPlace(String token) {
	    return new NewUserSetupPlace(token);
	}
    }
}
