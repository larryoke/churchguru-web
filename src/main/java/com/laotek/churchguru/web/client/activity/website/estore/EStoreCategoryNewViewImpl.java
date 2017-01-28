package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;

public class EStoreCategoryNewViewImpl implements EStoreCategoryNewView {

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
	Window.setTitle("EStore New Category");
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
    public EStoreCategoryDto add() {
	EStoreCategoryDto categoryDto = new EStoreCategoryDto();
	return categoryDto;
    }

}
