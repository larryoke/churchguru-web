package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.estore.CustomerDto;

public interface EStoreCustomersView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(CustomerDto dto);

    public interface Presenter {
	void goTo(Place place);
    }
}