package com.laotek.churchguru.web.clientm.activity.privacypolicy;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PrivacyPolicyPlace extends Place {
    private String name;

    public PrivacyPolicyPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<PrivacyPolicyPlace> {
	@Override
	public String getToken(PrivacyPolicyPlace place) {
	    return place.getName();
	}

	@Override
	public PrivacyPolicyPlace getPlace(String token) {
	    return new PrivacyPolicyPlace(token);
	}
    }
}
