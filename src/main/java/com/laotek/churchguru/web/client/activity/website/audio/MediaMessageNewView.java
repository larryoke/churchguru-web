package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageAction;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public interface MediaMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(AudioMessageDto dto, List<AudioMessageSpeakerDto> speakers,
                        List<AudioMessageCategoryDto> categories,
                        List<AudioMessagePictureDto> pictures,
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