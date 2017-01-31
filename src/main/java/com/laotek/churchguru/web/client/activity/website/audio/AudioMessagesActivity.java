package com.laotek.churchguru.web.client.activity.website.audio;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageResult;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessagesAction;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessagesResult;

public class AudioMessagesActivity extends AbstractActivity implements AudioMessagesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private AudioMessagesView view;

    public AudioMessagesActivity(AudioMessagesPlace place, ClientFactory clientFactory) {
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
	GetAudioMessagesAction action = new GetAudioMessagesAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetAudioMessagesResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetAudioMessagesResult result) {
		view.init(result.getMessages());
	    }
	});

    }

    @Override
    public void createMessage(String title) {
	// This call is also used in WebsiteActivity
	CreateNewAudioMessageAction action = new CreateNewAudioMessageAction(title);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<CreateNewAudioMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("A server error occured when attempting to create a new message.");
		    }

		    @Override
		    public void onSuccess(CreateNewAudioMessageResult result) {
			ApplicationContext.getInstance().getPlaceController()
				.goTo(new AudioMessageNewPlace(result.getNewMessageID()));
		    }
		});

    }
}
