package com.laotek.churchguru.web.client.activity.media.youtube;

import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

import net.customware.gwt.dispatch.shared.Result;

public class SubmitYoutubeVideoResult implements Result {

    private YoutubeVideoDto message;

    public SubmitYoutubeVideoResult() {
    }

    public SubmitYoutubeVideoResult(YoutubeVideoDto message) {
	this.message = message;
    }

    public YoutubeVideoDto getMessage() {
	return message;
    }

}
