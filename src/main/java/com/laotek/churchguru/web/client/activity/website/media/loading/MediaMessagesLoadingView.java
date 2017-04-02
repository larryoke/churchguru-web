package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface MediaMessagesLoadingView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String messageID);

    void initTab();

    void initWidgets();

    public interface Presenter {

	void goTo(Place place);
    }
}