package com.laotek.churchguru.web.clientm.activity.website;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.clientm.MobileFactory;
import com.laotek.churchguru.web.clientm.activity.DetailActivity;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;

public class WebsiteUrlActivity extends DetailActivity implements
	WebsiteUrlView.Presenter {

    private final MobileFactory mobileFactory;

    private WebsiteUrlView view;

    public WebsiteUrlActivity(MobileFactory mobileFactory) {
	super(mobileFactory.getUnderConstructionView(), "nav");
	this.mobileFactory = mobileFactory;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
	super.start(panel, eventBus);
	view = mobileFactory.getWebsiteUrlView();
	view.setPresenter(this);
	view.getHeader().setText("Church Website");
	panel.setWidget(view);
	getWebsiteUrl(view);
    }

    private void getWebsiteUrl(final WebsiteUrlView view) {
	WebsiteUrlAction action = new WebsiteUrlAction();
	MobileContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<WebsiteUrlResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
		    }

		    @Override
		    public void onSuccess(WebsiteUrlResult result) {
			view.showForm(result.getMessage());
		    }
		});

    }

}
