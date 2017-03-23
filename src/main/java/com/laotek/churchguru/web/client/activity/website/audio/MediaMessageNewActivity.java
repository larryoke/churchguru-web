package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessageResult;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageResult;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageAction;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public class MediaMessageNewActivity extends AbstractActivity implements
	MediaMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private MediaMessageNewView view;
    private MediaMessageNewPlace place;

    public MediaMessageNewActivity(MediaMessageNewPlace place,
                                       ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getEStoreMessageNewView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
	view.initWidgets();
	String identifier = place.getIdentifier();
	getNewMessage(identifier);
    }

    /**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
	return null;
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }

    private void getNewMessage(String identifier) {
	GetAudioMessageAction action = new GetAudioMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetAudioMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("Unable to retrieve the message request due to a server error");
		    }

		    @Override
		    public void onSuccess(GetAudioMessageResult result) {
			AudioMessageDto dto = result.getMessage();
			List<AudioMessageSpeakerDto> speakers = result.getSpeakers();
			List<AudioMessageCategoryDto> categories = result
				.getCategories();
			List<AudioMessagePictureDto> pictures = result
				.getPictures();
			Map<String, Boolean> workersSelectedForFreeMessages = result
				.getWorkersSelectedForFreeMessages();

			view.initNewMessage(dto, speakers, categories,
				pictures, workersSelectedForFreeMessages);
		    }
		});

    }

    @Override
    public void submit(SubmitAudioMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitAudioMessageResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    SubmitAudioMessageResult result) {
				view.uploadPhotosByWorker();
			    }
			});

    }

}
