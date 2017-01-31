package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public interface ListeningSpeakerNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    ListeningSpeakerDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}