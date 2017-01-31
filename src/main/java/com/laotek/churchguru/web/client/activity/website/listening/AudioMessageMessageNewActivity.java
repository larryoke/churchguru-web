package com.laotek.churchguru.web.client.activity.website.listening;

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
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageResult;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.SubmitListeningMessageResult;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

public class AudioMessageMessageNewActivity extends AbstractActivity implements
	AudioMessageMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private AudioMessageMessageNewView view;
    private AudioMessageMessageNewPlace place;

    public AudioMessageMessageNewActivity(AudioMessageMessageNewPlace place,
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
	GetListeningMessageAction action = new GetListeningMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetListeningMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("Unable to retrieve the message request due to a server error");
		    }

		    @Override
		    public void onSuccess(GetListeningMessageResult result) {
			AudioMessageMessageDto dto = result.getMessage();
			List<AudioMessageSpeakerDto> speakers = result.getSpeakers();
			List<AudioMessageCategoryDto> categories = result
				.getCategories();
			List<AudioMessageMessagePictureDto> pictures = result
				.getPictures();
			Map<String, Boolean> workersSelectedForFreeMessages = result
				.getWorkersSelectedForFreeMessages();

			view.initNewMessage(dto, speakers, categories,
				pictures, workersSelectedForFreeMessages);
		    }
		});

    }

    @Override
    public void submit(SubmitListeningMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitListeningMessageResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    SubmitListeningMessageResult result) {
				view.uploadPhotosByWorker();
			    }
			});

    }

}
