package com.laotek.churchguru.web.client.activity.password;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;

public class PasswordResetActivity extends AbstractActivity implements
	PasswordResetView.Presenter {

    private ClientFactory clientFactory;
    private PasswordResetPlace place;

    private DispatchServiceAsync dispatchService = GWT
	    .create(DispatchService.class);
    private DispatchClient dispatchClient = new DispatchClient(dispatchService,
	    null, new DefaultExceptionHandler());

    public PasswordResetActivity(PasswordResetPlace place,
	    ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {

	final PasswordResetView view = clientFactory.getPasswordResetView();
	PasswordResetValidatorAction action = new PasswordResetValidatorAction();
	action.setPasswordIdentifier(place.getPasswordIdentifier());
	dispatchClient.execute(action,
		new AsyncCallback<PasswordResetValidatorResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			containerWidget.setWidget(view.asErrorPage());
		    }

		    @Override
		    public void onSuccess(PasswordResetValidatorResult result) {
			view.setPresenter(PasswordResetActivity.this);
			view.initTab();
			view.init(result.getUsername(), result.getOrgName(),
				place.getPasswordIdentifier());
			containerWidget.setWidget(view.asWidget());
			view.initWidgets();
		    }
		});
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
