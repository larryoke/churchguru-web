package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;

public class NewsLettersChartActivity extends AbstractActivity implements NewsLettersChartView.Presenter {
	
	    private ClientFactory clientFactory;
	    private String name;

	    public NewsLettersChartActivity(NewslettersChartPlace place, ClientFactory clientFactory) {
	        this.name = place.getName();
	        this.clientFactory = clientFactory;
	    }

	    /**
	     * Invoked by the ActivityManager to start a new Activity
	     */
	    @Override
	    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	        NewsLettersChartView view = clientFactory.getNewsLettersChartView();
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
	}
