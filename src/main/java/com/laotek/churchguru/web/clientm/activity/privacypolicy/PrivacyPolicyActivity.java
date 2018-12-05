package com.laotek.churchguru.web.clientm.activity.privacypolicy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;

public class PrivacyPolicyActivity extends DetailActivity implements PrivacyPolicyView.Presenter {

	private final MobileFactory mobileFactory;

	private PrivacyPolicyView view;
	private PrivacyPolicyPlace place;

	public PrivacyPolicyActivity(PrivacyPolicyPlace place, MobileFactory mobileFactory) {
		super(mobileFactory.getPrivacyPolicyView(), "nav");
		this.mobileFactory = mobileFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		super.start(panel, eventBus);
		view = mobileFactory.getPrivacyPolicyView();
		view.setPresenter(this);
		view.showForm();
		view.getHeader().setText("Privacy Policy");
		panel.setWidget(view);
	}
}
