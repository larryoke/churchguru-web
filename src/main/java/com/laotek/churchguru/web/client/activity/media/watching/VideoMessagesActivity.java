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

public class VideoMessagesActivity extends AbstractActivity implements VideoMessagesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private VideoMessagesView view;

    public VideoMessagesActivity(VideoMessagesPlace place, ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getWatchingMessagesView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
	view.initWidgets();
	getMessages();
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

    private void getMessages() {
	GetWatchingMessagesAction action = new GetWatchingMessagesAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetWatchingMessagesResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetWatchingMessagesResult result) {
		view.init(result.getMessages());
	    }
	});

    }

    @Override
    public void createMessage(String title) {
	// This call is also used in WebsiteActivity
	CreateNewWatchingMessageAction action = new CreateNewWatchingMessageAction(title);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<CreateNewWatchingMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("A server error occured when attempting to create a new message.");
		    }

		    @Override
		    public void onSuccess(CreateNewWatchingMessageResult result) {
			ApplicationContext.getInstance().getPlaceController()
				.goTo(new VideoMessageNewPlace(result.getNewMessageID()));
		    }
		});

    }
}
