package com.laotek.churchguru.web.client.activity.website.audio.cat;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.GetOrgDetailAction;
import com.laotek.churchguru.web.client.activity.GetOrgDetailResult;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppView;

public class AudioMessageCategoryNewActivity extends AbstractActivity implements AudioMessageCategoryNewView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private AudioMessageCategoryNewView view;

    public AudioMessageCategoryNewActivity(AudioMessageCategoryNewPlace place, ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getEStoreCategoryNewView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
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

    @SuppressWarnings("unused")
    private void getOrganisation(final GeneralChurchAppView homeView) {
	GetOrgDetailAction action = new GetOrgDetailAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetOrgDetailResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetOrgDetailResult result) {
		homeView.initOrganisation(result.getDto());
	    }
	});

    }
}
