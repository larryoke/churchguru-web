package com.laotek.churchguru.web.client.activity.media.youtube;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

public class YoutubeVideoNewActivity extends AbstractActivity implements YoutubeVideoNewView.Presenter {

    private ClientFactory clientFactory;
    private YoutubeVideoNewView view;
    private YoutubeVideoNewPlace place;

    public YoutubeVideoNewActivity(YoutubeVideoNewPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

	view = clientFactory.getYoutubeNewView();
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
	GetYoutubeVideoAction action = new GetYoutubeVideoAction(identifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetYoutubeVideoResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("Unable to retrieve the message request due to a server error");
	    }

	    @Override
	    public void onSuccess(GetYoutubeVideoResult result) {
		YoutubeVideoDto dto = result.getMessage();
		view.initNewMessage(dto);
	    }
	});

    }

    @Override
    public void submit(SubmitYoutubeVideoAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<SubmitYoutubeVideoResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(SubmitYoutubeVideoResult result) {
		ApplicationContext.getInstance().getPlaceController().goTo(new YoutubeVideosPlace("videos"));
	    }
	});

    }

}
