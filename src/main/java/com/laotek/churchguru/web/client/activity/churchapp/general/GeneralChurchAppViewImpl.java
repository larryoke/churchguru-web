package com.laotek.churchguru.web.client.activity.churchapp.general;

import java.math.BigDecimal;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.model.shared.enums.sharedmob.ChurchAppTopicEnum;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageResult;
import com.laotek.churchguru.web.client.activity.churchapp.UpdateType;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.EnumNoticeOrEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventActionResult;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppPlace;
import com.laotek.churchguru.web.client.activity.media.youtube.YoutubeVideoNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessageNewPlace;
import com.laotek.churchguru.web.client.activity.website.audio.AudioMessagesPlace;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.CheckBoxItemHandler;
import com.laotek.churchguru.web.client.widget.RichTextToolbar;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.client.widget.TextLongItem;
import com.laotek.churchguru.web.shared.OrganisationDto;
import com.laotek.churchguru.web.shared.UserDto;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

public class GeneralChurchAppViewImpl implements GeneralChurchAppView {

    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "100%";

    private int currentlySelectedTab = 0;

    private Presenter presenter;

    private FlexTable layout = new FlexTable();

    private TabPanel tabPanel = new TabPanel();

    private InsertAnchor insertOrgLogoAnchor = new InsertAnchor();

    private InsertAnchor insertPastorPictureAnchor = new InsertAnchor();

    private Image logo = new Image("/uploadedphotos/photos/org/logo");

    private Image pastorPicture = new Image("/uploadedphotos/photos/org/aboutpastor");

    private SingleUploader logoUploader;

    private SingleUploader pastorPictureUploader;

    private TextBox orgName = new TextBox();
    private TextBox adminEmailAddressAddress = new TextBox();
    private TextBox prayerRequestEmailAddressAddress = new TextBox();
    private TextBox websiteUrl = new TextBox();
    private TextBox serviceTimes = new TextBox();
    private TextBox addressLine1 = new TextBox();
    private TextBox postcode = new TextBox();
    private TextBox addressLine2 = new TextBox();

    private TextBox googleApiKey = new TextBox();
    private TextBox latitude = new TextBox();
    private TextBox longitude = new TextBox();

    private GeneralChurchAppTopic churchAppHome = new GeneralChurchAppTopic("Church Mobile App Home",
	    "/uploadedphotos/photos/org/home", "servlet.uploadOrganisationChurchAppProfilePicture",
	    ChurchAppTopicEnum.HOME);

    private GeneralChurchAppTopic churchAppPastorDesk = new GeneralChurchAppTopic("Church Mobile App Pastor Desk",
	    "/uploadedphotos/photos/org/pastordesk", "servlet.uploadOrganisationPastorDeskPhoto",
	    ChurchAppTopicEnum.PASTORS_DESK);

    private GeneralChurchAppTopic churchAppPrayerRequest = new GeneralChurchAppTopic("Church Mobile App Prayer Request",
	    "/uploadedphotos/photos/org/prayerrequest", "servlet.uploadOrganisationPrayerRequestPhoto",
	    ChurchAppTopicEnum.PRAYER_REQUEST);

    private GeneralChurchAppTopic churchAppMessages = new GeneralChurchAppTopic(
	    "Send a Church Mobile Notice or Event message", "/uploadedphotos/photos/org/messages",
	    "servlet.uploadOrganisationChurchAppMessagesPic", ChurchAppTopicEnum.NOTICES_AND_EVENTS);

    private GeneralChurchAppTopic churchAppDonation = new GeneralChurchAppTopic("Church Mobile App Donation",
	    "/uploadedphotos/photos/org/give", "servlet.uploadOrganisationDonatePic", ChurchAppTopicEnum.DONATION);

    private GeneralChurchAppTopic churchAppTwitter = new GeneralChurchAppTopic("Church Mobile App Twitter Page",
	    "/uploadedphotos/photos/org/twitter", "servlet.uploadOrganisationChurchAppTwitterPic",
	    ChurchAppTopicEnum.TWITTER);

