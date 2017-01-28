package com.laotek.churchguru.web.client.activity.donation;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DonationSearchPlace extends Place {
    private String name;

    public DonationSearchPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<DonationSearchPlace> {
	@Override
	public String getToken(DonationSearchPlace place) {
	    return place.getName();
	}

	@Override
	public DonationSearchPlace getPlace(String token) {
	    return new DonationSearchPlace(token);
	}
    }
}
