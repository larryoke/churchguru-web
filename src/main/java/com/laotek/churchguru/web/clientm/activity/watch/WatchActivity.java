package com.laotek.churchguru.web.clientm.activity.watch;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class WatchActivity extends DetailActivity implements
	WatchView.Presenter {

    private final MobileFactory mobileFactory;

    private WatchView view;

    public WatchActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getWatchView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getWatchView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("Watch Now");
	panel.setWidget(view);
    }

}
