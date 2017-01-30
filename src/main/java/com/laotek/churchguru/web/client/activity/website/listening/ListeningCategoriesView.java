package com.laotek.churchguru.web.client.activity.website.estore;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;

public interface ListeningCategoriesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void initCategories(List<EStoreCategoryDto> dtos);

    public interface Presenter {
	void goTo(Place place);
    }
}