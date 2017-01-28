package com.laotek.churchguru.web.clientm.activity.pastorsdesk;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class PastorDeskActivity extends DetailActivity implements
	PastorDeskView.Presenter {

    private final MobileFactory mobileFactory;

    private PastorDeskView view;

    public PastorDeskActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getPastorDeskView();
	view.setPresenter(this);
	view.getHeader().setText("From the Pastor's Desk");
	panel.setWidget(view);
	getPastorMessage(view);
    }

    private void getPastorMessage(final PastorDeskView view) {
	PastorDeskAction action = new PastorDeskAction();
	MobileContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<PastorDeskResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
		    }

		    @Override
		    public void onSuccess(PastorDeskResult result) {
			view.showForm(result.getMessage());
		    }
		});

    }

}
