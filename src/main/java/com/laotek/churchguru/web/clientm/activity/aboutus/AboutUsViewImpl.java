package com.laotek.churchguru.web.clientm.activity.aboutus;

import java.math.BigDecimal;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.list.widgetlist.WidgetList;
import com.laotek.churchguru.web.clientm.activity.DetailViewGwtImpl;
import com.laotek.churchguru.web.clientm.activity.facebook.FacebookPlace;
import com.laotek.churchguru.web.clientm.activity.twitter.TwitterPlace;
import com.laotek.churchguru.web.clientm.activity.youtube.YoutubePlace;
import com.laotek.churchguru.web.clientm.dispatch.MobileContext;
import com.laotek.churchguru.web.clientm.widget.HeaderLabel;

public class AboutUsViewImpl extends DetailViewGwtImpl implements AboutUsView {
    private Presenter presenter;

    private FlowPanel formContainer = new FlowPanel();
    private SimplePanel body = new SimplePanel();

    private HTML aboutPastorMessage = new HTML();
    private HTML serviceTimesHtml = new HTML("Every Sunday 10am to 12:30pm");
    private HTML locationHtml = new HTML(
	    "THE DISCOVERY CENTRE<br>Jenkins Lane off North Circular Road, Barking, Essex IG11 0AD.");
    private HTML aboutUsDetailsHtml = new HTML(
	    "Imagine a church that is influential in the society with the prayer power to change the spiritual climate. "
		    + "A church with a prophetic vision for its generation and a voice which reverberates far beyond the confines of its four walls."
		    + "A church where purpose is discovered, vision is defined, lives are changed, families are built up and leaders are trained. "
		    + "Where young professionals are becoming urban missionaries in their careers and businesses. "
		    + "A church that restores hope to the hurting strengthens the weary. "
		    + "One with a message that is relevant to the needs of its people.");
    private Anchor websiteAnchor = new Anchor();

    private HeaderLabel headerLabel = new HeaderLabel("About Us");

    private Image facebookImg = new Image("/images/app/facebook.png");
    private Image twitterImg = new Image("/images/app/twitter.png");
    private Image youtubeImg = new Image("/images/app/youtube.png");

    private HorizontalPanel socialMediaPanel = new HorizontalPanel();

    public AboutUsViewImpl() {

	scrollPanel.setScrollingEnabledX(false);
	scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid2x());

	addChurchPicture(formContainer);

	addAboutUs(formContainer);

	formContainer.add(new HTML("&nbsp;"));

	addAboutPastorPicture(formContainer);

	addAboutPastorMessage(formContainer);

	addWebsite(formContainer);

	addSocialMedia(formContainer);

	addLocation(formContainer);

	addServiceTimes(formContainer);

	websiteAnchor.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		Window.Location.replace(websiteAnchor.getText());
	    }
	});

    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void showForm(String orgName, String aboutUs, String aboutPastor, String fullAddress, String serviceTimes,
	    String websiteUrl, boolean isFacebook, boolean isTwitter, boolean isYoutube, String googleApiKey,
	    final BigDecimal lati, final BigDecimal longi) {

	headerLabel.setText("About " + orgName);
	aboutUsDetailsHtml.setHTML(aboutUs);
	aboutPastorMessage.setHTML(aboutPastor);
	serviceTimesHtml.setHTML(serviceTimes);
	locationHtml.setHTML(fullAddress);
	websiteAnchor.setText(websiteUrl);

	socialMediaPanel.clear();
	if (isFacebook) {
	    socialMediaPanel.add(facebookImg);
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	}
	if (isTwitter) {
	    socialMediaPanel.add(twitterImg);
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	}
	if (isYoutube) {
	    socialMediaPanel.add(youtubeImg);
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	    socialMediaPanel.add(new HTML("&nbsp;"));
	}

	scrollPanel.setWidget(formContainer);

	ScriptInjector.fromUrl("https://maps.googleapis.com/maps/api/js?key=" + googleApiKey)
		.setWindow(ScriptInjector.TOP_WINDOW).setCallback(new Callback<Void, Exception>() {

		    @Override
		    public void onSuccess(Void result) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			    @Override
			    public void execute() {
				log("inside onSuccess");
				body.setWidth("300px");
				body.setHeight("300px");
				if (lati != null && longi != null) {
				    initMap(body.getElement(), lati.doubleValue(), longi.doubleValue());
				}
				scrollPanel.refresh();
				scrollPanel.scrollTo(0, 0);
			    }
			});
		    }

		    @Override
		    public void onFailure(Exception reason) {
			log("exception loading the twitter widget javascript: " + reason);
		    }
		}).inject();

	// handle on load
	handleOnLoad(formContainer.getElement());

    }

    @Override
    public void goTo(String approvalUrl) {
	Window.Location.replace(approvalUrl);
    }

    private void addChurchPicture(FlowPanel container) {
	Image givePic = new Image("/uploadedphotos/photos/org/aboutus");
	givePic.setWidth("100%");
	// givePic.setHeight("100px");
	container.add(givePic);
	container.add(new HTML("&nbsp;"));
    }

    private void addAboutUs(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(headerLabel);
	aboutUsDetailsHtml.setStylePrimaryName("normalLabel");
	widget.add(aboutUsDetailsHtml);
	container.add(widget);
    }

    private void addAboutPastorPicture(FlowPanel container) {
	Image pic = new Image("/uploadedphotos/photos/org/aboutpastor");
	pic.setWidth("100%");
	container.add(pic);
	container.add(new HTML("&nbsp;"));
    }

    private void addAboutPastorMessage(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("The Pastor"));
	aboutUsDetailsHtml.setStylePrimaryName("normalLabel");
	widget.add(aboutPastorMessage);
	container.add(widget);
    }

    private void addServiceTimes(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Service times"));
	serviceTimesHtml.setStylePrimaryName("normalLabel");
	widget.add(serviceTimesHtml);
	container.add(widget);
    }

    private void addLocation(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Location"));
	locationHtml.setStylePrimaryName("normalLabel");
	widget.add(locationHtml);
	container.add(widget);
	container.add(body);
    }

    private void addWebsite(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Website"));
	widget.add(websiteAnchor);
	container.add(widget);
    }

    private void addSocialMedia(FlowPanel container) {
	WidgetList widget = new WidgetList();
	widget.setHeader(new HeaderLabel("Social Media"));
	widget.add(socialMediaPanel);

	facebookImg.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		MobileContext.getInstance().getPlaceController().goTo(new FacebookPlace("youtube"));
	    }
	});

	twitterImg.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		MobileContext.getInstance().getPlaceController().goTo(new TwitterPlace("twitter"));
	    }
	});

	youtubeImg.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		MobileContext.getInstance().getPlaceController().goTo(new YoutubePlace("youtube"));
	    }
	});
	container.add(widget);
    }

    public void refreshPull() {
	scrollPanel.scrollTo(0, 0);
	scrollPanel.refresh();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.clientm.activity.aboutus.AboutUsViewImpl::refreshPull()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

    private static native void log(final String message)/*-{
							console.log(message);
							}-*/;

    // var uluru = {lat: 51.526241, lng: 0.079905}
    public static native void initMap(Element data, double lati, double longi) /*-{	
									       var uluru = {lat: lati, lng: longi}					     
									       var map = new $wnd.google.maps.Map(data, {
									       center: uluru,
									       zoom: 12
									       });
									       var marker = new $wnd.google.maps.Marker({
									       position: uluru,
									       map: map
									       });						    
									       }-*/;

}
