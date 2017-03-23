package com.laotek.churchguru.web.client.activity.website.gal;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.shared.listening.GalleryDto;

public class MediaMessageGalleryViewImpl implements MediaMessageGalleryView {

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
	// TODO Auto-generated method stub

    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("gallery");
    }

    @Override
    public void initWidgets() {
	// TODO Auto-generated method stub

    }

    @Override
    public void init(List<GalleryDto> dtos) {
	// TODO Auto-generated method stub

    }

}
