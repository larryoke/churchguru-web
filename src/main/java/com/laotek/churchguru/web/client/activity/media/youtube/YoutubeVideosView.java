package com.laotek.churchguru.web.client.activity.media.youtube;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

public interface YoutubeVideosView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<YoutubeVideoDto> dto);

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}