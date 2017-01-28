package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewslettersChartPlace extends Place {
    private String name;

    public NewslettersChartPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<NewslettersChartPlace> {
	@Override
	public String getToken(NewslettersChartPlace place) {
	    return place.getName();
	}

	@Override
	public NewslettersChartPlace getPlace(String token) {
	    return new NewslettersChartPlace(token);
	}
    }
}
