package com.laotek.churchguru.web.client.activity.password;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PasswordResetPlace extends Place {
    private String passwordIdentifier;

    public PasswordResetPlace(String passwordIdentifier) {
	this.passwordIdentifier = passwordIdentifier;
    }

    public String getPasswordIdentifier() {
	return passwordIdentifier;
    }

    public static class Tokenizer implements PlaceTokenizer<PasswordResetPlace> {
	@Override
	public String getToken(PasswordResetPlace place) {
	    return place.getPasswordIdentifier();
	}

	@Override
	public PasswordResetPlace getPlace(String token) {
	    return new PasswordResetPlace(token);
	}
    }
}
