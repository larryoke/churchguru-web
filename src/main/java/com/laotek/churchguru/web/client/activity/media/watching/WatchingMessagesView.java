package com.laotek.churchguru.web.client.activity.media.watching;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

public interface WatchingMessagesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<WatchingMessageDto> dto);

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}