package com.laotek.churchguru.web.client.activity.donation;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface DonationSearchView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void setDonationResult(List<DonationDto> dtos);

    public interface Presenter {
	void search(DonationSearchAction action);

	void goTo(Place place);
    }
}