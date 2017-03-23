package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageAction;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.FullnameItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.shared.listening.AudioMessageCategoryDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;
import com.laotek.churchguru.web.shared.listening.AudioMessagePictureDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageSpeakerDto;

import elemental.events.EventListener;
import elemental.events.MessageEvent;
import elemental.html.Worker;

public class MediaMessageNewViewImpl extends BaseViewImpl implements MediaMessageNewView {

    private static final String ADD_NEW_DESCRIPTION_PHOTO = "Add New Description Photo";

    private static final String ADD_NEW_CATEGORY = "Add New Category";

    private static final String ADD_NEW_SPEAKER = "Add New Speaker";

    private Presenter presenter;

    private HTML messageTitle = new HTML();

    private SelectItem speakerSelect = new SelectItem("Speaker", true);

    private SelectItem categorySelect = new SelectItem("Category", true);

    private SelectItem descPhotoSelect = new SelectItem("Description Photo", false);

    private SelectItem chargePerMessageSelect = new SelectItem("Charge Per Message", true);

    private TextItem location = new TextItem("Location", false);

    private CheckBoxItem notifyOnlineMembers = new CheckBoxItem("Notify online members", false);
    private CheckBoxItem notifyFullMembers = new CheckBoxItem("Notify all full Members", false);
    private CheckBoxItem notifyGuests = new CheckBoxItem("Notify guests", false);
    private CheckBoxItem notifyWorkers = new CheckBoxItem("Notify workers", false);
    private CheckBoxItem notifyNonWorkers = new CheckBoxItem("Notify non-workers", false);

    private TextItem categoryName = new TextItem("Category Name", true);

    private FileUpload mediaUpload = new FileUpload();

    private DatePicker messageDatePicker = new DatePicker();

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel errorMessageAndMainPanel = new VerticalPanel();

    private TextArea descArea = new TextArea();

    private HTML youHaveErrorsMessage = new HTML("<h3>You have errors</h3>");

    private HTML pleasePickADate = new HTML("<h4>Please pick a date the message was delivered</h4>");

    private String messageIdentifier;

    private FullnameItem newSpeakerFullnameItem = new FullnameItem(true);

    private TextArea speakerDescArea = new TextArea();

    private static elemental.html.Window window;

    private Worker worker;

