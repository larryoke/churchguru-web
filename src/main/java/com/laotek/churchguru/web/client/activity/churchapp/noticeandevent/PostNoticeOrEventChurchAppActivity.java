package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;

public class PostNoticeOrEventChurchAppActivity extends AbstractActivity
	implements PostNoticeOrEventChurchAppView.Presenter {

    private ClientFactory clientFactory;
    private PostNoticeOrEventChurchAppView view;
    private PostNoticeOrEventChurchAppPlace place;

    public PostNoticeOrEventChurchAppActivity(
	    PostNoticeOrEventChurchAppPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    /**
     * Invoked by the ActivityManager to start a new Activity
     */
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	view = clientFactory.getPostNoticeOrEventChurchAppView();
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());

	init();

	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		view.topScroll();
	    }
	});
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
	view.stopTextAreaValidation();
	return null;
    }

    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }

    @Override
    public void previewNoticeOrEvent(String subject, String message,
	    Date eventDate, EventTime eventTime) {
	SubmitNoticeAndEventAction action = new SubmitNoticeAndEventAction(
		place.getKey(), place.getIdentifier(), subject, message,
		eventDate, eventTime);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitNoticeAndEventResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				Window.alert("An error occured:  "
					+ throwable.getMessage());
			    }

			    @Override
			    public void onSuccess(
				    SubmitNoticeAndEventResult result) {
				ApplicationContext
					.getInstance()
					.getPlaceController()
					.goTo(new PreviewNoticeOrEventChurchAppPlace(
						place.getKey(), place
							.getIdentifier()));

			    }
			});

    }

    @Override
    public void saveAsDraft(String subject, String message, Date eventDate,
	    EventTime eventTime, final boolean alertResult) {
	SubmitNoticeAndEventAction action = new SubmitNoticeAndEventAction(
		place.getKey(), place.getIdentifier(), subject, message,
		eventDate, eventTime);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<SubmitNoticeAndEventResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				Window.alert("An error occured:  "
					+ throwable.getMessage());
			    }

			    @Override
			    public void onSuccess(
				    SubmitNoticeAndEventResult result) {
				if (alertResult) {
				    view.alertSavedAsDraft();
				}
			    }
			});

    }

    @Override
    public void deleteMessage() {
	NoticeAndEventAction action = new NoticeAndEventAction(place.getKey(),
		place.getIdentifier(), EnumNoticeOrEventAction.DELETE);
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
				ApplicationContext
					.getInstance()
					.getPlaceController()
					.goTo(new GetCurrentNoticesAndEventsHistoryPlace(
						BrowseMessagesType.POSTED
							.name()));
			    }
			});

    }

    @Override
    public void removePicture() {
	NoticeAndEventAction action = new NoticeAndEventAction(place.getKey(),
		place.getIdentifier(), EnumNoticeOrEventAction.REMOVE_PICTURE);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<NoticeAndEventActionResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				Window.alert("Unable to remove picture. Please try again later");
			    }

			    @Override
			    public void onSuccess(
				    NoticeAndEventActionResult result) {
				ApplicationContext
					.getInstance()
					.getPlaceController()
					.goTo(new PostNoticeOrEventChurchAppPlace(
						place.getKey(), place
							.getIdentifier()));
			    }
			});

    }

    @Override
    public void refresh() {
	ApplicationContext
		.getInstance()
		.getPlaceController()
		.goTo(new PostNoticeOrEventChurchAppPlace(place.getKey(), place
			.getIdentifier()));
    }

}
