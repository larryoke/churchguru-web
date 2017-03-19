package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.widget.CheckBoxItem;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;

public class PreviewNoticeOrEventChurchAppViewImpl implements PreviewNoticeOrEventChurchAppView {

    private static final String TABLES_WIDTH = "100%";

    private Presenter presenter;

    private FlexTable layout = new FlexTable();

    private FlexTable mainBody = new FlexTable();

    private RichTextArea richTextArea = new RichTextArea();
    private CheckBoxItem notifyAppUserCheckbox = new CheckBoxItem("Causes phone to vibrate or bleep", false);
    private TextBox subjectBox = new TextBox();
    private TextBox eventNoticeDateBox = new TextBox();

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/sendMail.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>Preview Notice or Event</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	initNoticesAndEventsPostMessage(0, mainBody, "hello");
	layout.setWidget(2, 0, new RoundedCornerPanel(mainBody));
	layout.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);

	subjectBox.setReadOnly(true);
	eventNoticeDateBox.setReadOnly(true);
	richTextArea.setEnabled(false);
	notifyAppUserCheckbox.check();
	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init(NoticeOrEventDto dto) {
	subjectBox.setText(dto.getSubject());
	eventNoticeDateBox.setText(dto.getEventDateAsStr());
	richTextArea.setHTML(dto.getBody());
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showInstantMessagePanel("instantMessage");
    }

    @Override
    public void instantMessageForm() {

    }

    @Override
    public void clearInstantMessageForm() {

    }

    private void initNoticesAndEventsPostMessage(int row, FlexTable panel, String html) {

	initMessageSubjectPanel(++row, panel);

	initEventDatePanel(++row, panel);

	initTextArea(++row, panel);

	initMessageNotificationPanel(++row, panel);

	Button backButton = new Button("<< Back");
	backButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		History.back();
	    }
	});

	Button submitButton = new Button("Post Message >>");
	submitButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String subject = subjectBox.getText();
		String eventDate = eventNoticeDateBox.getText();

		String messageDesc = "Please confirm you're sure you're ready to post event or notice '" + subject
			+ "' for date: " + eventDate;

		if (Window.confirm(messageDesc)) {
		    presenter.completeNoticeOrEvent(subject, notifyAppUserCheckbox.isChecked());

		}

	    }
	});
	buttonPanel(++row, panel, backButton, submitButton);
    }

    private void initTextArea(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Post Message: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	VerticalPanel richToolAndArea = new VerticalPanel();

	richTextArea.setWidth("100%");
	richTextArea.setHeight("350px");
	richTextArea.setStylePrimaryName("newsletterTextarea");
	richToolAndArea.add(richTextArea);

	panel.setWidget(row, 1, richToolAndArea);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initMessageSubjectPanel(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Subject: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	subjectBox.setWidth("60%");
	panel.setWidget(row, 1, subjectBox);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initEventDatePanel(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Event Date: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	eventNoticeDateBox.setWidth("60%");
	panel.setWidget(row, 1, eventNoticeDateBox);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initMessageNotificationPanel(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Notify App Users Now: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	notifyAppUserCheckbox.setWidth("60%");
	panel.setWidget(row, 1, notifyAppUserCheckbox);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void buttonPanel(int row, FlexTable panel, Button back, Button submitButton) {
	panel.setWidget(row, 0, back);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	HorizontalPanel submitPanel = new HorizontalPanel();
	submitPanel.add(new HTML("&nbsp;"));
	submitPanel.add(submitButton);
	panel.setWidget(row, 1, submitPanel);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }
}
