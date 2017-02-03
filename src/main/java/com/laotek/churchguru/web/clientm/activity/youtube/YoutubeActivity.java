package com.laotek.churchguru.web.clientm.activity.youtube;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class YoutubeActivity extends DetailActivity implements YoutubeView.Presenter {

    private final MobileFactory mobileFactory;

    private YoutubeView view;

    public YoutubeActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getYoutubeView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getYoutubeView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("YouTube");
	panel.setWidget(view);
    }

}
