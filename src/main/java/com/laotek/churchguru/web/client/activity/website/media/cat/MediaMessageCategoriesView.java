package com.laotek.churchguru.web.client.activity.website.media.cat;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;

public interface MediaMessageCategoriesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void initCategories(List<MediaMessageCategoryDto> dtos);

    public interface Presenter {
	void goTo(Place place);
    }
}