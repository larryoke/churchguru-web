package com.laotek.churchguru.web.client.activity.website.listening.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public interface ListeningSpeakersView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(AudioMessageSpeakerDto dto);

    public interface Presenter {
	void goTo(Place place);
    }
}