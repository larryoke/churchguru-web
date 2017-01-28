package com.laotek.churchguru.web.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SystemSettingsPlace extends Place {

    private String name;

    public SystemSettingsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<SystemSettingsPlace> {
	@Override
	public String getToken(SystemSettingsPlace place) {
	    return place.getName();
	}

	@Override
	public SystemSettingsPlace getPlace(String token) {
	    return new SystemSettingsPlace(token);
	}
    }
}
