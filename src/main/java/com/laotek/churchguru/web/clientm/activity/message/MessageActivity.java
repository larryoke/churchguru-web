package com.laotek.churchguru.web.clientm.activity.message;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class MessageActivity extends DetailActivity implements MessageView.Presenter {

    private final MobileFactory mobileFactory;

    private MessageView view;
    private MessagePlace place;

    public MessageActivity(MessagePlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getMessageView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getMessageView();
	view.setPresenter(this);
	panel.setWidget(view);

	GetMessageMobAction action = new GetMessageMobAction(place.getMessageIdentifier());
	MobileContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<GetMessageMobResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(GetMessageMobResult result) {
		if (result.getDto() != null && result.getDto().getTitle() != null) {
		    view.getHeader().setText(result.getDto().getTitle());
		    view.showForm(result.getDto());
		    view.refresh();
		}
	    }
	});
    }

}
