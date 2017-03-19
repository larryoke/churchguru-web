package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;
import com.laotek.churchguru.web.client.widget.MyRichTextToolbar;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.SelectItem;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

public class PostNoticeOrEventChurchAppViewImpl implements PostNoticeOrEventChurchAppView {

    private static final String TABLES_WIDTH = "100%";

    private Presenter presenter;

    private FlexTable layout = new FlexTable();

    private FlexTable mainBody = new FlexTable();

    private RichTextArea richTextArea = new RichTextArea();
    private DateBox dateBox = new DateBox();
    private TextBox subjectBox = new TextBox();
    private SelectItem eventTimeSelect = new SelectItem();

    private Button saveAsDraftButton = new Button("Save as draft");
    private Button submitButton = new Button("Preview Message >>");
    private Button deleteButton = new Button("Delete Message");

    private SingleUploader pictureUploader;

    private Anchor removePictureAnchor = new Anchor("Remove Picture");

    private MyRichTextToolbar richTextToolbar = new MyRichTextToolbar(richTextArea);

    private HTML richTextHTML = new HTML();

    private HTML pageTitle = new HTML("<h2>Post Notice or Event</h2>");

    private Timer updateTimer;

    public static int MESSAGE_KEY;
    public static String MESSAGE_IDENTIFIER;
    public static boolean HAS_PICTURE;

