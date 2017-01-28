package com.laotek.churchguru.web.clientm.activity.twitter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class TwitterActivity extends DetailActivity implements
	TwitterView.Presenter {

    private final MobileFactory mobileFactory;

    private TwitterView view;
    private TwitterPlace place;

    public TwitterActivity(TwitterPlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getGiveView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getTwitterView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("Twitter");
	panel.setWidget(view);
    }

    @Override
    public void onStop() {
	view.stop();
	super.onStop();
    }
}
