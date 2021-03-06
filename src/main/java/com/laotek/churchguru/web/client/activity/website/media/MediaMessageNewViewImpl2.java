package com.laotek.churchguru.web.client.activity.website.media;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.laotek.churchguru.model.shared.enums.MediaMessageStatus;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageAction;
import com.laotek.churchguru.web.client.widget.FullnameItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public class MediaMessageNewViewImpl2 extends BaseViewImpl implements MediaMessageNewView {

    private static final String ADD_NEW_CATEGORY = "Add New Category";
    private static final String ADD_NEW_SPEAKER = "Add New Speaker";

    private Presenter presenter;

    private HTML messageTitle = new HTML();

    private Map<String, String> speakerImageURLs = new HashMap<String, String>();

    private SelectItem speakerSelect = new SelectItem("Speaker", true);

    private FlexTable speakerPanel = new FlexTable();

    private SelectItem categorySelect = new SelectItem("Category", true);

    private SelectItem publishSelect = new SelectItem("Publish", true);

    private SelectItem chargePerMessageSelect = new SelectItem("Charge Per Message", true);

    private TextItem publishStatus = new TextItem("Publish Status", false);

    private TextItem location = new TextItem("Location", false);

    private Image descImage = new Image();

    private TextItem categoryName = new TextItem("Category Name", true);

    private DatePicker messageDatePicker = new DatePicker();

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel errorMessageAndMainPanel = new VerticalPanel();

    private TextArea descArea = new TextArea();

    private Anchor goToPlay = new Anchor("Go to play");

    private HTML youHaveErrorsMessage = new HTML("<h3>You have errors</h3>");

    private HTML pleasePickADate = new HTML("<h4>Please pick a date the message was delivered</h4>");

    private String messageIdentifier;

    private FullnameItem newSpeakerFullnameItem = new FullnameItem(true);

    private TextArea speakerDescArea = new TextArea();

    private MediaFiles mediaFiles = MediaFiles.getInstance();

    private VerticalPanel newSpeakerPanel = new VerticalPanel();

    private static String mediaUrl;

    public MediaMessageNewViewImpl2() {
	descImage.setWidth("200px");
	youHaveErrorsMessage.setStylePrimaryName("errorMessage");
	pleasePickADate.setStylePrimaryName("errorMessage");
	publishStatus.disable();
	goToPlay.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (mediaUrl != null) {
		    presenter.gotoPlayMedia(messageIdentifier);
		} else {
		    Window.alert("No uploaded media found");
		}
	    }
	});
    }

    @Override
    public Widget asWidget() {

	mainPanel.setBorderWidth(0);

	mainPanel.setWidget(0, 0, messageTitle);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(1, 0, addSpeakerPanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(2, 0, addCategoryPanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(3, 0, addDescriptionAndPhotoPanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(4, 0, addDownloadChargePanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(5, 0, addMediaUploadPanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(5, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(6, 0, addPublishStatusPanel());
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);

	{
	    HorizontalPanel butPanel = new HorizontalPanel();
	    butPanel.add(createBackButton());
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(addUploadButton());
	    mainPanel.setWidget(7, 0, butPanel);
	    mainPanel.getFlexCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	errorMessageAndMainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	errorMessageAndMainPanel.add(mainPanel);

	return getMainLayout("images/app/download.png", "Manage Messages", errorMessageAndMainPanel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("EStore New Message");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("Listen to
	// Messages");
    }

    @Override
    public void initWidgets() {

    }

    @Override
    public void initNewMessage(MediaMessageDto dto, List<MediaMessageSpeakerDto> speakers,
	    List<MediaMessageCategoryDto> categories, Map<String, Boolean> workersSelectedForFreeMessages) {

	messageIdentifier = dto.getIdentifier();
	mediaUrl = dto.getMediaMessageUrl();
	String mediaMessageUrl = dto.getMediaMessageUrl();
	if (mediaMessageUrl != null && mediaMessageUrl.contains("mp3")) {
	    goToPlay.setText("Play Audio");

	} else if (mediaMessageUrl != null && mediaMessageUrl.contains("mp4")) {
	    goToPlay.setText("Play Video");

	} else {
	    goToPlay.setText("No uploaded audio found");
	}

	messageTitle.setHTML("<h2>" + dto.getTitle().toUpperCase() + "</h2><br/>");

	descArea.setValue(dto.getDescription());
	location.setValue(dto.getLocation());
	if (null != dto.getDescriptionPictureURL() && !"".equals(dto.getDescriptionPictureURL())) {
	    descImage.setUrl(dto.getDescriptionPictureURL());
	}
	messageDatePicker.setValue(dto.getMessageDate());

	speakerSelect.clear();
	speakerSelect.addItem("");
	MediaMessageSpeakerDto sdto = dto.getSpeakerDto();
	for (MediaMessageSpeakerDto speaker : speakers) {
	    speakerImageURLs.put(speaker.getIdentifier(), speaker.getPictureURL());
	    boolean toBeSelected = sdto != null && speaker.getIdentifier().equals(sdto.getIdentifier());
	    speakerSelect.addItem(speaker.getFullnameDto().getFullname(), speaker.getIdentifier(), toBeSelected);
	}
	speakerSelect.addItem(ADD_NEW_SPEAKER);

	if (sdto != null && sdto.getPictureURL() != null && !"".equals(sdto.getPictureURL())) {
	    Image speakerImage = new Image(sdto.getPictureURL());
	    speakerImage.setWidth("200px");
	    speakerPanel.setWidget(2, 0, speakerImage);
	}

	categorySelect.clear();
	categorySelect.addItem("");
	MediaMessageCategoryDto cdto = dto.getCategoryDto();
	for (MediaMessageCategoryDto category : categories) {
	    if (cdto != null) {
		categorySelect.addItem(category.getName(), category.getIdentifier(),
			category.getIdentifier().equals(cdto.getIdentifier()));
	    } else {
		categorySelect.addItem(category.getName(), category.getIdentifier());
	    }
	}
	categorySelect.addItem(ADD_NEW_CATEGORY);

	chargePerMessageSelect.clear();
	chargePerMessageSelect.addItem("");
	chargePerMessageSelect.addItem("0 point per message", "0", dto.getSalePoints() == 0);
	chargePerMessageSelect.addItem("1 points per message", "1", dto.getSalePoints() == 1);
	chargePerMessageSelect.addItem("2 points per message", "2", dto.getSalePoints() == 2);
	chargePerMessageSelect.addItem("3 points per message", "3", dto.getSalePoints() == 3);
	chargePerMessageSelect.addItem("4 points per message", "4", dto.getSalePoints() == 4);
	chargePerMessageSelect.addItem("5 points per message", "5", dto.getSalePoints() == 5);
	chargePerMessageSelect.addItem("6 points per message", "6", dto.getSalePoints() == 6);
	chargePerMessageSelect.addItem("7 points per message", "7", dto.getSalePoints() == 7);
	chargePerMessageSelect.addItem("8 points per message", "8", dto.getSalePoints() == 8);
	chargePerMessageSelect.addItem("9 points per message", "9", dto.getSalePoints() == 9);
	chargePerMessageSelect.addItem("10 points per message", "10", dto.getSalePoints() == 10);

	publishStatus.setValue(dto.getStatus().getDesc());
	publishSelect.clear();
	publishSelect.addItem("");
	if (MediaMessageStatus.LOADED.equals(dto.getStatus())) {
	    publishSelect.addItem(MediaMessageStatus.LOADED.getDesc(), MediaMessageStatus.LOADED.name(), true);
	    publishSelect.addItem("Publish", MediaMessageStatus.PUBLISHED.name(), false);

	} else if (MediaMessageStatus.PUBLISHED.equals(dto.getStatus())) {
	    publishSelect.addItem(MediaMessageStatus.PUBLISHED.getDesc(), MediaMessageStatus.PUBLISHED.name(), true);
	    publishSelect.addItem("Unpublish", MediaMessageStatus.UNPUBLISHED.name(), false);

	} else if (MediaMessageStatus.UNPUBLISHED.equals(dto.getStatus())) {
	    publishSelect.addItem("Unpublish", MediaMessageStatus.UNPUBLISHED.name(), true);
	    publishSelect.addItem("Publish", MediaMessageStatus.PUBLISHED.name(), false);
	} else {
	    publishSelect.addItem(dto.getStatus().getDesc(), dto.getStatus().name(), true);
	}
    }

    private RoundedCornerPanel addSpeakerPanel() {
	{
	    newSpeakerPanel.clear();
	    newSpeakerPanel.add(newSpeakerFullnameItem);
	    newSpeakerPanel.add(new HTML("Brief Description"));
	    speakerDescArea.setHeight("100px");
	    newSpeakerPanel.add(speakerDescArea);
	    newSpeakerPanel.add(mediaFiles.getSpeakerUploadFile());
	    Button uploadSpeakerPic = new Button("Upload Speaker Picture");
	    uploadSpeakerPic.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    if (Window.confirm("Are you sure you want to upload a picture for the speaker?")) {
			submit("speaker");
		    }
		}
	    });
	    newSpeakerPanel.add(new HTML("&nbsp;"));
	    newSpeakerPanel.add(uploadSpeakerPic);
	}

	speakerPanel.setHTML(0, 0,
		"Please select all or choose the group of people you want to be notified of this new media");
	speakerPanel.setWidget(1, 0, new HTML("<br/>"));
	{
	    speakerSelect.addChangeHandler(new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
		    String select = speakerSelect.getValue();
		    if (ADD_NEW_SPEAKER.equals(select)) {
			Window.alert("1");
			if (speakerPanel.isCellPresent(2, 0)) {
			    Window.alert("2");
			    speakerPanel.removeCell(2, 0);
			    Window.alert("3");
			}
			speakerPanel.setWidget(4, 0, newSpeakerPanel);
		    } else {
			String url = speakerImageURLs.get(select);
			if (url != null && !url.equals("")) {
			    Image img = new Image(url);
			    img.setWidth("200px");
			    speakerPanel.setWidget(2, 0, img);
			}
			speakerPanel.removeCell(4, 0);
		    }
		}
	    });
	}
	speakerPanel.setWidget(3, 0, speakerSelect);
	return new RoundedCornerPanel("Speaker", speakerPanel);
    }

    private RoundedCornerPanel addCategoryPanel() {

	final VerticalPanel categoryPanel = new VerticalPanel();
	categoryPanel.add(
		new HTML("Please select all or choose the group of people you want to be notified of this new media"));
	categoryPanel.add(new HTML("<br/>"));
	{
	    categorySelect.addChangeHandler(new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
		    String select = categorySelect.getValue();
		    if (ADD_NEW_CATEGORY.equals(select)) {
			categoryPanel.add(categoryName);
		    } else {
			categoryPanel.remove(categoryName);
		    }
		}
	    });
	}
	categoryPanel.add(categorySelect);
	return new RoundedCornerPanel("Category", categoryPanel);
    }

    private RoundedCornerPanel addDescriptionAndPhotoPanel() {
	final VerticalPanel newDescAndPhotoPanel = new VerticalPanel();
	newDescAndPhotoPanel.add(
		new HTML("Please select all or choose the group of people you want to be notified of this new media"));
	newDescAndPhotoPanel.add(new HTML("<br/>"));

	newDescAndPhotoPanel.add(new HTML("Brief Description"));
	descArea.setHeight("60px");
	descArea.setWidth("180px");
	newDescAndPhotoPanel.add(descArea);
	newDescAndPhotoPanel.add(new HTML("<br/>"));
	newDescAndPhotoPanel.add(location);

	newDescAndPhotoPanel.add(new HTML("<br/>"));
	newDescAndPhotoPanel.add(new HTML("Date and Time"));
	messageDatePicker.setYearArrowsVisible(true);
	newDescAndPhotoPanel.add(messageDatePicker);

	newDescAndPhotoPanel.add(new HTML("<br/>"));
	newDescAndPhotoPanel.add(descImage);
	newDescAndPhotoPanel.add(mediaFiles.getDescriptionUploadFile());
	Button uploadDescPic = new Button("Upload Description Picture");
	uploadDescPic.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (mediaFiles.getDescriptionUploadFile().getFilename() != null) {
		    if (Window.confirm("Are you sure you want to upload a picture for the message?")) {
			submit("desc");
		    }
		}
	    }
	});
	newDescAndPhotoPanel.add(new HTML("&nbsp;"));
	newDescAndPhotoPanel.add(uploadDescPic);

	return new RoundedCornerPanel("Message Details", newDescAndPhotoPanel);
    }

    private RoundedCornerPanel addDownloadChargePanel() {
	VerticalPanel chargePanel = new VerticalPanel();
	chargePanel.add(
		new HTML("Please select all or choose the group of people you want to be notified of this new media"));
	chargePanel.add(new HTML("<br/>"));

	chargePanel.add(chargePerMessageSelect);
	return new RoundedCornerPanel("Sales Charge Per Message", chargePanel);
    }

    private RoundedCornerPanel addPublishStatusPanel() {
	VerticalPanel publishSelectPanel = new VerticalPanel();
	publishSelectPanel.add(new HTML(
		"This enables you to publish and unpublish the media.<br/> When you publish, you make the media publically available"));
	publishSelectPanel.add(publishStatus);
	publishSelectPanel.add(new HTML("<br/>"));

	publishSelectPanel.add(publishSelect);
	return new RoundedCornerPanel("Publish or Unpublish", publishSelectPanel);
    }

    private RoundedCornerPanel addMediaUploadPanel() {
	VerticalPanel mediaPanel = new VerticalPanel();
	mediaPanel.add(new HTML("<br/>"));
	mediaPanel.add(goToPlay);
	mediaPanel.add(new HTML("<br/>"));
	mediaPanel.add(new HTML("Please select the media you want to upload"));
	mediaPanel.add(new HTML("<br/>"));

	mediaPanel.add(mediaFiles.getMediaUploadFile());
	mediaPanel.add(new HTML("&nbsp;"));
	Button uploadMessagePic = new Button("Upload Message Media");
	uploadMessagePic.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (!"".equals(mediaFiles.getMediaUploadFile().getFilename())
			&& mediaFiles.getMediaUploadFile().getFilename() != null) {
		    if (Window.confirm("Are you sure you want to upload a media file of the message now?")) {
			submit("message");
		    }
		}
	    }
	});
	mediaPanel.add(uploadMessagePic);
	return new RoundedCornerPanel("Media Upload", mediaPanel);
    }

    private Button createBackButton() {
	Button backButton = new Button("Back");
	backButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		History.back();
	    }
	});
	return backButton;
    }

    private Button addUploadButton() {
	Button upload = new Button("Update");
	upload.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		submit(null);
	    }
	});
	return upload;
    }

    private void submit(String uploadType) {
	if (messageDatePicker.getValue() == null) {
	    errorMessageAndMainPanel.insert(youHaveErrorsMessage, 0);
	    errorMessageAndMainPanel.insert(pleasePickADate, 0);
	    ApplicationContext.getInstance().scrollSlowlyToTop();

	} else if (categorySelect.validate() && speakerSelect.validate() && chargePerMessageSelect.validate()
		&& location.validate() && publishSelect.validate()) {

	    errorMessageAndMainPanel.remove(youHaveErrorsMessage);
	    errorMessageAndMainPanel.remove(pleasePickADate);

	    SubmitMediaMessageAction action = new SubmitMediaMessageAction();
	    action.setUploadType(uploadType);

	    String speakerSelectValue = speakerSelect.getValue();
	    if (ADD_NEW_SPEAKER.equals(speakerSelectValue) && newSpeakerFullnameItem.validate()) {
		action.setNewSpeaker(newSpeakerFullnameItem.getFullnameDto());
		action.setSpeakerDesc(speakerDescArea.getValue());

	    } else {
		action.setExistingSpeaker(speakerSelectValue);
	    }

	    String categorySelectValue = categorySelect.getValue();
	    if (ADD_NEW_CATEGORY.equals(categorySelectValue) && categoryName.validate()) {
		action.setNewCategory(categoryName.getValue());
	    } else {
		action.setExistingCategory(categorySelectValue);
	    }

	    action.setMessageDate(messageDatePicker.getValue());

	    action.setSalesChargePerMessage(Integer.parseInt(chargePerMessageSelect.getValue()));
	    action.setIdentifier(messageIdentifier);
	    action.setLocation(location.getValue());
	    action.setBriefDescription(descArea.getValue());

	    MediaMessageStatus status = MediaMessageStatus.valueOf(publishSelect.getValue());
	    action.setStatus(status);

	    presenter.submit(action);
	} else {
	    errorMessageAndMainPanel.insert(youHaveErrorsMessage, 0);
	    ApplicationContext.getInstance().scrollSlowlyToTop();
	}
    }

}
