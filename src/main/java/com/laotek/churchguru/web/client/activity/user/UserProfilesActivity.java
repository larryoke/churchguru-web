package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;

public class UserProfilesActivity extends AbstractActivity implements
	UserProfilesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private UserProfilesView view;
    private UserProfilesPlace place;

    public UserProfilesActivity(UserProfilesPlace place,
	    ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getUserProfilesView();
	view.setName(name);
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());

	GetAllUserProfilesAction action = new GetAllUserProfilesAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetAllUserProfilesResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			ErrorMessageDialog.getInstance().handleError(throwable);
		    }

		    @Override
		    public void onSuccess(GetAllUserProfilesResult result) {
			view.initUserProfiles(result.getProfiles());
		    }
		});
    }

    @Override
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }

}
