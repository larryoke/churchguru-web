package com.laotek.churchguru.web.client.activity.donation;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;

public class DonationSearchActivity extends AbstractActivity implements
	DonationSearchView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private DonationSearchView view;

    public DonationSearchActivity(DonationSearchPlace place,
	    ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getDonationSearchView();
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

    @Override
    public void search(DonationSearchAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<DonationSearchResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("An error has occured during the donation search");
		    }

		    @Override
		    public void onSuccess(DonationSearchResult result) {
			view.setDonationResult(result.getDtos());
		    }
		});

    }
}
