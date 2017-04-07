package com.laotek.churchguru.web.client.activity.website.media;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageAction;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public interface MediaMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(MediaMessageDto dto, List<MediaMessageSpeakerDto> speakers,
	    List<MediaMessageCategoryDto> categories, Map<String, Boolean> workersSelectedForFreeMessages);

    void init();

    void initTab();

    void initWidgets();

    public interface Presenter {
	void submit(SubmitMediaMessageAction action);

	void goTo(Place place);
    }
}