package com.laotek.churchguru.web.clientm.activity.aboutus;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AboutUsPlace extends Place {
    private String name;

    public AboutUsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AboutUsPlace> {
	@Override
	public String getToken(AboutUsPlace place) {
	    return place.getName();
	}

	@Override
	public AboutUsPlace getPlace(String token) {
	    return new AboutUsPlace(token);
	}
    }
}
