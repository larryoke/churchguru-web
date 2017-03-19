package com.laotek.churchguru.web.client.activity.website.audio.cust;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.CustomerDto;

public class AudioMessageCustomersViewImpl implements AudioMessageCustomersView {

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
	// MainMenuContext.getInstance().showWebsitePanel("Customer");
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
