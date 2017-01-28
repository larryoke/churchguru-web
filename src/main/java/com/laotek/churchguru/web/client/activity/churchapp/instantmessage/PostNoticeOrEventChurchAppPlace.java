package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PostNoticeOrEventChurchAppPlace extends Place {
    private int key;
    private String identifier;

    public PostNoticeOrEventChurchAppPlace(int key, String identifier) {
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
	    PlaceTokenizer<PostNoticeOrEventChurchAppPlace> {
	@Override
	public String getToken(PostNoticeOrEventChurchAppPlace place) {
	    return place.getKey() + ":" + place.getIdentifier();
	}

	@Override
	public PostNoticeOrEventChurchAppPlace getPlace(String token) {
	    int key = Integer.parseInt(token.split(":")[0]);
	    String identifier = token.split(":")[1];
	    return new PostNoticeOrEventChurchAppPlace(key, identifier);
	}
    }
}
