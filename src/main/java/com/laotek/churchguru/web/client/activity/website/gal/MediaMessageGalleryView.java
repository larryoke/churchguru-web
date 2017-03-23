package com.laotek.churchguru.web.client.activity.website.gal;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.GalleryDto;

public interface MediaMessageGalleryView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<GalleryDto> dtos);

    public interface Presenter {
	void goTo(Place place);
    }
}