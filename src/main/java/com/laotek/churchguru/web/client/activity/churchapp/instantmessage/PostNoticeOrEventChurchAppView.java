package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import java.util.Date;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.model.shared.enums.EventTime;

public interface PostNoticeOrEventChurchAppView extends IsWidget {
    void setPresenter(Presenter presenter);

    void init(NoticeOrEventDto dto);

    void initTab();

    void instantMessageForm();

    void clearInstantMessageForm();

    void alertSavedAsDraft();

    void topScroll();

    void validateTextArea();

    void stopTextAreaValidation();

    void setServletPath();

    public interface Presenter {

	void saveAsDraft(String subject, String message, Date eventDate,
		EventTime eventTime, boolean alertResult);

	void previewNoticeOrEvent(String subject, String message,
		Date eventDate, EventTime eventTime);

	void deleteMessage();

	void removePicture();

	void goTo(Place place);

	void refresh();
    }
}