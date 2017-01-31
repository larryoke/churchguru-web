package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PreviewNoticeOrEventChurchAppPlace extends Place {
    private int key;
    private String identifier;

    public PreviewNoticeOrEventChurchAppPlace(int key, String identifier) {
	this.key = key;
	this.identifier = identifier;
    }

    public int getKey() {
	return key;
    }

    public void setKey(int key) {
	this.key = key;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<PreviewNoticeOrEventChurchAppPlace> {
	@Override
	public String getToken(PreviewNoticeOrEventChurchAppPlace place) {
	    return place.getKey() + ":" + place.getIdentifier();
	}

	@Override
	public PreviewNoticeOrEventChurchAppPlace getPlace(String token) {
	    int key = Integer.parseInt(token.split(":")[0]);
	    String identifier = token.split(":")[1];
	    return new PreviewNoticeOrEventChurchAppPlace(key, identifier);
	}
    }
}
