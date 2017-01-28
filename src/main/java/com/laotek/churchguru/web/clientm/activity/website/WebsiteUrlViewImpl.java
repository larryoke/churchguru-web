package com.laotek.churchguru.web.clientm.activity.website;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;

public class WebsiteUrlViewImpl extends DetailViewGwtImpl implements
	WebsiteUrlView {
    private FlowPanel formContainer = new FlowPanel();

    private Button backButton = new Button("Back");
    private Button gotoWebsite = new Button("Go to website");

    private HorizontalPanel butPanel = new HorizontalPanel();
    private String websiteUrl;

    public String getWebsiteUrl() {
	return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
	this.websiteUrl = websiteUrl;
    }

    public WebsiteUrlViewImpl() {

	HTML html = new HTML("Loading");
	html.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");
	html.setHeight("25px");

	scrollPanel.add(html);
	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	butPanel.setWidth("100%");
	butPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

	addPicture(formContainer);

	backButton.setRound(true);
	backButton.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		History.back();
	    }
	});

	gotoWebsite.setRound(true);
	gotoWebsite.addTapHandler(new TapHandler() {
	    @Override
	    public void onTap(TapEvent event) {
		Window.Location.replace(getWebsiteUrl());
	    }
	});
	butPanel.add(backButton);
	butPanel.add(gotoWebsite);

	HTML spacer = new HTML();
	spacer.setHeight("15px");
	formContainer.add(spacer);

	HTML message = new HTML(
		"Please select <i>Go to website</i> button to continue to the website.");
	message.setHeight("25px");
	message.setWidth("100%");
	message.getElement().setAttribute("style",
		"text-align: center; padding: 20px;");

	formContainer.add(message);
	formContainer.add(butPanel);

	spacer = new HTML();
	spacer.setHeight("15px");
	formContainer.add(spacer);
    }

    @Override
    public void setPresenter(Presenter presenter) {
    }

    @Override
    public void showForm(String url) {
	setWebsiteUrl(url);
	formContainer.add(butPanel);
	scrollPanel.setWidget(formContainer);
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		scrollPanel.refresh();
	    }
	});
    }

    private void addPicture(FlowPanel container) {
	Image pic = new Image("/uploadedphotos/photos/org/give");
	pic.setWidth("100%");
	pic.setHeight("100px");
	container.add(pic);
	container.add(new HTML("&nbsp;"));
    }

}
