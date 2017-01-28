package com.laotek.churchguru.web.client.activity.home;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.GetOrgDetailAction;
import com.laotek.churchguru.web.client.activity.GetOrgDetailResult;

public class HomeActivity extends AbstractActivity implements HomeView.Presenter {

    private ClientFactory clientFactory;
    private String name;

    public HomeActivity(HomePlace place, ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	HomeView homeView = clientFactory.getHomeView();
	homeView.setPresenter(this);
	homeView.initTab();
	containerWidget.setWidget(homeView.asWidget());
	homeView.init();
	homeView.initWidgets();
	getOrganisation(homeView);
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

    private void getOrganisation(final HomeView homeView) {
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
