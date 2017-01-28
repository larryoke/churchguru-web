package com.laotek.churchguru.web.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.activity.user.LoginPlace;
import com.laotek.churchguru.web.client.activity.user.LoginView;

public class LoginActivity extends AbstractActivity implements LoginView.Presenter {
	
	    private ClientFactory clientFactory;
	    private String name;

	    public LoginActivity(LoginPlace place, ClientFactory clientFactory) {
	        this.name = place.getName();
	        this.clientFactory = clientFactory;
	    }

	    /**
	     * Invoked by the ActivityManager to start a new Activity
	     */
	    @Override
	    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	        LoginView loginView = clientFactory.getLoginView();
	        loginView.setPresenter(this);
	        loginView.init();
	        containerWidget.setWidget(loginView.asWidget());
	        
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
