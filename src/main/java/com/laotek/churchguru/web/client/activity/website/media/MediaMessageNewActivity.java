package com.laotek.churchguru.web.client.activity.website.media;

import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessageResult;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageResult;
import com.laotek.churchguru.web.client.activity.website.media.loading.MediaMessagesLoadingPlace;
import com.laotek.churchguru.web.client.activity.website.media.play.MediaMessagePlayPlace;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public class MediaMessageNewActivity extends AbstractActivity implements MediaMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private MediaMessageNewView view;
    private MediaMessageNewPlace place;

    public MediaMessageNewActivity(MediaMessageNewPlace place, ClientFactory clientFactory) {
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
	GetMediaMessageAction action = new GetMediaMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMediaMessageResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("Unable to retrieve the message request due to a server error");
	    }

	    @Override
	    public void onSuccess(GetMediaMessageResult result) {
		MediaMessageDto dto = result.getMessage();
		List<MediaMessageSpeakerDto> speakers = result.getSpeakers();
		List<MediaMessageCategoryDto> categories = result.getCategories();
		Map<String, Boolean> workersSelectedForFreeMessages = result.getWorkersSelectedForFreeMessages();

		view.initNewMessage(dto, speakers, categories, workersSelectedForFreeMessages);
	    }
	});

    }

    @Override
    public void submit(final SubmitMediaMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<SubmitMediaMessageResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(SubmitMediaMessageResult result) {
		if ("speaker".equals(action.getUploadType())) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new MediaMessagesLoadingPlace("speaker", result.getNewSpeakerIdentity()));

		} else if ("desc".equals(action.getUploadType())) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new MediaMessagesLoadingPlace("desc", action.getIdentifier()));

		} else if ("message".equals(action.getUploadType())) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new MediaMessagesLoadingPlace("message", action.getIdentifier()));
		} else {
		    History.back();
		}
	    }
	});

    }

    @Override
    public void gotoPlayMedia(String identifier) {
	ApplicationContext.getInstance().getPlaceController().goTo(new MediaMessagePlayPlace(identifier));
    }

}
