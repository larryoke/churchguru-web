package com.laotek.churchguru.web.client.activity.website.media.play;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;

public class MediaMessagePlayActivity extends AbstractActivity implements MediaMessagePlayView.Presenter {

    private ClientFactory clientFactory;
    private MediaMessagePlayView view;
    private MediaMessagePlayPlace place;

    public MediaMessagePlayActivity(MediaMessagePlayPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getMediaMessagePlayView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	String identifier = place.getIdentity();
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
	GetMediaAction action = new GetMediaAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMediaResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("Unable to retrieve the message request due to a server error");
	    }

	    @Override
	    public void onSuccess(GetMediaResult result) {
		view.init(result.getMediaUrl(), result.getTitle());
	    }
	});

    }
}
