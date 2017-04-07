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
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageAction;
import com.laotek.churchguru.web.client.widget.FullnameItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.shared.listening.MediaMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;
import com.laotek.churchguru.web.shared.listening.MediaMessageSpeakerDto;

public class MediaMessageNewViewImpl extends BaseViewImpl implements MediaMessageNewView {

    private static final String ADD_NEW_CATEGORY = "Add New Category";

    private static final String ADD_NEW_SPEAKER = "Add New Speaker";

    private Presenter presenter;

    private HTML messageTitle = new HTML();

    private Image speakerImage = new Image();

    private Map<Integer, String> speakerImageURLs = new HashMap<Integer, String>();

    private SelectItem speakerSelect = new SelectItem("Speaker", true);

    private SelectItem categorySelect = new SelectItem("Category", true);

    private SelectItem chargePerMessageSelect = new SelectItem("Charge Per Message", true);

    private TextItem location = new TextItem("Location", false);

    private Image descImage = new Image();

    private TextItem categoryName = new TextItem("Category Name", true);

    private DatePicker messageDatePicker = new DatePicker();

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel errorMessageAndMainPanel = new VerticalPanel();

    private TextArea descArea = new TextArea();

    private HTML youHaveErrorsMessage = new HTML("<h3>You have errors</h3>");

    private HTML pleasePickADate = new HTML("<h4>Please pick a date the message was delivered</h4>");

    private String messageIdentifier;

    private FullnameItem newSpeakerFullnameItem = new FullnameItem(true);

    private TextArea speakerDescArea = new TextArea();

    private MediaFiles mediaFiles = MediaFiles.getInstance();

    public MediaMessageNewViewImpl() {

	youHaveErrorsMessage.setStylePrimaryName("errorMessage");
	pleasePickADate.setStylePrimaryName("errorMessage");
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

	{
	    HorizontalPanel butPanel = new HorizontalPanel();
	    butPanel.add(createBackButton());
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(addUploadButton());
	    mainPanel.setWidget(6, 0, butPanel);
	    mainPanel.getFlexCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);
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

	    if (sdto != null) {
		speakerSelect.addItem(speaker.getFullnameDto().getFullname(), speaker.getIdentifier(),
			speaker.getIdentifier().equals(sdto.getIdentifier()));
	    } else {
		int index = speakers.indexOf(speaker);
		speakerImageURLs.put(index, speaker.getPictureURL());
		speakerSelect.addItem(speaker.getFullnameDto().getFullname(), speaker.getIdentifier());
	    }
	}
	speakerSelect.addItem(ADD_NEW_SPEAKER);

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
    }

    private RoundedCornerPanel addSpeakerPanel() {
	final VerticalPanel newSpeakerPanel = new VerticalPanel();
	{
	    newSpeakerPanel.add(newSpeakerFullnameItem);
	    newSpeakerPanel.add(newSpeakerFullnameItem);
	    newSpeakerPanel.add(new HTML("Brief Description"));
	    speakerDescArea.setHeight("100px");
	    newSpeakerPanel.add(speakerDescArea);
	    newSpeakerPanel.add(mediaFiles.getSpeakerUploadFile());
	    Button uploadSpeakerPic = new Button("Upload Speaker Picture");
	    uploadSpeakerPic.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    submit("speaker");
		}
	    });
	    newSpeakerPanel.add(new HTML("&nbsp;"));
	    newSpeakerPanel.add(uploadSpeakerPic);
	}

	final VerticalPanel speakerPanel = new VerticalPanel();
	speakerPanel.add(
		new HTML("Please select all or choose the group of people you want to be notified of this new media"));
	speakerPanel.add(new HTML("<br/>"));
	{
	    speakerSelect.addChangeHandler(new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
		    String select = speakerSelect.getValue();
		    if (ADD_NEW_SPEAKER.equals(select)) {
			speakerPanel.add(newSpeakerPanel);
		    } else {
			speakerImage.setUrl(speakerImageURLs.get(select));
			speakerPanel.remove(newSpeakerPanel);
		    }
		}
	    });
	}
	speakerPanel.add(speakerImage);
	speakerPanel.add(speakerSelect);
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
		    submit("desc");
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

    private RoundedCornerPanel addMediaUploadPanel() {
	VerticalPanel mediaPanel = new VerticalPanel();
	mediaPanel.add(new HTML("Please select the media you want to upload"));
	mediaPanel.add(new HTML("<br/>"));

	mediaPanel.add(mediaFiles.getMediaUploadFile());
	mediaPanel.add(new HTML("&nbsp;"));
	Button uploadMessagePic = new Button("Upload Message Media");
	uploadMessagePic.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (mediaFiles.getMediaUploadFile() != null) {
		    submit("message");
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
	Button upload = new Button("Save");
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
		&& location.validate()) {

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

	    presenter.submit(action);
	} else {
	    errorMessageAndMainPanel.insert(youHaveErrorsMessage, 0);
	    ApplicationContext.getInstance().scrollSlowlyToTop();
	}
    }

}
