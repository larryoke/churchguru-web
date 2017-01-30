package com.laotek.churchguru.web.client.activity.website.listening;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;

public class ListeningCategoriesActivity extends AbstractActivity implements ListeningCategoriesView.Presenter {

    private ClientFactory clientFactory;
    private String name;
    private ListeningCategoriesView view;

    public ListeningCategoriesActivity(ListeningCategoriesPlace place, ClientFactory clientFactory) {
        this.name = place.getName();
        this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        view = clientFactory.getEStoreCategoriesView();
        view.setPresenter(this);
        view.initTab();
        containerWidget.setWidget(view.asWidget());
        view.init();
        view.initWidgets();
        getCategories();
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

    private void getCategories() {
        GetListeningCategoriesAction action = new GetListeningCategoriesAction();
        UserContext.getInstance().decorateClientSessionId(action);
        UserContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetListeningCategoriesResult>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(GetListeningCategoriesResult result) {
                view.initCategories(result.getCategoryDtos());
            }
        });

    }
}