    public PostNoticeOrEventChurchAppViewImpl() {

	saveAsDraftButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		validateSaveData(false);
	    }
	});
	submitButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		validateSaveData(true);
	    }
	});

	deleteButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (Window.confirm("Are you sure you want to delete this message? '" + subjectBox.getText() + "'")) {
		    presenter.deleteMessage();
		}
	    }
	});

	pictureUploader = new SingleUploader(FileInputType.CUSTOM.with(new InsertAnchor()));
	pictureUploader.setValidExtensions(new String[] { "png", "jpg", "gif" });
	pictureUploader.setAutoSubmit(true);
	pictureUploader.addOnFinishUploadHandler(new IUploader.OnFinishUploaderHandler() {
	    public void onFinish(IUploader uploader) {
		if (uploader.getStatus() == Status.SUCCESS) {
		    UploadedInfo info = uploader.getServerInfo();
		    presenter.refresh();
		}
	    }
	});

	pictureUploader.addOnStartUploadHandler(new IUploader.OnStartUploaderHandler() {
	    @Override
	    public void onStart(IUploader uploader) {

		String subject = subjectBox.getText();
		String message = richTextArea.getHTML();
		Date eventDate = dateBox.getValue();
		EventTime eventTime = getCurrentEventTime();
		presenter.saveAsDraft(subject, message, eventDate, eventTime, false);
		setServletPath();
	    }
	});

	removePictureAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (Window.confirm("Are you sure you want to remove the current picture?")) {
		    presenter.removePicture();
		}
	    }
	});
    }

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/sendMail.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(pageTitle);

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidth("100%");
	layout.setBorderWidth(0);
	layout.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	layout.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	initNoticesAndEventsPostMessage(0, mainBody);
	layout.setWidget(2, 0, new RoundedCornerPanel(mainBody));
	layout.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	richTextHTML.setStylePrimaryName("richTextHTML");
	layout.setWidget(3, 0, richTextHTML);

	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init(NoticeOrEventDto dto) {
	subjectBox.setText(dto.getSubject());
	MESSAGE_KEY = (int) dto.getId();
	MESSAGE_IDENTIFIER = dto.getIdentifier();
	HAS_PICTURE = dto.hasPicture();
	richTextArea.setHTML(dto.getBody());
	HorizontalPanel toolsBar = richTextToolbar.getToolsPanel();

	dateBox.setValue(dto.getEventDate());

	eventTimeSelect.clear();
	eventTimeSelect.addItem("");
	for (EventTime eventTime : EventTime.values()) {
	    if (eventTime.equals(dto.getEventTime())) {
		eventTimeSelect.addItem(eventTime.getDesc(), eventTime.name(), true);
	    } else {
		eventTimeSelect.addItem(eventTime.getDesc(), eventTime.name());
	    }
	}

	if (BrowseMessagesType.DRAFT.equals(dto.getMessageType())
		&& (dto.getBody() == null || (dto.getBody() != null && dto.getBody().trim().equals("")))) {
	    pageTitle.setHTML("<h2>Post new notice or event</h2>");

	} else if (BrowseMessagesType.DRAFT.equals(dto.getMessageType())) {
	    pageTitle.setHTML("<h2>Edit and post draft notice or event</h2>");

	} else if (BrowseMessagesType.POSTED.equals(dto.getMessageType())) {
	    pageTitle.setHTML("<h2>Edit and repost already posted notice or event</h2>");
	}

	toolsBar.add(new HTML("&nbsp;"));
	toolsBar.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
	if (dto.hasPicture()) {
	    toolsBar.remove(pictureUploader);
	    toolsBar.add(removePictureAnchor);
	} else {
	    toolsBar.remove(removePictureAnchor);
	    toolsBar.add(pictureUploader);
	}
	handleOnLoad(richTextArea.getElement());
    }

    private void startTextAreaValidation() {
	updateTimer = new Timer() {
	    public void run() {
		validateTextArea();
	    }
	};
	updateTimer.scheduleRepeating(3000);
    }

    @Override
    public void stopTextAreaValidation() {
	if (updateTimer != null) {
	    updateTimer.cancel();
	}
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

    private void initNoticesAndEventsPostMessage(int row, FlexTable panel) {

	initMessageSubjectPanel(++row, panel);

	initMessageDatePickerPanel(++row, panel);

	initMessageEventTime(++row, panel);

	initTextArea(++row, panel);

	buttonPanel(++row, panel, saveAsDraftButton, submitButton, deleteButton);

    }

    private void validateSaveData(boolean preview) {
	String subject = subjectBox.getText();
	String message = richTextArea.getHTML();
	Date eventDate = dateBox.getValue();

	EventTime eventTime = getCurrentEventTime();

	if ("".equals(subject) || subject == null) {
	    Window.alert("The subject line cannot be empty");

	    // } else if (eventDate == null) {
	    // Window.alert("Please select a date and time for this notice or
	    // event");

	} else if ("".equals(message) || message == null) {
	    Window.alert("Please provide the event or notice message to post");

	} else if (preview) {
	    presenter.previewNoticeOrEvent(subject, message, eventDate, eventTime);
	} else {
	    presenter.saveAsDraft(subject, message, eventDate, eventTime, true);
	}
    }

    private EventTime getCurrentEventTime() {
	String eventTimeName = eventTimeSelect.getValue();
	EventTime eventTime = null;
	if (eventTimeName != null && !eventTimeName.equals("")) {
	    eventTime = EventTime.valueOf(eventTimeName);
	}
	return eventTime;
    }

    private void initTextArea(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Post Message: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	VerticalPanel richToolAndArea = new VerticalPanel();

	richTextArea.setWidth("100%");
	richTextArea.setHeight("200px");
	richTextArea.setStylePrimaryName("newsletterTextarea");
	richToolAndArea.add(richTextToolbar);
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

    private void initMessageDatePickerPanel(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Event or Notice Date: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);
	DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	dateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));
	// dateBox.setYearAndMonthDropdownVisible(true);
	// dateBox.setYearArrowsVisible(true);
	panel.setWidget(row, 1, dateBox);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void initMessageEventTime(int row, FlexTable panel) {
	panel.setWidget(row, 0, new HTML("Event or Notice Time: "));
	panel.getFlexCellFormatter().setVerticalAlignment(row, 0, HasVerticalAlignment.ALIGN_TOP);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_RIGHT);

	panel.setWidget(row, 1, eventTimeSelect);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    private void buttonPanel(int row, FlexTable panel, Button saveAsDraftButton, Button submitButton,
	    Button deleteButton) {
	HorizontalPanel butPanel = new HorizontalPanel();
	butPanel.add(saveAsDraftButton);
	butPanel.add(new HTML("&nbsp;"));
	butPanel.add(submitButton);
	butPanel.add(new HTML("&nbsp;"));
	butPanel.add(deleteButton);
	panel.setWidget(row, 1, butPanel);
	panel.getFlexCellFormatter().setVerticalAlignment(row, 1, HasVerticalAlignment.ALIGN_MIDDLE);
	panel.getFlexCellFormatter().setHorizontalAlignment(row, 1, HasHorizontalAlignment.ALIGN_LEFT);
    }

    @Override
    public void alertSavedAsDraft() {
	Window.alert("The message is saved as draft");
    }

    class InsertAnchor extends Composite implements HasClickHandlers {
	private SimplePanel widget = new SimplePanel();
	private HTML message = new HTML("<u>Insert Picture</u>");

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

    @Override
    public void topScroll() {
	Window.scrollTo(0, 0);
	// Timer scrollTimer = new Timer() {
	// @SuppressWarnings("unused")
	// public void run() {
	// int currentVerticalScrollPosition = layout.getscrollPanel
	// .getVerticalScrollPosition();
	//
	// if (currentVerticalScrollPosition > 0) {
	//
	// currentVerticalScrollPosition = scrollPanel
	// .getVerticalScrollPosition() - 15;
	//
	// scrollPanel
	// .setVerticalScrollPosition(currentVerticalScrollPosition);
	// } else {
	// cancel();
	// }
	// }
	// };
	// scrollTimer.scheduleRepeating(10);
    }

    @Override
    public void validateTextArea() {
	richTextHTML.setHTML(richTextArea.getHTML());
	int offsetHeight = richTextHTML.getOffsetHeight();
	if (offsetHeight > 200) {
	    richTextArea.setHeight(offsetHeight + "px");
	} else {
	    richTextArea.setHeight("200px");
	}

	if (richTextArea.getHTML() != null && !richTextArea.getHTML().trim().equals("")) {
	    submitButton.setEnabled(true);
	} else {
	    submitButton.setEnabled(false);
	}
    }

    private void onFinishLoading() {
	startTextAreaValidation();
    }

    private native void handleOnLoad(JavaScriptObject jso) /*-{
							   
							   var instance=this;
							   
							   var func = function() {
							   
							   instance.@com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.PostNoticeOrEventChurchAppViewImpl::onFinishLoading()();
							   
							   };
							   
							   jso.addEventListener("load", func, true);
							   
							   }-*/;

    @Override
    public void setServletPath() {
	pictureUploader.setServletPath(
		"servlet.uploadNoticeOrEventPhotoServlet?key=" + MESSAGE_KEY + "&identifier=" + MESSAGE_IDENTIFIER);
    }
}
