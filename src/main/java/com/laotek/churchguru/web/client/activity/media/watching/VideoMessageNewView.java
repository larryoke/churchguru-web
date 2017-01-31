package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

public interface VideoMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(WatchingMessageDto dto);

    void init();

    void initTab();

    void initWidgets();

    public interface Presenter {
	void submit(SubmitWatchingMessageAction action);

	void goTo(Place place);
    }
}