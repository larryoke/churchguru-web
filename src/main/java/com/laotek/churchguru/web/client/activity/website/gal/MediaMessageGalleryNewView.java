package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.GalleryDto;

public interface MediaMessageGalleryNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    GalleryDto add();

    public interface Presenter {
	void goTo(Place place);
    }
}