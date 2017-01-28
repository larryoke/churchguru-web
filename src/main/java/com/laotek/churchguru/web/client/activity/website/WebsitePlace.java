package com.laotek.churchguru.web.client.activity.website;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WebsitePlace extends Place {
    private String name;

    public WebsitePlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<WebsitePlace> {
	@Override
	public String getToken(WebsitePlace place) {
	    return place.getName();
	}

	@Override
	public WebsitePlace getPlace(String token) {
	    return new WebsitePlace(token);
	}
    }
}
