package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;

public class GetCurrentNoticesAndEventsHistoryPlace extends Place {
    private BrowseMessagesType browseMessagesType;

    public GetCurrentNoticesAndEventsHistoryPlace(String token) {
	this.browseMessagesType = BrowseMessagesType.valueOf(token);
    }

    public BrowseMessagesType getBrowseMessagesType() {
	return browseMessagesType;
    }

    public static class Tokenizer implements
	    PlaceTokenizer<GetCurrentNoticesAndEventsHistoryPlace> {
	@Override
	public String getToken(GetCurrentNoticesAndEventsHistoryPlace place) {
	    return place.getBrowseMessagesType().name();
	}

	@Override
	public GetCurrentNoticesAndEventsHistoryPlace getPlace(String token) {
	    return new GetCurrentNoticesAndEventsHistoryPlace(token);
	}
    }
}
