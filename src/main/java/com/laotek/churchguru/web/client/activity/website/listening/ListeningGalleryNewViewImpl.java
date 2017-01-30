package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.estore.GalleryDto;

public class ListeningGalleryNewViewImpl implements ListeningGalleryNewView {

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
	Window.setTitle("EStore New Gallery Item");
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
    public GalleryDto add() {
	// TODO Auto-generated method stub
	return null;
    }

}
