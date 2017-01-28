package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;

public class AllUserActivity extends AbstractActivity implements
	AllUserView.Presenter {

    private ClientFactory clientFactory;
    // Name that will be appended to "Hello,"
    private String name;

    public AllUserActivity(AllUserPlace place, ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	final AllUserView allAdministratorView = clientFactory.getAllUserView();
	allAdministratorView.setPresenter(this);
	allAdministratorView.initTab();
	containerWidget.setWidget(allAdministratorView.asWidget());

	GetUserDetailsAction action = new GetUserDetailsAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetUserDetailsResult>() {
		    @Override
		    public void onFailure(Throwable caught) {
			ErrorMessageDialog.getInstance().handleError(caught);
		    }

		    @Override
		    public void onSuccess(GetUserDetailsResult result) {
			allAdministratorView.initUsers(result.getDtos());
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
