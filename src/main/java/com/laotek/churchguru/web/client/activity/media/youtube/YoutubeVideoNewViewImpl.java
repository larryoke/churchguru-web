package com.laotek.churchguru.web.client.activity.media.youtube;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.website.audio.BaseViewImpl;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.client.widget.TextLongItem;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

public class YoutubeVideoNewViewImpl extends BaseViewImpl implements YoutubeVideoNewView {

    private Presenter presenter;

    private HTML messageTitle = new HTML();

    private TextItem speaker = new TextItem("Speaker", true);

    private TextItem location = new TextItem("Location", false);

    private TextLongItem youtubeUrl = new TextLongItem("YouTube URL", false);

    private DatePicker messageDatePicker = new DatePicker();

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel errorMessageAndMainPanel = new VerticalPanel();

    private HTML youHaveErrorsMessage = new HTML("<h3>You have errors</h3>");

    private HTML pleasePickADate = new HTML("<h4>Please pick a date the video was recorded</h4>");

    private String messageIdentifier;

    public YoutubeVideoNewViewImpl() {
	youHaveErrorsMessage.setStylePrimaryName("errorMessage");
    }

    @Override
    public Widget asWidget() {

	mainPanel.setBorderWidth(0);

	mainPanel.setWidget(1, 0, youtubeUrl);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel.setWidget(2, 0, speaker);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);

	mainPanel.setWidget(3, 0, location);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT);

	VerticalPanel datePanel = new VerticalPanel();
	datePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	datePanel.add(pleasePickADate);
	datePanel.add(messageDatePicker);
	mainPanel.setWidget(4, 0, datePanel);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(4, 0, HasHorizontalAlignment.ALIGN_LEFT);

	errorMessageAndMainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	errorMessageAndMainPanel.add(mainPanel);

	VerticalPanel formsAndUpdateBut = new VerticalPanel();
	formsAndUpdateBut.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	formsAndUpdateBut.add(messageTitle);
	formsAndUpdateBut.add(new RoundedCornerPanel(errorMessageAndMainPanel));
	{
	    HorizontalPanel butPanel = new HorizontalPanel();
	    butPanel.add(createBackButton());
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(new HTML("&nbsp;"));
	    butPanel.add(createUpdateButton());
	    formsAndUpdateBut.add(butPanel);
	}

	return getMainLayout("images/app/youtube.png", "Embed New Youtube Video", formsAndUpdateBut);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("New Youtube Video Embed");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("New Youtube
	// Video Embed");
    }

    @Override
    public void initWidgets() {

    }

    @Override
    public void initNewMessage(YoutubeVideoDto dto) {
	messageIdentifier = dto.getIdentifier();
	messageTitle.setHTML("<h2>" + dto.getTitle().toUpperCase() + "</h2><br/>");
	youtubeUrl.setValue(dto.getYoutubeUrl());
	location.setValue(dto.getLocation());
	messageDatePicker.setValue(dto.getMessageDate());

	speaker.setValue(dto.getSpeakers());

    }

    private Button createUpdateButton() {
	Button updateButton = new Button("Update");
	updateButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (messageDatePicker.getValue() == null) {
		    errorMessageAndMainPanel.insert(youHaveErrorsMessage, 0);
		    errorMessageAndMainPanel.insert(pleasePickADate, 0);
		    ApplicationContext.getInstance().scrollSlowlyToTop();
		} else {
		    SubmitYoutubeVideoAction action = new SubmitYoutubeVideoAction(messageIdentifier,
			    youtubeUrl.getValue(), speaker.getValue(), location.getValue(),
			    messageDatePicker.getValue());
		    presenter.submit(action);
		}
	    }
	});
	return updateButton;
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
}
