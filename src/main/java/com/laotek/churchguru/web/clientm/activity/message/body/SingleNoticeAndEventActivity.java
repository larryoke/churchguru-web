package com.laotek.churchguru.web.clientm.activity.message.body;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class SingleNoticeAndEventActivity extends DetailActivity implements
	SingleNoticeAndEventView.Presenter {

    private final MobileFactory mobileFactory;

    private final SingleNoticeAndEventPlace place;

    public SingleNoticeAndEventActivity(SingleNoticeAndEventPlace place,
	    MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	SingleNoticeAndEventView view = mobileFactory.getSingleMessageView();
	view.setPresenter(this);
	panel.setWidget(view);
	getSingleMessage(view, Long.valueOf(place.getId()));
    }

    private void getSingleMessage(final SingleNoticeAndEventView view, long id) {
	GetSingleNoticeAndEventAction action = new GetSingleNoticeAndEventAction(
		id);
	MobileContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<GetSingleNoticeAndEventResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    GetSingleNoticeAndEventResult result) {
				view.getHeader().setText("Notices and Events");
				view.showForm(result.getTitle(),
					result.getBody(),
					result.getEventDateAndTime(),
					result.getCreatedTimeDesc());
			    }
			});

    }

}
