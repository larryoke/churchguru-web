package com.laotek.churchguru.web.clientm.activity.youtube;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWT;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class YoutubeViewImpl extends DetailViewGwtImpl implements YoutubeView {
    private FlowPanel formContainer = new FlowPanel();

    public YoutubeViewImpl() {

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
    public void showForm() {

	formContainer.clear();

	Image youtubePic = new Image("/uploadedphotos/photos/org/youtube");
	youtubePic.setWidth("100%");
	formContainer.add(youtubePic);
	formContainer.add(new HTML("&nbsp;"));
	Frame frame = new Frame("https://www.youtube.com/embed/videoseries?list=PLgmRz0Ecn4AJ7z9uR7kXapP1WwdyKuXPM");
	frame.setWidth("100%");
	frame.setHeight("100%");
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
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.youtube.YoutubeViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

}
