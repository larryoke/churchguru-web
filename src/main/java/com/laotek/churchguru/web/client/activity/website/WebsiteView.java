package com.laotek.churchguru.web.client.activity.website;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface WebsiteView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}