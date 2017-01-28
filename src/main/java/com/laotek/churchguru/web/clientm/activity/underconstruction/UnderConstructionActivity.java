package com.laotek.churchguru.web.clientm.activity.underconstruction;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class UnderConstructionActivity extends DetailActivity implements
	UnderConstructionView.Presenter {

    private final MobileFactory mobileFactory;

    private UnderConstructionView view;

    public UnderConstructionActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getUnderConstructionView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("View under construction");
	panel.setWidget(view);
    }

}
