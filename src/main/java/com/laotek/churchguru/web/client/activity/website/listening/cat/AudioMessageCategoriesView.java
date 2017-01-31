package com.laotek.churchguru.web.client.activity.website.listening.cat;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;

public interface AudioMessageCategoriesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void initCategories(List<AudioMessageCategoryDto> dtos);

    public interface Presenter {
	void goTo(Place place);
    }
}