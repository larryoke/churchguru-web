package com.laotek.churchguru.web.clientm.activity.facebook;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class FacebookActivity extends DetailActivity implements
	FacebookView.Presenter {

    private final MobileFactory mobileFactory;

    private FacebookView view;
    private FacebookPlace place;

    public FacebookActivity(FacebookPlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getGiveView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getFacebookView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("Facebook");
	panel.setWidget(view);
    }
}
