package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.estore.CreateNewMessageAction;
import com.laotek.churchguru.web.client.activity.estore.CreateNewMessageResult;
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessagesAction;
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessagesResult;

public class EStoreMessagesActivity extends AbstractActivity implements
	EStoreMessagesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private EStoreMessagesView view;

    public EStoreMessagesActivity(EStoreMessagesPlace place,
	    ClientFactory clientFactory) {
	this.name = place.getName();
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getEStoreMessagesView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
	view.initWidgets();
	getMessages();
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

    private void getMessages() {
	GetEStoreMessagesAction action = new GetEStoreMessagesAction();
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetEStoreMessagesResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
		    }

		    @Override
		    public void onSuccess(GetEStoreMessagesResult result) {
			view.init(result.getMessages());
		    }
		});

    }

    @Override
    public void createMessage(String title) {
	// This call is also used in WebsiteActivity
	CreateNewMessageAction action = new CreateNewMessageAction(title);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<CreateNewMessageResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			Window.alert("A server error occured when attempting to create a new message.");
		    }

		    @Override
		    public void onSuccess(CreateNewMessageResult result) {
			ApplicationContext
				.getInstance()
				.getPlaceController()
				.goTo(new EStoreMessageNewPlace(result
					.getNewMessageID()));
		    }
		});

    }
}
