package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.shared.watching.VideoMessageDto;

public class VideoMessageNewActivity extends AbstractActivity implements VideoMessageNewView.Presenter {

    private ClientFactory clientFactory;
    private VideoMessageNewView view;
    private VideoMessageNewPlace place;

    public VideoMessageNewActivity(VideoMessageNewPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

	view = clientFactory.getWatchingMessageNewView();
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
	GetWatchingMessageAction action = new GetWatchingMessageAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetWatchingMessageResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("Unable to retrieve the message request due to a server error");
	    }

	    @Override
	    public void onSuccess(GetWatchingMessageResult result) {
		VideoMessageDto dto = result.getMessage();
		view.initNewMessage(dto);
	    }
	});

    }

    @Override
    public void submit(SubmitVideoMessageAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<SubmitVideoMessageResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(SubmitVideoMessageResult result) {
		ApplicationContext.getInstance().getPlaceController().goTo(new VideoMessagesPlace("videos"));
	    }
	});

    }

}
