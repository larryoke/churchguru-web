package com.laotek.churchguru.web.clientm.activity.underconstruction;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class UnderConstructionPlace extends Place {
    private String name;

    public UnderConstructionPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<UnderConstructionPlace> {
	@Override
	public String getToken(UnderConstructionPlace place) {
	    return place.getName();
	}

	@Override
	public UnderConstructionPlace getPlace(String token) {
	    return new UnderConstructionPlace(token);
	}
    }
}
