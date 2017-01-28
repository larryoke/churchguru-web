package com.laotek.churchguru.web.client.activity.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place {
    private String name;

    public HomePlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<HomePlace> {
	@Override
	public String getToken(HomePlace place) {
	    return place.getName();
	}

	@Override
	public HomePlace getPlace(String token) {
	    return new HomePlace(token);
	}
    }
}
