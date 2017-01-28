package com.laotek.churchguru.web.clientm.activity.watch;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class WatchViewImpl extends DetailViewGwtImpl implements WatchView {
    private FlowPanel formContainer = new FlowPanel();

    public WatchViewImpl() {

	HTML html = new HTML("Loading...");
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

	formContainer.clear();

	Image watchPic = new Image("/uploadedphotos/photos/org/watch");
	watchPic.setWidth("100%");
	formContainer.add(watchPic);
	formContainer.add(new HTML("&nbsp;"));
	Frame frame = new Frame("https://www.youtube.com/embed/E_VofkUqL4M");
	frame.setWidth("100%");
	formContainer.add(frame);
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

							   instance.@com.laotek.churchguru.web.clientm.activity.watch.WatchViewImpl::refreshPull()();

							   };

							   jso.addEventListener("load", func, true);

							   }-*/;

}
