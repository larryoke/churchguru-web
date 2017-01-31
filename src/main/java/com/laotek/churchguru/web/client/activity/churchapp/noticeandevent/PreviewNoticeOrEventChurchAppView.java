package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface PreviewNoticeOrEventChurchAppView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(NoticeOrEventDto dto);

    void initTab();

    void instantMessageForm();

    void clearInstantMessageForm();

    public interface Presenter {
	void completeNoticeOrEvent(String subject, boolean notifyAppUsers);

	void goTo(Place place);
    }
}