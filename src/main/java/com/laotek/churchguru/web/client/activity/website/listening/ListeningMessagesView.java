package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;

public interface ListeningMessagesView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void initTab();

    void initWidgets();

    void init(List<ListeningMessageDto> dto);

    public interface Presenter {

	void createMessage(String title);

	void goTo(Place place);
    }
}