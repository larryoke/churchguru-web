package com.laotek.churchguru.web.client.activity.website.audio.speaker;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public interface AudioMessageSpeakerNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    AudioMessageSpeakerDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}