package com.laotek.churchguru.web.client.activity.website.estore;

import java.util.List;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.laotek.churchguru.web.client.activity.estore.SubmitEStoreMessageAction;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;
import com.laotek.churchguru.web.shared.estore.EStoreMessagePictureDto;
import com.laotek.churchguru.web.shared.estore.EStoreSpeakerDto;

public interface ListeningMessageNewView extends IsWidget {
    void setPresenter(Presenter presenter);

    void initNewMessage(EStoreMessageDto dto, List<EStoreSpeakerDto> speakers,
	    List<EStoreCategoryDto> categories,
	    List<EStoreMessagePictureDto> pictures,
	    Map<String, Boolean> workersSelectedForFreeMessages);

    void init();

    void initTab();

    void initWidgets();

    void uploadPhotosByWorker();

    public interface Presenter {
	void submit(SubmitEStoreMessageAction action);

	void goTo(Place place);
    }
}