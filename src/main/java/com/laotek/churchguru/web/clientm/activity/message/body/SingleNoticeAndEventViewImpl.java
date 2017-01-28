package com.laotek.churchguru.web.clientm.activity.message.body;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class SingleNoticeAndEventViewImpl extends DetailViewGwtImpl implements
	SingleNoticeAndEventView {

    private FlowPanel formContainer = new FlowPanel();
    private Image img = new Image("/uploadedphotos/photos/org/messages?width="
	    + Window.getClientWidth());

    public SingleNoticeAndEventViewImpl() {

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
	img.setWidth("100%");
	// img.setHeight("100px");

    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public void showForm(String title, String message, String eventDateAndTime,
	    String createdTimeDesc) {

	formContainer.clear();
	WidgetList messagePanel = new WidgetList();
	messagePanel.setHeader(new HeaderLabel(title));
	messagePanel.setWidth("100%");

	formContainer.add(img);

	HTML html = null;

	if (!"".equals(eventDateAndTime) && eventDateAndTime != null) {
	    html = new HTML("<small>Event: " + eventDateAndTime + "</small>");
	    html.setStylePrimaryName("normalLabel");
	    messagePanel.add(html);
	}

	html = new HTML("<small>Sent: " + createdTimeDesc + "</small>");
	html.setStylePrimaryName("normalLabel");

	messagePanel.add(html);

	html = new HTML(message);
	html.setStylePrimaryName("normalLabel");

	messagePanel.add(html);

	formContainer.add(messagePanel);

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

							   instance.@com.laotek.churchguru.web.clientm.activity.message.body.SingleNoticeAndEventViewImpl::refreshPull()();

							   };

							   jso.addEventListener("load", func, true);

							   }-*/;

}
