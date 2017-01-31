package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;

public interface AudioMessageMessagesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<AudioMessageMessageDto> dto);

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}