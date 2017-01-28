package com.laotek.churchguru.web.clientm.activity.underconstruction;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.ui.client.MGWT;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class UnderConstructionViewImpl extends DetailViewGwtImpl implements
	UnderConstructionView {
    private FlowPanel formContainer = new FlowPanel();

    public UnderConstructionViewImpl() {

	HTML html = new HTML(
		"View is under construction, please try back later");
	html.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");
	html.setHeight("25px");

	scrollPanel.add(html);
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public void showForm() {
	scrollPanel.setWidget(formContainer);
    }

}