    private GeneralChurchAppTopic churchAppFacebook = new GeneralChurchAppTopic("Church Mobile App Facebook Page",
	    "/uploadedphotos/photos/org/facebook", "servlet.uploadOrganisationChurchAppFacebookPic",
	    ChurchAppTopicEnum.FACEBOOK);

    private GeneralChurchAppTopic churchAppListen = new GeneralChurchAppTopic("Church Mobile App Listen",
	    "/uploadedphotos/photos/org/listen", "servlet.uploadOrganisationChurchAppListenPic",
	    ChurchAppTopicEnum.LISTEN);

    private GeneralChurchAppTopic churchAppYoutube = new GeneralChurchAppTopic("Church Mobile App YouTube",
	    "/uploadedphotos/photos/org/youtube", "servlet.uploadOrganisationChurchAppYoutubePic",
	    ChurchAppTopicEnum.YOUTUBE);

    private GeneralChurchAppTopic churchAppAboutUs = new GeneralChurchAppTopic("Church Mobile App About Us",
	    "/uploadedphotos/photos/org/aboutus", "servlet.uploadOrganisationAboutUsPic", ChurchAppTopicEnum.ABOUT_US);

    public GeneralChurchAppViewImpl(PlaceController placeController) {

	tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {

	    @Override
	    public void onSelection(SelectionEvent<Integer> event) {
		currentlySelectedTab = event.getSelectedItem();
	    }
	});

