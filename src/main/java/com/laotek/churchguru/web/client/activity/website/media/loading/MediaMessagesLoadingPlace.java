package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MediaMessagesLoadingPlace extends Place {
    private String uploadType;
    private String identity;

    public MediaMessagesLoadingPlace(String uploadType, String identity) {
	this.uploadType = uploadType;
	this.identity = identity;
    }

    public String getIdentity() {
	return identity;
    }

    public String getUploadType() {
	return uploadType;
    }

    public static class Tokenizer implements PlaceTokenizer<MediaMessagesLoadingPlace> {
	@Override
	public String getToken(MediaMessagesLoadingPlace place) {
	    return place.getUploadType() + ":" + place.getIdentity();
	}

	@Override
	public MediaMessagesLoadingPlace getPlace(String token) {
	    String uploadType = token.split(":")[0];
	    String identity = token.split(":")[1];
	    return new MediaMessagesLoadingPlace(uploadType, identity);
	}
    }
}
