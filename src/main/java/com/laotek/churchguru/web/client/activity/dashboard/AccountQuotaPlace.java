package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class AccountQuotaPlace extends Place {
    private String name;

    public AccountQuotaPlace(String token) {
	this.name = token;
    }

    public String getName() {
	return name;
    }

    public static class Tokenizer implements PlaceTokenizer<AccountQuotaPlace> {
	@Override
	public String getToken(AccountQuotaPlace place) {
	    return place.getName();
	}

	@Override
	public AccountQuotaPlace getPlace(String token) {
	    return new AccountQuotaPlace(token);
	}
    }
}
