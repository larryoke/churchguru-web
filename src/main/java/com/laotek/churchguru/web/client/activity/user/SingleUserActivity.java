package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.laotek.churchguru.web.client.ClientFactory;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.ErrorMessageDialog;

public class SingleUserActivity extends AbstractActivity implements
	SingleUserView.Presenter {

    private static int MAX_AUDITS_PER_PAGE = 10;
    private int currentAuditPageIndex = 0;

    private ClientFactory clientFactory;
    private String name;
    private SingleUserView view;
    private SingleUserPlace place;

    public SingleUserActivity(SingleUserPlace place, ClientFactory clientFactory) {
	this.place = place;
	this.clientFactory = clientFactory;
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	String userIdentifier = place.getUserIdentifier();
	view = clientFactory.getSingleUserView();
	view.setName(name);
	view.setPresenter(this);
	view.initTab();
	containerWidget.setWidget(view.asWidget());
	GetSingleUserDetailsAction action = new GetSingleUserDetailsAction(
		userIdentifier);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext
		.getInstance()
		.getDispatchClient()
		.execute(action,
			new AsyncCallback<GetSingleUserDetailsResult>() {
			    @Override
			    public void onFailure(Throwable throwable) {
				ErrorMessageDialog.getInstance().handleError(
					throwable);
			    }

			    @Override
			    public void onSuccess(
				    GetSingleUserDetailsResult result) {
				view.initUser(result.getDto());
			    }
			});
    }

    @Override
    public void goTo(Place place) {
	clientFactory.getPlaceController().goTo(place);
    }

    @Override
    public void collectAuditTrail() {
	int beginIndex = currentAuditPageIndex;
	int endIndex = currentAuditPageIndex + MAX_AUDITS_PER_PAGE;
	String userIdentifier = place.getUserIdentifier();
	GetUserAuditsAction action = new GetUserAuditsAction(userIdentifier,
		beginIndex, endIndex);
	UserContext.getInstance().decorateClientSessionId(action);
	UserContext.getInstance().getDispatchClient()
		.execute(action, new AsyncCallback<GetUserAuditsResult>() {
		    @Override
		    public void onFailure(Throwable throwable) {
			ErrorMessageDialog.getInstance().handleError(throwable);
		    }

		    @Override
		    public void onSuccess(GetUserAuditsResult result) {
			view.addUserAuditTrail(result.getDtos(), result
				.getDtos().size() == MAX_AUDITS_PER_PAGE);
			currentAuditPageIndex = currentAuditPageIndex
				+ MAX_AUDITS_PER_PAGE;
		    }
		});
    }

}
