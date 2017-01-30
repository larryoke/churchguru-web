package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.estore.CustomerDto;

public class ListeningCustomersViewImpl implements ListeningCustomersView {

    private Presenter presenter;

    @Override
    public Widget asWidget() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("EStore Customers");
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showWebsitePanel("EStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void init(CustomerDto dto) {
	// TODO Auto-generated method stub

    }

}
