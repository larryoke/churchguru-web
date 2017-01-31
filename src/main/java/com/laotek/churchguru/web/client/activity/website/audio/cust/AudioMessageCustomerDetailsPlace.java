package com.laotek.churchguru.web.client.activity.website.audio.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AudioMessageCustomerDetailsPlace extends Place {
    private String name;

    public AudioMessageCustomerDetailsPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AudioMessageCustomerDetailsPlace> {
	@Override
	public String getToken(AudioMessageCustomerDetailsPlace place) {
	    return place.getName();
	}

	@Override
	public AudioMessageCustomerDetailsPlace getPlace(String token) {
	    return new AudioMessageCustomerDetailsPlace(token);
	}
    }
}
