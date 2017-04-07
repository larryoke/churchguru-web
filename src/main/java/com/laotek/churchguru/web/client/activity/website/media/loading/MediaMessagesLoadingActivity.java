package com.laotek.churchguru.web.client.activity.website.media.loading;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;

public class MediaMessagesLoadingActivity extends AbstractActivity implements MediaMessagesLoadingView.Presenter {

    private ClientFactory clientFactory;
    private MediaMessagesLoadingView view;
    private MediaMessagesLoadingPlace place;

    public MediaMessagesLoadingActivity(MediaMessagesLoadingPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getMediaMessagesLoadingView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init(place.getUploadType(), place.getIdentity());
	view.initWidgets();
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
}
