package com.laotek.churchguru.web.clientm.activity.give;

import java.math.BigDecimal;
import java.util.Map;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.activity.home.MobileHomePlace;
import com.laotek.churchguru.web.clientm.activity.home.SubmitDonationDetailsAction;
import com.laotek.churchguru.web.clientm.activity.home.SubmitDonationDetailsResult;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class GiveActivity extends DetailActivity implements GiveView.Presenter {

    private final MobileFactory mobileFactory;

    private GiveView view;
    private GivePlace place;

    public GiveActivity(GivePlace place, MobileFactory mobileFactory) {
	super(mobileFactory.getGiveView(), "nav");
	this.mobileFactory = mobileFactory;
	this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getGiveView();
	view.setPresenter(this);

	if ("thanks".equals(place.getName())) {
	    if (MGWT.getOsDetection().isIOs()) {
		Window.Location.replace("trinitychapel://");
	    } else {
		view.showThanks();
	    }
	} else {
	    view.showForm();
	}
	view.getHeader().setText("Give");
	panel.setWidget(view);
    }

    @Override
    public void submit(Map<String, String> giveDetails, Map<DonationType, BigDecimal> payments) {
	SubmitDonationDetailsAction action = new SubmitDonationDetailsAction();
	action.setDetails(giveDetails);
	action.setPayments(payments);
	MobileContext.getInstance().getDispatchClient().execute(action,
		new AsyncCallback<SubmitDonationDetailsResult>() {

		    @Override
		    public void onFailure(Throwable caught) {
			view.showErrorMessage(caught.getMessage());
			view.showForm();
		    }

		    @Override
		    public void onSuccess(SubmitDonationDetailsResult result) {

			if (Window.confirm("Please confirm access to Paypal: " + result.getPaypalApprovalUrl())) {
			    view.goTo(result.getPaypalApprovalUrl());
			}

			// redirect app screen to home because paypal
			// screen is popped up
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			    @Override
			    public void execute() {
				MobileContext.getInstance().getClientFactory().getPlaceController()
					.goTo(new MobileHomePlace("home"));
			    }
			});

		    }
		});

	view.pleaseWaitLoadingPaypal();
    }
}
