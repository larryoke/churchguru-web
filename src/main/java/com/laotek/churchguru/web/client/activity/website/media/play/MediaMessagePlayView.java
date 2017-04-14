package com.laotek.churchguru.web.client.activity.website.media.play;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.model.shared.enums.MediaType;

public interface MediaMessagePlayView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(String mediaUrl, MediaType mediaType);

    void initTab();

    public interface Presenter {

	void goTo(Place place);
    }
}