package com.laotek.churchguru.web.clientm.activity.aboutus;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class AboutUsActivity extends DetailActivity implements AboutUsView.Presenter {

    private final MobileFactory mobileFactory;

    private AboutUsView view;
    private AboutUsPlace place;

    public AboutUsActivity(AboutUsPlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getGiveView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getAboutUsView();
	view.setPresenter(this);
	view.getHeader().setText("About Trinity Chapel");
	panel.setWidget(view);
	getAboutUsDetails(view);
    }

    private void getAboutUsDetails(final AboutUsView view) {
	AboutUsDetailsAction action = new AboutUsDetailsAction();
	MobileContext.getInstance().getDispatchClient().execute(action, new AsyncCallback<AboutUseDetailsResult>() {
	    @Override
	    public void onFailure(Throwable throwable) {
	    }

	    @Override
	    public void onSuccess(AboutUseDetailsResult result) {
		view.showForm(result.getOrgName(), result.getAboutUsMessage(), result.getAboutPastorMessage(),
			result.getFullAddress(), result.getServiceTimes(), result.getWebsiteUrl(),
			result.getGoogleApiUrl(), result.getLatitude(), result.getLongitude());
	    }
	});

    }

}
