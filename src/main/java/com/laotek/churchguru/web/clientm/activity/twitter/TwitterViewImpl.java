package com.laotek.churchguru.web.clientm.activity.twitter;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class TwitterViewImpl extends DetailViewGwtImpl implements TwitterView {
    private Presenter presenter;

    private FlowPanel formContainer = new FlowPanel();
    private WidgetList widget = new WidgetList();
    private SimplePanel body = new SimplePanel();

    public TwitterViewImpl() {
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
	scrollPanel.setWidget(formContainer);
	addPicture(formContainer);
	addTwitter(formContainer);

	HorizontalPanel loadingPanel = new HorizontalPanel();
	loadingPanel.setWidth("100%");
	loadingPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	loadingPanel.add(new HTML("Loading..."));
	formContainer.add(loadingPanel);
    }

    private void addTwitter(FlowPanel container) {
	widget.setHeader(new HeaderLabel("Trinity Chapel Twitter Page"));
	widget.add(body);
	container.add(widget);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void showForm() {
	ScriptInjector.fromUrl("https://platform.twitter.com/widgets.js")
		.setWindow(ScriptInjector.TOP_WINDOW)
		.setCallback(new Callback<Void, Exception>() {

		    @Override
		    public void onSuccess(Void result) {
			log("inside onSuccess");
			callTimeline(body.getElement());
		    }

		    @Override
		    public void onFailure(Exception reason) {
			log("exception loading the twitter widget javascript: "
				+ reason);
		    }
		}).inject();
    }

    private void addPicture(FlowPanel container) {
	Image givePic = new Image("/uploadedphotos/photos/org/messages");
	givePic.setWidth("100%");
	givePic.setHeight("100px");
	container.add(givePic);
	container.add(new HTML("&nbsp;"));
    }

    @Override
    public void stop() {
    }

    private static native void log(final String message)/*-{
							console.log(message);
							}-*/;

    public static native void callTimeline(final Element data) /*-{
							       $wnd.twttr.widgets.createTimeline(
							       '676683842703503361',
							       data,
							       {
							       width: '450',
							       height: '700',
							       related: 'twitterdev,twitterapi'
							       }).then(function (el) {
							       console.log("Embedded a timeline.")
							       });
							       }-*/;
}
