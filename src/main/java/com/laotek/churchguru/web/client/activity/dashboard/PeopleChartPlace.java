package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PeopleChartPlace extends Place {
    private String name;

    public PeopleChartPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<PeopleChartPlace> {
	@Override
	public String getToken(PeopleChartPlace place) {
	    return place.getName();
	}

	@Override
	public PeopleChartPlace getPlace(String token) {
	    return new PeopleChartPlace(token);
	}
    }
}
