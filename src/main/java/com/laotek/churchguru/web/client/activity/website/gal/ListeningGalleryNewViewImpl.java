package com.laotek.churchguru.web.client.activity.website.gal;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.shared.listening.GalleryDto;

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
	MainMenuContext.getInstance().showMessageMediaPanel("gallery");
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
