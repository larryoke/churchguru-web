package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.listening.CreateNewMessageAction;
import com.laotek.churchguru.web.client.activity.listening.CreateNewMessageResult;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessagesAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessagesResult;

public class ListeningMessagesActivity extends AbstractActivity implements
        ListeningMessagesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private ListeningMessagesView view;

    public ListeningMessagesActivity(ListeningMessagesPlace place,
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
        GetListeningMessagesAction action = new GetListeningMessagesAction();
        UserContext.getInstance().decorateClientSessionId(action);
        UserContext.getInstance().getDispatchClient()
                .execute(action, new AsyncCallback<GetListeningMessagesResult>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                    }

                    @Override
                    public void onSuccess(GetListeningMessagesResult result) {
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
                                .goTo(new ListeningMessageNewPlace(result
                                        .getNewMessageID()));
                    }
                });

    }
}
