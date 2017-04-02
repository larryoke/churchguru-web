package com.laotek.churchguru.web.client.activity.website.media;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.media.CreateNewMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.CreateNewMediaMessageResult;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessagesResult;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessagesAction;

public class MediaMessagesActivity extends AbstractActivity implements MediaMessagesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private MediaMessagesView view;

    public MediaMessagesActivity(MediaMessagesPlace place, ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getEStoreMessagesView();
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
	GetMediaMessagesAction action = new GetMediaMessagesAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMediaMessagesResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetMediaMessagesResult result) {
		view.init(result.getMessages());
	    }
	});

    }

    @Override
    public void createMessage(String title) {
	// This call is also used in WebsiteActivity
	CreateNewMediaMessageAction action = new CreateNewMediaMessageAction(title);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<CreateNewMediaMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("A server error occured when attempting to create a new message.");
		    }

		    @Override
		    public void onSuccess(CreateNewMediaMessageResult result) {
			ApplicationContext.getInstance().getPlaceController()
				.goTo(new MediaMessageNewPlace(result.getNewMessageID()));
		    }
		});

    }
}
