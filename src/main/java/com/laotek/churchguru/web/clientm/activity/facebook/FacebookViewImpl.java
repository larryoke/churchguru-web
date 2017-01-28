package com.laotek.churchguru.web.clientm.activity.facebook;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class FacebookViewImpl extends DetailViewGwtImpl implements FacebookView {
    private Presenter presenter;

    private FlowPanel formContainer = new FlowPanel();
    private WidgetList widget = new WidgetList();
    private SimplePanel body = new SimplePanel();

    public FacebookViewImpl() {
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());
	scrollPanel.setWidget(formContainer);
	addLocation(formContainer);

	HorizontalPanel loadingPanel = new HorizontalPanel();
	loadingPanel.setWidth("100%");
	loadingPanel
		.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	loadingPanel.add(new HTML("Loading..."));
	formContainer.add(loadingPanel);
    }

    private void addLocation(FlowPanel container) {
	widget.setHeader(new HeaderLabel("Trinity Facebook Page"));
	widget.add(body);
	container.add(widget);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void showForm() {
	ScriptInjector
		.fromUrl(
			"https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.5")
		.setWindow(ScriptInjector.TOP_WINDOW)
		.setCallback(new Callback<Void, Exception>() {

		    @Override
		    public void onSuccess(Void result) {
			log("inside onSuccess");
			body.add(new HTML(
				"<div class=\"fb-page\" "
					+ " data-href=\"https://www.facebook.com/Trinity-Chapel-935189093202052/\""
					+ " data-tabs=\"timeline\" data-small-header=\"false\""
					+ " data-adapt-container-width=\"true\""
					+ " data-hide-cover=\"false\""
					+ " data-show-facepile=\"true\"></div>"));
		    }

		    @Override
		    public void onFailure(Exception reason) {
			log("exception loading the facebook widget javascript: "
				+ reason);
		    }
		}).inject();
    }

    private static native void log(final String message)/*-{
							console.log(message);
							}-*/;

    public static native void callTimeline(final Element data) /*-{
							       $wndFB.api(
							       	"/{page-id}/feed",
							       	function (response) {
							       	if (response && !response.error) {
							       		console.log(response.error);
							              }
							            }
							        );
							       }-*/;

}
