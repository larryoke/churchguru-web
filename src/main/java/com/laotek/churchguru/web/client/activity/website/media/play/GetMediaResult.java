package com.laotek.churchguru.web.client.activity.website.media.play;

import com.laotek.churchguru.model.shared.enums.MediaType;

import net.customware.gwt.dispatch.shared.Result;

public class GetMediaResult implements Result {

    private String mediaUrl;
    private MediaType mediaType;

    public GetMediaResult() {
    }

    public GetMediaResult(String mediaUrl, MediaType mediaType) {
	super();
	this.mediaUrl = mediaUrl;
	this.mediaType = mediaType;
    }

    public String getMediaUrl() {
	return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
	this.mediaUrl = mediaUrl;
    }

    public MediaType getMediaType() {
	return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
	this.mediaType = mediaType;
    }
}
