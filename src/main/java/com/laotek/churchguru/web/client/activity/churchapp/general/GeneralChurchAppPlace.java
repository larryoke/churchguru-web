package com.laotek.churchguru.web.client.activity.churchapp.general;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GeneralChurchAppPlace extends Place {
    private String name;

    public GeneralChurchAppPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<GeneralChurchAppPlace> {
	@Override
	public String getToken(GeneralChurchAppPlace place) {
	    return place.getName();
	}

	@Override
	public GeneralChurchAppPlace getPlace(String token) {
	    return new GeneralChurchAppPlace(token);
	}
    }
}
