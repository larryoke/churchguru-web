package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.shared.instantmessage.MessageDto;

public interface GetCurrentNoticesAndEventsHistoryView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init();

    void add(BrowseMessagesType browseMessagesType, List<MessageDto> messages);

    void initTab();

    void initWidgets();

    int getCurrentIndex();

    int getCount();

    void refresh();

    void disableRow(FlexTable rowTab);

    public interface Presenter {

	void getNextMessages(int index, int count);

	void goTo(Place place);

	void deleteMessage(int messageId, String identifier, FlexTable rowTab);
    }
}