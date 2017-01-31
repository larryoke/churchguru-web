package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

public interface AudioMessageCategoryNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    AudioMessageCategoryDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}