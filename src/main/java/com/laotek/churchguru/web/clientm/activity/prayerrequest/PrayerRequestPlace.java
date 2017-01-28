package com.laotek.churchguru.web.clientm.activity.prayerrequest;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PrayerRequestPlace extends Place {
    private String name;

    public PrayerRequestPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<PrayerRequestPlace> {
	@Override
	public String getToken(PrayerRequestPlace place) {
	    return place.getName();
	}

	@Override
	public PrayerRequestPlace getPlace(String token) {
	    return new PrayerRequestPlace(token);
	}
    }
}
