package com.laotek.churchguru.web.client.activity.website.media.cust;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.CustomerDto;

public interface MediaMessageCustomersView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(CustomerDto dto);

    public interface Presenter {
	void goTo(Place place);
    }
}