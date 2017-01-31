package com.laotek.churchguru.web.client.activity.churchapp.instantmessage;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.FlexTable;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;

public class GetCurrentNoticesAndEventsHistoryActivity extends AbstractActivity
	implements GetCurrentNoticesAndEventsHistoryView.Presenter {

    private ClientFactory clientFactory;
    private GetCurrentNoticesAndEventsHistoryView view;
    private GetCurrentNoticesAndEventsHistoryPlace place;

    public GetCurrentNoticesAndEventsHistoryActivity(
	    GetCurrentNoticesAndEventsHistoryPlace place,
	    ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getCurrentInstantMessagesHistoryView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	view.init();
	view.initWidgets();
	int currentIndex = view.getCurrentIndex();
	int count = view.getCount();
	getNextMessages(currentIndex, count);
    }

    @Override
    public void onStop() {
	view.refresh();
	super.onStop();
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

    @Override
    public void getNextMessages(int currentIndex, int count) {
	GetCurrentNoticesAndEventsHistoryAction action = new GetCurrentNoticesAndEventsHistoryAction(
		place.getBrowseMessagesType(), currentIndex, count);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(
			action,
			new AsyncCallback<GetCurrentNoticesAndEventsHistoryResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    GetCurrentNoticesAndEventsHistoryResult result) {
				view.add(place.getBrowseMessagesType(),
					result.getMessages());
			    }
			});

    }

    @Override
    public void deleteMessage(int id, String identifier, final FlexTable rowTab) {
	NoticeAndEventAction action = new NoticeAndEventAction(id, identifier,
		EnumNoticeOrEventAction.DELETE);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<NoticeAndEventActionResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				Window.alert("Unable to delete message. Please try again later");
			    }

			    @Override
			    public void onSuccess(
				    NoticeAndEventActionResult result) {
				view.disableRow(rowTab);
			    }
			});

    }
}
