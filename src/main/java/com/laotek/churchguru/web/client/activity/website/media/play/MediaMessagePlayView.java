package com.laotek.churchguru.web.client.activity.website.media.play;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface MediaMessagePlayView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String mediaUrl, String title);

    void initTab();

    public interface Presenter {

	void goTo(Place place);
    }
}