package com.laotek.churchguru.web.clientm.activity.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MobileHomePlace extends Place {
    private String name;

    public MobileHomePlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<MobileHomePlace> {
	@Override
	public String getToken(MobileHomePlace place) {
	    return place.getName();
	}

	@Override
	public MobileHomePlace getPlace(String token) {
	    return new MobileHomePlace(token);
	}
    }
}
