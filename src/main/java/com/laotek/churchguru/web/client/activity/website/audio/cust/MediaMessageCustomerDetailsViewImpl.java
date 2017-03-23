package com.laotek.churchguru.web.client.activity.website.audio.cust;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.CustomerDto;

public class MediaMessageCustomerDetailsViewImpl implements MediaMessageCustomerDetailsView {

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
	Window.setTitle("EStore Customer Details!");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("eStore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void init(List<CustomerDto> dto) {
	// TODO Auto-generated method stub

    }

}
