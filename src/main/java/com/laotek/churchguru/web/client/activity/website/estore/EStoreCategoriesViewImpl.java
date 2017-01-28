package com.laotek.churchguru.web.client.activity.website.estore;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.estore.EStoreCategoryDto;

public class EStoreCategoriesViewImpl implements EStoreCategoriesView {

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
	Window.setTitle("EStore Categories");
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showEstorePanel("estore");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void initCategories(List<EStoreCategoryDto> dtos) {
	// TODO Auto-generated method stub

    }

}
