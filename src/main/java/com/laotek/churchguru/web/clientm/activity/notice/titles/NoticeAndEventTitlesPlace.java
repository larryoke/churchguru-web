package com.laotek.churchguru.web.clientm.activity.notice.titles;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NoticeAndEventTitlesPlace extends Place {
    private String newsType;

    public NoticeAndEventTitlesPlace(String token) {
	this.newsType = token;
    }

    public String getNewsType() {
	return newsType;
    }

    public static class Tokenizer implements PlaceTokenizer<NoticeAndEventTitlesPlace> {
	@Override
	public String getToken(NoticeAndEventTitlesPlace place) {
	    return place.getNewsType();
	}

	@Override
	public NoticeAndEventTitlesPlace getPlace(String token) {
	    return new NoticeAndEventTitlesPlace(token);
	}
    }
}
