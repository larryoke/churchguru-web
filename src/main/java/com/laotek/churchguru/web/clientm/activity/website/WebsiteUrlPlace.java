package com.laotek.churchguru.web.clientm.activity.website;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class WebsiteUrlPlace extends Place {
    private String name;

    public WebsiteUrlPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<WebsiteUrlPlace> {
	@Override
	public String getToken(WebsiteUrlPlace place) {
	    return place.getName();
	}

	@Override
	public WebsiteUrlPlace getPlace(String token) {
	    return new WebsiteUrlPlace(token);
	}
    }
}
