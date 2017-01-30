package com.laotek.churchguru.web.client.activity.website.listening;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageAction;
import com.laotek.churchguru.web.shared.listening.ListeningCategoryDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessageDto;
import com.laotek.churchguru.web.shared.listening.ListeningMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.ListeningSpeakerDto;

public interface ListeningMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(ListeningMessageDto dto, List<ListeningSpeakerDto> speakers,
                        List<ListeningCategoryDto> categories,
                        List<ListeningMessagePictureDto> pictures,
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