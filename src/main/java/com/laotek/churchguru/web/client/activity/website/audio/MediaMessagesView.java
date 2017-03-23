package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

public interface MediaMessagesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<MediaMessageDto> dto);

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}