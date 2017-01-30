package com.laotek.churchguru.web.client.activity.website.estore;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.NotificationDto;

public interface ListeningNotificationsView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<NotificationDto> dto);

    public interface Presenter {
	void goTo(Place place);
    }
}