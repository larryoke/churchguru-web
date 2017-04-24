package com.laotek.churchguru.web.clientm.activity.pastorsdesk;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class PastorDeskViewImpl extends DetailViewGwtImpl implements PastorDeskView {
    private FlowPanel formContainer = new FlowPanel();

    public PastorDeskViewImpl() {

	HTML html = new HTML("Loading...");
	html.getElement().setAttribute("style", "text-align: center; padding: 20px;");
	html.setHeight("25px");

	scrollPanel.add(html);
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public void showForm(String message) {

	formContainer.clear();
	WidgetList widget = new WidgetList();
	HTML html = new HTML(message);
	html.setStylePrimaryName("normalLabel");
	Image pastorsDeskPic = new Image("/uploadedphotos/photos/org/pastordesk");
	pastorsDeskPic.setWidth("100%");
	// pastorsDeskPic.setHeight("200px");
	formContainer.add(pastorsDeskPic);
	formContainer.add(new HTML("&nbsp;"));
	widget.setHeader(new HeaderLabel("Pastor's message"));
	widget.add(html);
	formContainer.add(widget);
	scrollPanel.setWidget(formContainer);
	handleOnLoad(formContainer.getElement());
    }

    private void refreshPull() {
	scrollPanel.scrollTo(0, 0);
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.pastorsdesk.PastorDeskViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;
}
