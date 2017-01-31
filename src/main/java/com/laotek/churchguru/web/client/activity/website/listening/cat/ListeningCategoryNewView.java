package com.laotek.churchguru.web.client.activity.website.listening.cat;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;

public interface ListeningCategoryNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    ListeningCategoryDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}