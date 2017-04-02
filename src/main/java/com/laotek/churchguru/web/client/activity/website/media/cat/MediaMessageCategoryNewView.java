package com.laotek.churchguru.web.client.activity.website.media.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;

public interface MediaMessageCategoryNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    MediaMessageCategoryDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}