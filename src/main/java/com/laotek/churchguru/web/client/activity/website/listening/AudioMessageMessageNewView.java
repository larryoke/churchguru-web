package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageAction;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public interface AudioMessageMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(AudioMessageMessageDto dto, List<AudioMessageSpeakerDto> speakers,
                        List<AudioMessageCategoryDto> categories,
                        List<AudioMessageMessagePictureDto> pictures,
                        Map<String, Boolean> workersSelectedForFreeMessages);

    void init();

    void initTab();

    void initWidgets();

    void uploadPhotosByWorker();

    public interface Presenter {
	void submit(SubmitListeningMessageAction action);

	void goTo(Place place);
    }
}