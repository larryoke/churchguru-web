package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.estore.EStoreSpeakerDto;

public interface EStoreSpeakerNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    EStoreSpeakerDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}