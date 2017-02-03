package com.laotek.churchguru.web.client.activity.media.youtube;

import java.util.List;

import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

import net.customware.gwt.dispatch.shared.Result;

public class GetYoutubeVideosResult implements Result {

    private List<YoutubeVideoDto> messages;

    public GetYoutubeVideosResult() {
    }

    public GetYoutubeVideosResult(List<YoutubeVideoDto> messages) {
	this.messages = messages;
    }

    public List<YoutubeVideoDto> getMessages() {
	return messages;
    }

}
