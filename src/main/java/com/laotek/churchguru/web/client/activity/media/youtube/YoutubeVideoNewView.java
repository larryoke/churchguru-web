package com.laotek.churchguru.web.client.activity.media.youtube;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

public interface YoutubeVideoNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(YoutubeVideoDto dto);

    void init();

    void initTab();

    void initWidgets();

    public interface Presenter {
	void submit(SubmitYoutubeVideoAction action);

	void goTo(Place place);
    }
}