package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageAction;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public interface MediaMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(MediaMessageDto dto, List<MediaMessageSpeakerDto> speakers,
                        List<MediaMessageCategoryDto> categories,
                        List<MediaMessagePictureDto> pictures,
                        Map<String, Boolean> workersSelectedForFreeMessages);

    void init();

    void initTab();

    void initWidgets();

    void uploadPhotosByWorker();

    public interface Presenter {
	void submit(SubmitAudioMessageAction action);

	void goTo(Place place);
    }
}