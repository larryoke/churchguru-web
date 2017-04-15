package com.laotek.churchguru.web.client.activity.website.media.play;

import net.customware.gwt.dispatch.shared.Result;

public class GetMediaResult implements Result {

    private String mediaUrl;
    private String title;

    public GetMediaResult() {
    }

    public GetMediaResult(String mediaUrl, String title) {
	super();
	this.mediaUrl = mediaUrl;
	this.title = title;
    }

    public String getMediaUrl() {
	return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
	this.mediaUrl = mediaUrl;
    }

    public String getTitle() {
	return title;
    }
}