	logoUploader = new SingleUploader(FileInputType.CUSTOM.with(insertOrgLogoAnchor));
	logoUploader.setValidExtensions(new String[] { "png", "jpg", "gif" });
	logoUploader.setAutoSubmit(true);
	logoUploader.setServletPath("servlet.uploadOrganisationLogo");
	logoUploader.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
		if (uploader.getStatus() == Status.SUCCESS) {
		    UploadedInfo info = uploader.getServerInfo();

		    // random is just to trick the browser to think url
		    // has changed, does nothing
		    logo.setUrl("/uploadedphotos/photos/org/logo?random=" + info.size);
		    Window.Location.reload();
		}
	    }
	});

	pastorPictureUploader = new SingleUploader(FileInputType.CUSTOM.with(insertPastorPictureAnchor));
	pastorPictureUploader.setValidExtensions(new String[] { "png", "jpg", "gif" });
	pastorPictureUploader.setAutoSubmit(true);
	pastorPictureUploader.setServletPath("servlet.uploadOrganisationAboutPastorPic");
	pastorPictureUploader.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
		if (uploader.getStatus() == Status.SUCCESS) {
		    UploadedInfo info = uploader.getServerInfo();

		    // random is just to trick the browser to think url
		    // has changed, does nothing
		    logo.setUrl("/uploadedphotos/photos/org/aboutpastor?random=" + info.size);
		    Window.Location.reload();
		}
	    }
	});
    }

    @Override
    public Widget asWidget() {
	log("start");
	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/website.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>Manage Mobile Application</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	layout.setWidget(1, 0, new HTML("&nbsp;"));

	log("end");
	return layout;
    }

    @Override
    public void initOrganisation(OrganisationDto dto) {
	int row = 7;
	tabPanel.setAnimationEnabled(true);
	tabPanel.clear();

	tabPanel.add(initWelcomeTopic(++row, churchAppHome, "Welcome", true), "Welcome Screen");

	tabPanel.add(initPastorDeskTopic(churchAppPastorDesk, dto.getPastorDeskChurchAppTopic(),
		dto.isPastorDeskChurchAppTopicFlag(), dto.getPastorDeskMessage()), "Pastor's Desk");

	tabPanel.add(initMessageDownloadTopic(churchAppListen, dto.getListenChurchAppTopic(),
		dto.isListenChurchAppTopicFlag()), "Downloads");

	tabPanel.add(initPrayerRequestTopic(churchAppPrayerRequest, dto.getPrayerRequestChurchAppTopic(),
		dto.isPrayerRequestChurchAppTopicFlag()), "Prayer Request");

	tabPanel.add(initNoticesAndEventsTopic(churchAppMessages, dto.getNoticesAndEventsChurchAppTopic(),
		dto.isOtherNewsChurchAppTopicFlag()), "Notices and Events");

	tabPanel.add(initDonationTopic(churchAppDonation, dto.getDonationChurchAppTopic(),
		dto.isDonationChurchAppTopicFlag()), "Give");

	RoundedCornerPanel tpanel = initTwitterTopic(churchAppTwitter, dto.getTwitterChurchAppTopic(),
		dto.isTwitterChurchAppTopicFlag(), dto.getTwitterTimelineCode());

	RoundedCornerPanel fbpanel = initFacebookTopic(churchAppFacebook, dto.getFacebookChurchAppTopic(),
		dto.isFacebookChurchAppTopicFlag(), dto.getFacebookUrl());

	RoundedCornerPanel ytbpanel = initYoutubeTopic(churchAppYoutube, dto.getYoutubeChurchAppTopic(),
		dto.isYoutubeChurchAppTopicFlag(), dto.getYoutubePlaylistUrl());

	VerticalPanel socialMediaPanel = new VerticalPanel();
	socialMediaPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	socialMediaPanel.add(tpanel);
	socialMediaPanel.add(fbpanel);
	socialMediaPanel.add(ytbpanel);

	tabPanel.add(socialMediaPanel, "Social Media");

	tabPanel.add(initAboutUsTopic(churchAppAboutUs, dto.getAboutUsChurchAppTopic(),
		dto.isAboutUsChurchAppTopicFlag(), dto.getOrgName(), dto.getAdminEmail(), dto.getPrayerRequestEmail(),
		dto.getAboutUsMessage(), dto.getAboutPastorMessage(), dto.getServiceTimes(), dto.getAddressLine1(),
		dto.getAddressLine2(), dto.getPostcode(), dto.getWebsiteUrl(), dto.getGoogleApiKey(), dto.getLatitude(),
		dto.getLongitude()), "About Us");

	tabPanel.selectTab(currentlySelectedTab);

	layout.setWidget(3, 0, tabPanel);
	layout.getCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showDashboardPanel("mobileAppOptions");
    }

    @Override
    public void init(final String tabName) {
	Window.setTitle("Manage Mobile Application");
	Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	    @Override
	    public void execute() {
		if ("welcomeScreen".equals(tabName)) {
		    tabPanel.selectTab(0);

		} else if ("pastorsDesk".equals(tabName)) {
		    tabPanel.selectTab(1);

		} else if ("prayerRequest".equals(tabName)) {
		    tabPanel.selectTab(2);

		} else if ("aboutUs".equals(tabName)) {
		    tabPanel.selectTab(3);
		}
	    }
	});
    }

    // private RoundedCornerPanel initPastorDeskTopic(OrganisationDto dto,
    // int row, final ChurchAppTopic topic, final String churchAppLabel,
    // boolean isShowLabel) {
    // richTextArea.setWidth("100%");
    // richTextArea.setStylePrimaryName("newsletterTextarea");
    // richTextArea.setHTML(dto.getPastorDeskMessage());
    //
    // VerticalPanel richToolAndArea = new VerticalPanel();
    // richToolAndArea.add(richTextToolbar);
    // richToolAndArea.add(richTextArea);
    //
    // Anchor anchor = new Anchor(churchAppLabel);
    // HorizontalPanel anchorPanel = new HorizontalPanel();
    // anchorPanel.add(new HTML("Mobile Menu Label: "));
    // anchorPanel.add(anchor);
    // anchor.addClickHandler(new ClickHandler() {
    // @Override
    // public void onClick(ClickEvent event) {
    // String value = Window.prompt("New Mobile App label:",
    // churchAppLabel);
    // if (value != null && !value.equals(churchAppLabel)) {
    // presenter.updateChurchAppLabel(
    // topic.getChurchAppTopicEnum(), value);
    // }
    // }
    // });
    //
    // CheckBoxItem cb = new CheckBoxItem("Show this label", false);
    // cb.getCheckbox().setValue(isShowLabel);
    // cb.addValueChangeHandler(new CheckBoxItemHandler() {
    // @Override
    // public void addChangeHandler(boolean value) {
    // presenter.updateChurchAppLabelShowFlag(
    // topic.getChurchAppTopicEnum(), value);
    // }
    // });
    //
    // Button submitPastorsDeskMessageButton = pastorSubmit();
    // FlexTable panel = new FlexTable();
    // panel.setWidget(0, 0, anchorPanel);
    // panel.setWidget(1, 0, cb);
    // panel.setWidget(2, 0, topic.getImage());
    // panel.setWidget(3, 0, topic.getSingleUploader());
    // panel.setWidget(4, 0, richToolAndArea);
    // panel.setWidget(5, 0, submitPastorsDeskMessageButton);
    // return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    // }

    private RoundedCornerPanel initPastorDeskTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel, String html) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	initPastorDeskPostMessage(topic, panel, html);

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initNoticesAndEventsTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	UserDto dto = UserContext.getInstance().getUserDto();

	createLink(new Image("images/app/searchResult.png"), "Posted Notices and Events", panel,
		new GetCurrentNoticesAndEventsHistoryPlace(BrowseMessagesType.POSTED.name()),
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

	createLink(new Image("images/app/searchResult.png"), "Draft Notices and Events", panel,
		new GetCurrentNoticesAndEventsHistoryPlace(BrowseMessagesType.DRAFT.name()),
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

	createPostNoticeOrEventLink(new Image("images/app/sendMail.png"), panel,
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    protected void createLink(Image image, String label, FlexTable panel, final Place place,
	    UserRoleName minimumUserRoleName, UserRoleName currentUserRoleName) {
	Anchor link = new Anchor(label);
	createLink(image, link, panel, place, minimumUserRoleName, currentUserRoleName);
    }

    private void createLink(Image image, Anchor link, FlexTable panel, final Place place,
	    UserRoleName minimumUserRoleName, UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.getCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	panel.setWidget(row, 0, image);
	panel.setWidget(row, 1, link);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(place);
	    }
	});
    }

    protected void createPostNoticeOrEventLink(Image image, FlexTable panel, UserRoleName minimumUserRoleName,
	    UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	image.setWidth("30px");
	panel.getCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	panel.setWidget(row, 0, image);
	Anchor link = new Anchor("Post new notice or event");
	panel.setWidget(row, 1, link);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = Window.prompt("Please start here by submitting the subject of new notice or event",
			"");
		if (subject != null && !subject.trim().equals("")) {
		    NoticeAndEventAction action = new NoticeAndEventAction(subject, EnumNoticeOrEventAction.CREATE);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext.getInstance().getDispatchClient().execute(action,
			    new AsyncCallback<NoticeAndEventActionResult>() {
				@Override
				public void onFailure(Throwable throwable) {
				    Window.alert("An error occured:  " + throwable.getMessage());
				}

				@Override
				public void onSuccess(NoticeAndEventActionResult result) {

				    ApplicationContext.getInstance().getPlaceController()
					    .goTo(new PostNoticeOrEventChurchAppPlace(result.getId(),
						    result.getIdentifier()));

				}
			    });
		}
	    }
	});
    }

    protected void createNewListeningMessageLink(FlexTable panel, UserRoleName minimumUserRoleName,
	    UserRoleName currentUserRoleName) {
	int row = panel.getRowCount();
	Anchor link = new Anchor("Post new audio message");
	panel.setWidget(row, 1, link);
	link.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = Window.prompt("Please provide the title of the new audio message to be uploaded", "");
		if (subject != null && !subject.trim().equals("")) {

		    CreateNewAudioMessageAction action = new CreateNewAudioMessageAction(subject);
		    UserContext.getInstance().decorateClientSessionId(action);
		    UserContext.getInstance().getDispatchClient().execute(action,
			    new AsyncCallback<CreateNewAudioMessageResult>() {
				@Override
				public void onFailure(Throwable throwable) {
				    Window.alert("A server error occured when attempting to create a new message.");
				}

				@Override
				public void onSuccess(CreateNewAudioMessageResult result) {
				    ApplicationContext.getInstance().getPlaceController()
					    .goTo(new AudioMessageNewPlace(result.getNewMessageID()));
				}
			    });
		}
	    }
	});
    }

    private RoundedCornerPanel initPrayerRequestTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initDonationTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initAboutUsTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel, String orgNameStr, String adminEmailAddressStr, String prayerRequestEmailAddressStr,
	    String aboutUsMessageStr, String aboutPastorMessageStr, String serviceTimesStr, String adressLine1Str,
	    String adressLine2Str, String postcodeStr, String websiteUrlStr, String googleApiKeyStr,
	    BigDecimal latitudeVal, BigDecimal longituteVal) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	orgName.setText(orgNameStr);
	adminEmailAddressAddress.setText(adminEmailAddressStr);
	prayerRequestEmailAddressAddress.setText(prayerRequestEmailAddressStr);
	websiteUrl.setText(websiteUrlStr);
	serviceTimes.setText(serviceTimesStr);
	addressLine1.setText(adressLine1Str);
	postcode.setText(postcodeStr);
	addressLine2.setText(adressLine2Str);

	googleApiKey.setText(googleApiKeyStr);
	if (latitudeVal != null) {
	    latitude.setText(latitudeVal.toPlainString());
	}
	if (longituteVal != null) {
	    longitude.setText(longituteVal.toPlainString());
	}

	initAboutUsPostMessage(topic, panel, aboutUsMessageStr, aboutPastorMessageStr);
	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initWelcomeTopic(int row, final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);
	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initYoutubeTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel, String youtubeUrl) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initSocialMediaURL(topic, "Youtube Playlist URL: ", youtubeUrl, panel);
	initScreenPicture(topic, panel);
	initAvailability(topic, isShowLabel, panel);

	Anchor anchor = new Anchor("Go to management");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(new YoutubeVideoNewPlace("youtube"));
	    }
	});
	gotoManagement(anchor, panel);

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initMessageDownloadTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initScreenPicture(topic, panel);

	UserDto dto = UserContext.getInstance().getUserDto();

	createLink(new Image("images/app/download.png"), "Manage Audio Message", panel, new AudioMessagesPlace("audio"),
		UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

	createNewListeningMessageLink(panel, UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private void initScreenPicture(final GeneralChurchAppTopic topic, FlexTable panel) {
	Image image = topic.getImage();
	image.setWidth("300px");
	panel.setWidget(2, 0, new HTML("Screen Picture: "));
	panel.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	panel.getFlexCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.setWidget(2, 1, image);
	panel.getFlexCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_LEFT);

	panel.setWidget(3, 1, topic.getSingleUploader());
	panel.getFlexCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initAvailability(final GeneralChurchAppTopic topic, boolean isShowLabel, FlexTable panel) {
	CheckBoxItem cb = new CheckBoxItem("Show this label", false);
	cb.getCheckbox().setValue(isShowLabel);
	cb.addValueChangeHandler(new CheckBoxItemHandler() {
	    @Override
	    public void addChangeHandler(boolean value) {
		if (Window.confirm("Are you sure you want to change the visibity now?")) {
		    presenter.updateChurchApp(topic.getChurchAppTopicEnum(), Boolean.toString(value), UpdateType.SHOW);
		}
	    }
	});

	panel.setWidget(4, 0, new HTML("Screen Availability: "));
	panel.getFlexCellFormatter().setVerticalAlignment(4, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(4, 1, cb);
	panel.getFlexCellFormatter().setVerticalAlignment(4, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(4, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private RoundedCornerPanel initTwitterTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel, String timelineCode) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initSocialMediaURL(topic, "Twitter Timelime URL: ", timelineCode, panel);
	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private RoundedCornerPanel initFacebookTopic(final GeneralChurchAppTopic topic, final String churchAppLabel,
	    boolean isShowLabel, String facebookUrl) {
	FlexTable panel = new FlexTable();
	panel.setWidth("100%");
	panel.setBorderWidth(0);
	initScreenTopicLabel(topic, churchAppLabel, panel);
	initAvailability(topic, isShowLabel, panel);
	initSocialMediaURL(topic, "Facebook Timelime URL: ", facebookUrl, panel);
	return new RoundedCornerPanel(topic.getWebsiteLabel(), panel);
    }

    private void initPastorDeskPostMessage(final GeneralChurchAppTopic topic, FlexTable panel, String html) {
	int row = 3;
	final RichTextArea richTextArea = postMessageLabelPanel(++row, panel, html);
	Button submitButton = new Button("Post Message");
	submitButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (Window.confirm("Are you ready to submit a message from pastor's desk?")) {
		    presenter.submitPastorDeskMessage(richTextArea.getHTML());
		}
	    }
	});
	buttonPanel(++row, panel, submitButton);
    }

    private void initAboutUsPostMessage(final GeneralChurchAppTopic topic, FlexTable panel, String aboutUsMessageStr,
	    String aboutPastorMessageStr) {
	int row = 4;

	HTML line = new HTML("<hr width=100%>");
	panel.setWidget(++row, 0, line);
	line = new HTML("<hr width=100%>");
	panel.setWidget(row, 1, line);

	HTML header = new HTML("<h3>Church Logo</h3>");
	panel.setWidget(++row, 0, header);

	// church org logo
	panel.setWidget(++row, 0, new HTML("Current Church Logo: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(row, 1, logo);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	panel.setWidget(++row, 1, logoUploader);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	line = new HTML("<hr width=100%>");
	panel.setWidget(++row, 0, line);
	line = new HTML("<hr width=100%>");
	panel.setWidget(row, 1, line);

	header = new HTML("<h3>About Pastor</h3>");
	panel.setWidget(++row, 0, header);

	// church org logo
	panel.setWidget(++row, 0, new HTML("Current Pastor Picture: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(row, 1, pastorPicture);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	panel.setWidget(++row, 1, pastorPictureUploader);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	line = new HTML("<hr width=100%>");
	panel.setWidget(++row, 0, line);
	line = new HTML("<hr width=100%>");
	panel.setWidget(row, 1, line);

	header = new HTML("<h3>Church Details</h3>");
	panel.setWidget(++row, 0, header);

	// church name
	panel.setWidget(++row, 0, new HTML("Church Name: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	orgName.setWidth("50%");
	panel.setWidget(row, 1, orgName);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// church admin email address
	panel.setWidget(++row, 0, new HTML("Church Admin Email Address: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	orgName.setWidth("50%");
	panel.setWidget(row, 1, adminEmailAddressAddress);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// church prayer request email address
	panel.setWidget(++row, 0, new HTML("Church Prayer Request Email Address: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	orgName.setWidth("50%");
	panel.setWidget(row, 1, prayerRequestEmailAddressAddress);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// website URL
	panel.setWidget(++row, 0, new HTML("Full Website URL: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	websiteUrl.setWidth("50%");
	panel.setWidget(row, 1, websiteUrl);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// service
	panel.setWidget(++row, 0, new HTML("Service Times: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	serviceTimes.setWidth("50%");
	panel.setWidget(row, 1, serviceTimes);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// addressline 1
	panel.setWidget(++row, 0, new HTML("Address Line 1: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	addressLine1.setWidth("50%");
	panel.setWidget(row, 1, addressLine1);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// addressline 2
	panel.setWidget(++row, 0, new HTML("Address Line 2: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	addressLine2.setWidth("50%");
	panel.setWidget(row, 1, addressLine2);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	// postcode
	panel.setWidget(++row, 0, new HTML("Postcode: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	postcode.setWidth("50%");
	panel.setWidget(row, 1, postcode);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	final RichTextArea aboutUs = postMessageLabelPanel(++row, panel, aboutUsMessageStr);

	final RichTextArea aboutPastorMessage = postPastorMessageLabelPanel(++row, panel, aboutPastorMessageStr);

	line = new HTML("<hr width=100%>");
	panel.setWidget(++row, 0, line);
	line = new HTML("<hr width=100%>");
	panel.setWidget(row, 1, line);

	header = new HTML("<h3>Google Map URL API</h3>");
	panel.setWidget(++row, 0, header);

	panel.setWidget(++row, 0, new HTML("API Key: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	googleApiKey.setWidth("50%");
	panel.setWidget(row, 1, googleApiKey);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	panel.setWidget(++row, 0, new HTML("Map Latitutde: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(row, 1, latitude);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	panel.setWidget(++row, 0, new HTML("Map Longitude: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(row, 1, longitude);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);

	line = new HTML("<hr width=100%>");
	panel.setWidget(++row, 0, line);
	line = new HTML("<hr width=100%>");
	panel.setWidget(row, 1, line);

	Button submitButton = new Button("Update church details");
	submitButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		SubmitAboutUsOrgDetailsAction action = new SubmitAboutUsOrgDetailsAction();
		action.setAboutUsMessage(aboutUs.getHTML());
		action.setAboutPastorMessage(aboutPastorMessage.getHTML());
		action.setAdressLine1(addressLine1.getText());
		action.setAdressLine2(addressLine2.getText());
		action.setPostcode(postcode.getText());
		action.setOrgName(orgName.getText());
		action.setAdminEmailAddress(adminEmailAddressAddress.getText());
		action.setPrayerRequestEmailAddress(prayerRequestEmailAddressAddress.getText());
		action.setWebsiteUrl(websiteUrl.getText());
		action.setServiceTimes(serviceTimes.getText());
		action.setGoogleApiKey(googleApiKey.getText());
		action.setLatitude(latitude.getText());
		action.setLongitude(longitude.getText());
		if (Window.confirm("Are you ready to submit and update the shown details?")) {
		    presenter.submitAboutUsOrgDetails(action);
		}
	    }
	});
	buttonPanel(++row, panel, submitButton);
    }

    private void gotoManagement(Anchor anchor, FlexTable panel) {
	int row = panel.getRowCount();
	panel.setWidget(row, 1, anchor);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private RichTextArea postMessageLabelPanel(int row, FlexTable panel, String html) {
	panel.setWidget(row, 0, new HTML("Post About Us Message: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	return textAreaPanel(row, panel, html);
    }

    private RichTextArea postPastorMessageLabelPanel(int row, FlexTable panel, String html) {
	panel.setWidget(row, 0, new HTML("Post About Pastor Message: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	return textAreaPanel(row, panel, html);
    }

    private RichTextArea textAreaPanel(int row, FlexTable panel, String html) {
	VerticalPanel richToolAndArea = new VerticalPanel();
	final RichTextArea richTextArea = new RichTextArea();
	richTextArea.setWidth("100%");
	richTextArea.setStylePrimaryName("newsletterTextarea");
	if (html != null) {
	    richTextArea.setHTML(html);
	}
	RichTextToolbar richTextToolbar = new RichTextToolbar(richTextArea);
	richToolAndArea.add(richTextToolbar);
	richToolAndArea.add(richTextArea);

	panel.setWidget(row, 1, richToolAndArea);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
	return richTextArea;
    }

    private void buttonPanel(int row, FlexTable panel, Button submitButton) {
	panel.setWidget(row, 1, submitButton);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initPostWebsiteUrl(final GeneralChurchAppTopic topic, FlexTable panel, String url) {
	int row = 4;

	panel.setWidget(row, 0, new HTML("Post Message: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	final TextBox urlBox = new TextBox();
	urlBox.setWidth("80%");
	panel.setWidget(row, 1, urlBox);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
	Button submitButton = new Button("Submit");
	submitButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String websiteUrl = urlBox.getText();
		if (!"".equals(websiteUrl) && websiteUrl != null) {
		    if (Window.confirm("Are you sure you want to submit the URL now?")) {
			presenter.submitWebsiteUrl(websiteUrl);
		    }
		} else {
		    Window.alert("URL cannot be empty");
		}
	    }
	});
	buttonPanel(++row, panel, submitButton);
    }

    private void initScreenTopicLabel(final GeneralChurchAppTopic topic, final String churchAppLabel, FlexTable panel) {
	TextItem label = new TextItem();
	label.getTextbox().setReadOnly(true);
	label.setValue(churchAppLabel);
	label.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String value = Window.prompt("New Mobile App label:", churchAppLabel);
		if (value != null && !value.equals("") && !value.equals(churchAppLabel)) {
		    presenter.updateChurchApp(topic.getChurchAppTopicEnum(), value, UpdateType.LABEL);
		}
	    }
	});

	panel.setWidget(0, 0, new HTML("Mobile Menu Label: "));
	panel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	panel.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	panel.setWidget(0, 1, label);
	panel.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
	panel.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
    }

    private void initSocialMediaURL(final GeneralChurchAppTopic topic, final String label, final String url,
	    FlexTable panel) {
	TextLongItem urlItem = new TextLongItem();
	urlItem.getTextbox().setReadOnly(true);
	urlItem.setValue(url);
	urlItem.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String value = Window.prompt(label, url);
		if (value != null && !value.equals("") && !value.equals(url)) {
		    presenter.updateChurchApp(topic.getChurchAppTopicEnum(), value, UpdateType.SOCIAL_MEDIA);
		}
	    }
	});

	panel.setWidget(1, 0, new HTML(label));
	panel.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	panel.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	panel.setWidget(1, 1, urlItem);
	panel.getFlexCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
	panel.getFlexCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
    }

    class InsertPhotoAnchor extends Composite implements HasClickHandlers {
	private SimplePanel widget = new SimplePanel();
	private HTML message = new HTML("<u>Add Church Mobile Home Picture</u>");

	public HTML getMessage() {
	    return message;
	}

	public void setMessage(HTML message) {
	    this.message = message;
	    widget.setWidget(message);
	}

	public InsertPhotoAnchor() {
	    initWidget(widget);
	    message.setStylePrimaryName("handPointer");
	    widget.setStylePrimaryName("handPointer");
	    widget.setWidget(message);
	    widget.setSize("150px", "40px");

	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
    }

    @Override
    public void instantMessageForm() {
	// TODO Auto-generated method stub

    }

    @Override
    public void clearInstantMessageForm() {
	// TODO Auto-generated method stub

    }

    class InsertAnchor extends Composite implements HasClickHandlers {
	private SimplePanel widget = new SimplePanel();
	private HTML message = new HTML("<u>Change Picture</u>");

	public HTML getMessage() {
	    return message;
	}

	public void setMessage(HTML message) {
	    this.message = message;
	    widget.setWidget(message);
	}

	public InsertAnchor() {
	    initWidget(widget);
	    message.setStylePrimaryName("handPointer");
	    widget.setStylePrimaryName("handPointer");
	    widget.setWidget(message);
	    widget.setSize("150px", "40px");

	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    return addDomHandler(handler, ClickEvent.getType());
	}
    }

    private static native void log(final String message)/*-{
							console.log('*********GeneralChurchAppViewImpl: ' + message + '**********');
							}-*/;

}
