package com.laotek.churchguru.web.client.activity.churchapp.general;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.GetOrgDetailAction;
import com.laotek.churchguru.web.client.activity.GetOrgDetailResult;
import com.laotek.churchguru.web.client.activity.MessageDialog;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelAction;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelFlagAction;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelFlagResult;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateChurchAppLabelResult;

public class GeneralChurchAppActivity extends AbstractActivity implements GeneralChurchAppView.Presenter {

    private ClientFactory clientFactory;
    private GeneralChurchAppView view;
    private GeneralChurchAppPlace place;

    public GeneralChurchAppActivity(GeneralChurchAppPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getGeneralChurchAppView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init(place.getName());
	getOrganisation(view);
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

    @Override
    public void updateChurchAppLabel(ChurchAppTopicEnum churchAppTopic, String value) {
	UpdateChurchAppLabelAction action = new UpdateChurchAppLabelAction(value, churchAppTopic);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<UpdateChurchAppLabelResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
		Window.alert("An error occured:  " + throwable.getMessage());
	    }

	    @Override
	    public void onSuccess(UpdateChurchAppLabelResult result) {
		ApplicationContext.getInstance().getPlaceController().goTo(new GeneralChurchAppPlace(place.getName()));
	    }
	});
    }

    @Override
    public void updateChurchAppLabelShowFlag(ChurchAppTopicEnum churchAppTopic, boolean value) {
	UpdateChurchAppLabelFlagAction action = new UpdateChurchAppLabelFlagAction(value, churchAppTopic);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<UpdateChurchAppLabelFlagResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("An error occured:  " + throwable.getMessage());
		    }

		    @Override
		    public void onSuccess(UpdateChurchAppLabelFlagResult result) {
			// Window.alert("Selected Church Mobile Menu visibility
			// is updated");
		    }
		});
    }

    @Override
    public void submitPastorDeskMessage(String pastorsDeskMessage) {
	SubmitPastorDeskMessageAction action = new SubmitPastorDeskMessageAction();
	action.setMessage(pastorsDeskMessage);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<SubmitPastorDeskMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("An error occured:  " + throwable.getMessage());
		    }

		    @Override
		    public void onSuccess(SubmitPastorDeskMessageResult result) {
			Window.alert("Pastor's Dest Message is updated");
		    }
		});

    }

    @Override
    public void submitWebsiteUrl(String websiteurl) {
    }

    @Override
    public void submitAboutUsOrgDetails(SubmitAboutUsOrgDetailsAction action) {
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<SubmitAboutUsOrgDetailsResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("An error occured:  " + throwable.getMessage());
		    }

		    @Override
		    public void onSuccess(SubmitAboutUsOrgDetailsResult result) {
			MessageDialog.getInstance().display("", "Organisation details are updated", null, false);
			view.clearInstantMessageForm();
		    }
		});
    }
}
