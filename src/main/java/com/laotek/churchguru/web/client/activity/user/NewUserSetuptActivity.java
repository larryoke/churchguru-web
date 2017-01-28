package com.laotek.churchguru.web.client.activity.user;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.DispatchClient;
import com.laotek.churchguru.web.client.activity.DispatchService;
import com.laotek.churchguru.web.client.activity.DispatchServiceAsync;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;

public class NewUserSetuptActivity extends AbstractActivity implements
	NewUserSetupView.Presenter {

    private ClientFactory clientFactory;
    private NewUserSetupPlace place;

    public NewUserSetuptActivity(NewUserSetupPlace place,
	    ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
	final NewUserSetupView view = clientFactory.getNewUserSetupView();
	NewUserSetupValidationAction action = new NewUserSetupValidationAction();
	action.setIdentifier(place.getIdentifier());
	DispatchServiceAsync dispatchService = GWT
		.create(DispatchService.class);
	DispatchClient dispatchClient = new DispatchClient(dispatchService,
		null, new DefaultExceptionHandler());
	UserContext.getInstance().initDispatchClient(dispatchClient);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<NewUserSetupValidationResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				containerWidget.setWidget(view.asErrorPage());
				ErrorMessageDialog.getInstance().handleError(
					throwable);
			    }

			    @Override
			    public void onSuccess(
				    NewUserSetupValidationResult result) {
				view.setPresenter(NewUserSetuptActivity.this);
				containerWidget.setWidget(view.asWidget());
				view.init(place.getIdentifier());
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
