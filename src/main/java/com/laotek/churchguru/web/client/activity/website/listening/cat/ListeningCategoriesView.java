package com.laotek.churchguru.web.client.activity.website.listening.cat;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;

public interface ListeningCategoriesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void initCategories(List<ListeningCategoryDto> dtos);

    public interface Presenter {
	void goTo(Place place);
    }
}