package com.laotek.churchguru.web.clientm.activity.message.titles;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.list.celllist.CellSelectedHandler;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventPlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class NoticeAndEventTitlesActivity extends DetailActivity implements
	NoticeAndEventTitlesView.Presenter {

    private final MobileFactory mobileFactory;

    private List<NoticeAndEventDto> messages;

    public static int MESSAGE_INDEX = 0;// initialised from MobileHomeActivity

    private int counter = 5;

    private NoticeAndEventTitlesView view;

    public NoticeAndEventTitlesActivity(NoticeAndEventTitlesPlace place,
	    MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);

	view = mobileFactory.getNewsTitlesView();

	view.setPresenter(this);

	panel.setWidget(view);

	initIndexAndMessages();

	view.getHeader().setText("Notices and Events");
    }

    private void getMessages(
	    AsyncCallback<GetNoticeAndEventTitlesResult> callback) {
	GetNoticeAndEventTitlesAction action = new GetNoticeAndEventTitlesAction(
		MESSAGE_INDEX, counter);
	MobileContext.getInstance().getDispatchClient()
		.execute(action, callback);
    }

    private void initIndexAndMessages() {

	addHandlerRegistration(view.getCellSelectedHandler()
		.addCellSelectedHandler(new CellSelectedHandler() {

		    @Override
		    public void onCellSelected(CellSelectedEvent event) {
			int index = event.getIndex();
			NoticeAndEventDto title = messages.get(index);

			MobileContext
				.getInstance()
				.getClientFactory()
				.getPlaceController()
				.goTo(new SingleNoticeAndEventPlace(String
					.valueOf(title.getId())));
		    }
		}));

	GetNoticeAndEventTitlesAction action = new GetNoticeAndEventTitlesAction(
		MESSAGE_INDEX, counter);
	MobileContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<GetNoticeAndEventTitlesResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
			    }

			    @Override
			    public void onSuccess(
				    GetNoticeAndEventTitlesResult result) {
				messages = result.getMessages();
				view.getHeader().setText("Notices and Events");
				view.render(messages);
				manageNavButtons();
				view.refresh();
			    }
			});
    }

    @Override
    public void goHome() {
	mobileFactory.getPlaceController().goTo(new MobileHomePlace(""));
    }

    @Override
    public void getNewer() {
	if (MESSAGE_INDEX - counter >= 0) {
	    MESSAGE_INDEX = MESSAGE_INDEX - counter;
	    getMessages(new AsyncCallback<GetNoticeAndEventTitlesResult>() {
		@Override
		public void onFailure(Throwable throwable) {
		}

		@Override
		public void onSuccess(GetNoticeAndEventTitlesResult result) {
		    view.render(result.getMessages());
		    manageNavButtons();
		    view.refresh();
		}

	    });
	} else {
	    view.displayError();
	    view.refresh();
	}
    }

    @Override
    public void getOlder() {
	MESSAGE_INDEX = MESSAGE_INDEX + counter;
	getMessages(new AsyncCallback<GetNoticeAndEventTitlesResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetNoticeAndEventTitlesResult result) {
		if (result.getMessages().size() > 0) {
		    view.render(result.getMessages());
		    manageNavButtons();
		    view.refresh();
		} else {
		    MESSAGE_INDEX = MESSAGE_INDEX - counter;
		    view.displayError();
		    view.refresh();
		}
	    }
	});

    }

    private void manageNavButtons() {
	boolean previous = false;
	boolean next = false;

	if (messages != null && counter == messages.size()) {
	    previous = true;
	    next = true;

	} else if (MESSAGE_INDEX < counter) {
	    previous = false;
	    next = true;

	} else if (MESSAGE_INDEX >= counter) {
	    previous = true;
	    next = false;
	}
	view.displayNavigationButtons(previous, next);
    }

}
