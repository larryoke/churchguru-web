package com.laotek.churchguru.web.client.activity.media.watching;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.laotek.churchguru.web.client.MainMenuContext;
import com.laotek.churchguru.web.client.activity.website.audio.BaseViewImpl;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.TextItem;
import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

public class WatchingMessageNewViewImpl extends BaseViewImpl implements VideoMessageNewView {

    private Presenter presenter;

    private HTML messageTitle = new HTML();

    private TextItem speaker = new TextItem("Speaker", true);

    private TextItem location = new TextItem("Location", false);

    private TextItem youtubeUrl = new TextItem("YouTube URL", false);

    private DatePicker messageDatePicker = new DatePicker();

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel errorMessageAndMainPanel = new VerticalPanel();

    private HTML youHaveErrorsMessage = new HTML("<h3>You have errors</h3>");

    private HTML pleasePickADate = new HTML("<h4>Please pick a date the message was delivered</h4>");

    private String messageIdentifier;

    public WatchingMessageNewViewImpl() {
	youHaveErrorsMessage.setStylePrimaryName("errorMessage");
	pleasePickADate.setStylePrimaryName("errorMessage");
    }

    @Override
    public Widget asWidget() {

	mainPanel.setBorderWidth(0);

	mainPanel.setWidget(0, 0, messageTitle);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(1, 0, youtubeUrl);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(2, 0, speaker);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(6, 0, HasHorizontalAlignment.ALIGN_CENTER);

	mainPanel.setWidget(3, 0, location);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(7, 0, HasHorizontalAlignment.ALIGN_CENTER);

	errorMessageAndMainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	errorMessageAndMainPanel.add(mainPanel);

	return getMainLayout("images/app/watch_live.png", "Manage New Video Message",
		new RoundedCornerPanel(errorMessageAndMainPanel));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("New Video Message");
    }

    @Override
    public void initTab() {
	MainMenuContext.getInstance().showMessageMediaPanel("New Video Message");
    }

    @Override
    public void initWidgets() {

    }

    @Override
    public void initNewMessage(WatchingMessageDto dto) {
	messageIdentifier = dto.getIdentifier();
	messageTitle.setHTML("<h2>" + dto.getTitle().toUpperCase() + "</h2><br/>");

	location.setValue(dto.getLocation());
	messageDatePicker.setValue(dto.getMessageDate());

	speaker.setValue(dto.getSpeakers());

    }
}