    public MediaMessageNewViewImpl() {
	window = elemental.client.Browser.getWindow();
	worker = window.newWorker("/workers/submit.js");
	worker.setOnerror(new EventListener() {
	    @Override
	    public void handleEvent(elemental.events.Event evt) {
		window.alert("Error message" + evt.toString());
	    }
	});
	worker.setOnmessage(new EventListener() {
	    @Override
	    public void handleEvent(elemental.events.Event evt) {
		if (evt instanceof MessageEvent) {
		    MessageEvent event = (MessageEvent) evt;
		    Object obj = event.getData();
		    window.alert(obj.toString() + "Data");
		} else {
		    window.alert("Message not came");
		}
	    }
	});

	youHaveErrorsMessage.setStylePrimaryName("errorMessage");
	pleasePickADate.setStylePrimaryName("errorMessage");

	mediaUpload.setName("MediaUpload");
	mediaUpload.setTitle("Media Upload");
	mediaUpload.getElement().setAttribute("accept", ".png,.jpg,.gif,.jpeg");
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

	descPhotoSelect.clear();
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

	return getMainLayout("images/app/download.png", "Manage Message Downloads", errorMessageAndMainPanel);
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
    public void initNewMessage(AudioMessageDto dto, List<AudioMessageSpeakerDto> speakers,
	    List<AudioMessageCategoryDto> categories, List<AudioMessagePictureDto> pictures,
	    Map<String, Boolean> workersSelectedForFreeMessages) {
	messageIdentifier = dto.getIdentifier();
	messageTitle.setHTML("<h2>" + dto.getTitle().toUpperCase() + "</h2><br/>");

	descArea.setValue(dto.getDescription());
	location.setValue(dto.getLocation());
	messageDatePicker.setValue(dto.getMessageDate());

	speakerSelect.clear();
	speakerSelect.addItem("");
	AudioMessageSpeakerDto sdto = dto.getSpeakerDto();
	for (AudioMessageSpeakerDto speaker : speakers) {

	    if (sdto != null) {
		speakerSelect.addItem(speaker.getFullnameDto().getFullname(), speaker.getIdentifier(),
			speaker.getIdentifier().equals(sdto.getIdentifier()));
	    } else {
		speakerSelect.addItem(speaker.getFullnameDto().getFullname(), speaker.getIdentifier());
	    }
	}
	speakerSelect.addItem(ADD_NEW_SPEAKER);

	categorySelect.clear();
	categorySelect.addItem("");
	AudioMessageCategoryDto cdto = dto.getCategoryDto();
	for (AudioMessageCategoryDto category : categories) {
	    if (cdto != null) {
		categorySelect.addItem(category.getName(), category.getIdentifier(),
			category.getIdentifier().equals(cdto.getIdentifier()));
	    } else {
		categorySelect.addItem(category.getName(), category.getIdentifier());
	    }
	}
	categorySelect.addItem(ADD_NEW_CATEGORY);

	AudioMessagePictureDto pictureDto = dto.getPictureDto();
	for (AudioMessagePictureDto picture : pictures) {
	    if (pictureDto != null) {
		descPhotoSelect.addItem(picture.getName(), picture.getIdentifier(),
			picture.getIdentifier().equals(pictureDto.getIdentifier()));
	    } else {
		descPhotoSelect.addItem(picture.getName(), picture.getIdentifier());
	    }
	}

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

	    FileUpload fileUpload = new FileUpload();
	    fileUpload.setName("speakerPhoto");
	    fileUpload.setTitle("Speaker Photo");
	    newSpeakerPanel.add(fileUpload);
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
			speakerPanel.remove(newSpeakerPanel);
		    }
		}
	    });
	}
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
	newDescAndPhotoPanel.add(descPhotoSelect);

	final VerticalPanel newPhotoPanel = new VerticalPanel();
	final FileUpload fileUpload = new FileUpload();
	fileUpload.setName("descPhoto");
	fileUpload.setTitle("Description Photo");
	newPhotoPanel.add(fileUpload);

	descPhotoSelect.addItem("");
	descPhotoSelect.addItem(ADD_NEW_DESCRIPTION_PHOTO);
	descPhotoSelect.addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		String select = descPhotoSelect.getValue();
		if (ADD_NEW_DESCRIPTION_PHOTO.equals(select)) {
		    newDescAndPhotoPanel.add(newPhotoPanel);
		} else {
		    newDescAndPhotoPanel.remove(newPhotoPanel);
		}
	    }
	});

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
	mediaUpload.setName("mediaUploadFile");
	VerticalPanel mediaPanel = new VerticalPanel();
	mediaPanel.add(
		new HTML("Please select all or choose the group of people you want to be notified of this new media"));
	mediaPanel.add(new HTML("<br/>"));

	mediaPanel.add(mediaUpload);
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
	Button upload = new Button("Upload now");
	upload.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		if (messageDatePicker.getValue() == null) {
		    errorMessageAndMainPanel.insert(youHaveErrorsMessage, 0);
		    errorMessageAndMainPanel.insert(pleasePickADate, 0);
		    ApplicationContext.getInstance().scrollSlowlyToTop();

		} else if (categorySelect.validate() && speakerSelect.validate() && descPhotoSelect.validate()
			&& chargePerMessageSelect.validate() && location.validate()) {

		    errorMessageAndMainPanel.remove(youHaveErrorsMessage);
		    errorMessageAndMainPanel.remove(pleasePickADate);

		    SubmitAudioMessageAction action = new SubmitAudioMessageAction();

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
	});
	return upload;
    }

    private native void postSpeakerPhotoFile(final Element data) /*-{
								 worker = new Worker('/workers/submit.js');
								 worker.onmessage = function(e) {
								 alert(e.data);
								 }
								 worker.postMessage(data.files[0]); 
								 }-*/;

    private native void postDescPictureFile(final Element data) /*-{
								worker = new Worker('/workers/submit.js');
								worker.onmessage = function(e) {
								alert(e.data);
								}
								worker.postMessage(data.files[0]); 
								}-*/;

    private native void postMediaFile(final Element data) /*-{
							  worker = new Worker('/workers/submit.js');
							  worker.onmessage = function(e) {
							  alert(e.data);
							  }
							  worker.postMessage(data.files[0]); 
							  }-*/;

    @Override
    public void uploadPhotosByWorker() {
	postSpeakerPhotoFile(mediaUpload.getElement());
    }

    private void speakerPhotoFileUploadCallback() {
	postDescPictureFile(mediaUpload.getElement());
    }

    private void descPictureFileUploadCallback() {
	postMediaFile(mediaUpload.getElement());
    }

}
