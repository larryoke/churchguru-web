package com.laotek.churchguru.web.client.activity.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.OrganisationDto;

public interface HomeView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void initOrganisation(OrganisationDto dto);

    public interface Presenter {
	void goTo(Place place);
    }
}