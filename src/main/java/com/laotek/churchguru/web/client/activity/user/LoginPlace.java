package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place {
    private String name;

    public LoginPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<LoginPlace> {
	@Override
	public String getToken(LoginPlace place) {
	    return place.getName();
	}

	@Override
	public LoginPlace getPlace(String token) {
	    return new LoginPlace(token);
	}
    }
}
