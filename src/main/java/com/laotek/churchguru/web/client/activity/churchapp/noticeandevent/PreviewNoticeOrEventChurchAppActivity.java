package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.MessageDialog;

public class PreviewNoticeOrEventChurchAppActivity extends AbstractActivity
	implements PreviewNoticeOrEventChurchAppView.Presenter {

    private ClientFactory clientFactory;
    private PreviewNoticeOrEventChurchAppView view;
    private PreviewNoticeOrEventChurchAppPlace place;

    public PreviewNoticeOrEventChurchAppActivity(
	    PreviewNoticeOrEventChurchAppPlace place,
	    ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getPreviewNoticeOrEventChurchAppView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());

	init();

    }

    private void init() {
	GetSingleNoticeOrEventAction action = new GetSingleNoticeOrEventAction(
		place.getKey(), place.getIdentifier());
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<GetSingleNoticeOrEventResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    GetSingleNoticeOrEventResult result) {
				view.init(result.getDto());
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

    @Override
    public void completeNoticeOrEvent(String subject, boolean notifyAppUsers) {
	NoticeAndEventAction action = new NoticeAndEventAction(place.getKey(),
		place.getIdentifier(), subject, notifyAppUsers,
		EnumNoticeOrEventAction.POST);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<NoticeAndEventActionResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				Window.alert("An error occured:  "
					+ throwable.getMessage());
			    }

			    @Override
			    public void onSuccess(
				    NoticeAndEventActionResult result) {
				MessageDialog.getInstance().display("",
					"Instant Message is updated", null,
					false);
				view.clearInstantMessageForm();

				ApplicationContext
					.getInstance()
					.getPlaceController()
					.goTo(new GetCurrentNoticesAndEventsHistoryPlace(
						BrowseMessagesType.POSTED
							.name()));

			    }
			});

    }

}
