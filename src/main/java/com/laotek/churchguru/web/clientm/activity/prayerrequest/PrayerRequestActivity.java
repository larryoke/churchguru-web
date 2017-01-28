package com.laotek.churchguru.web.clientm.activity.prayerrequest;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.widget.dialog.Dialogs;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class PrayerRequestActivity extends DetailActivity implements
	PrayerRequestView.Presenter {

    private final MobileFactory mobileFactory;

    private PrayerRequestView view;

    public PrayerRequestActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getPrayerRequestView();
	view.setPresenter(this);
	view.showForm();
	view.getHeader().setText("Prayer Request");
	panel.setWidget(view);
    }

    @Override
    public void submit(String title, String forename, String surname,
	    String mobile, String emailAddress, String message) {
	PrayerRequestAction action = new PrayerRequestAction(title, forename,
		surname, emailAddress, mobile, message);
	MobileContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<PrayerRequestResult>() {

		    @Override
		    public void onFailure(Throwable caught) {
			Dialogs.alert(
				"Prayer Request",
				"Unable to submit prayer request. Please try again later",
				null);
		    }

		    @Override
		    public void onSuccess(PrayerRequestResult result) {
			view.disableAndAlert();
		    }
		});
    }
}
