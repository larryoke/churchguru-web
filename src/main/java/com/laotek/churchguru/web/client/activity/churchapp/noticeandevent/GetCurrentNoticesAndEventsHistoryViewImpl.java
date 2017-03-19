package com.laotek.churchguru.web.client.activity.churchapp.noticeandevent;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.instantmessage.MessageDto;

public class GetCurrentNoticesAndEventsHistoryViewImpl implements GetCurrentNoticesAndEventsHistoryView {

    private static final int DELETE_WIDGET_ROW = 0;
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    private static final String TABLES_WIDTH = "100%";

    private Presenter presenter;

    private FlexTable layout = new FlexTable();
    private FlexTable resultTable = new FlexTable();

    private Button getMoreButton = new Button("More message");

    private int currentRow = 0;
    private int MAX_RECORD = 10;

    private HTML pageTitle = new HTML();

    public GetCurrentNoticesAndEventsHistoryViewImpl(PlaceController placeController) {
	resultTable.setWidth("400px");
	resultTable.setBorderWidth(1);
	getMoreButton.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		presenter.getNextMessages(currentRow, MAX_RECORD);
	    }
	});
    }

    @Override
    public Widget asWidget() {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/searchResult.png"));
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

	VerticalPanel resultAndButton = new VerticalPanel();
	resultAndButton.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	resultAndButton.add(resultTable);
	resultAndButton.add(new HTML("&nbsp;"));
	resultAndButton.add(getMoreButton);
	layout.setWidget(1, 0, new RoundedCornerPanel(resultAndButton));
	layout.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
	// layout
	layout.setWidget(2, 0, new HTML("&nbsp;"));

	return layout;
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showInstantMessagePanel("instantMessage");
    }

    @Override
    public void init() {
	Window.setTitle("Instant Messages");
	currentRow = 0;
	resultTable.removeAllRows();
    }

    @Override
    public void initWidgets() {
    }

    @Override
    public void add(BrowseMessagesType browseMessagesType, List<MessageDto> messages) {
	if (BrowseMessagesType.DRAFT.equals(browseMessagesType)) {
	    pageTitle.setHTML("<h2>Draft Notices and Events History</h2>");

	} else if (BrowseMessagesType.POSTED.equals(browseMessagesType)) {
	    pageTitle.setHTML("<h2>Posted Notices and Events History</h2>");
	}

	if (messages.size() == 0) {
	    if (resultTable.getRowCount() == 0) {
		resultTable.setHTML(0, 0, "There are no notices or events found");
		resultTable.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    } else {
		Window.alert("There are no notices or events found");
	    }
	    getMoreButton.setEnabled(false);
	} else {
	    getMoreButton.setEnabled(true);
	}

	for (final MessageDto dto : messages) {
	    final FlexTable rowTab = new FlexTable();
	    rowTab.setBorderWidth(0);
	    Anchor anchor = new Anchor(dto.getMessageId() + " " + dto.getTitle());
	    anchor.setTitle("Edit message");
	    anchor.addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new PostNoticeOrEventChurchAppPlace(dto.getMessageId(), dto.getIdentifier()));
		}
	    });
	    rowTab.setWidget(0, 0, anchor);

	    if (BrowseMessagesType.POSTED.equals(browseMessagesType)) {
		Image copyImg = new Image("images/app/copy.png");
		copyImg.setTitle("Duplicate message as new draft for editing");
		copyImg.setPixelSize(25, 25);
		copyImg.setStylePrimaryName("handPointer");
		copyImg.addClickHandler(new ClickHandler() {
		    @Override
		    public void onClick(ClickEvent event) {
			if (Window.confirm(
				"Are you sure you want to duplicate the posted message as a new draft copy?")) {

			    NoticeAndEventAction action = new NoticeAndEventAction(dto.getMessageId(),
				    dto.getIdentifier(), EnumNoticeOrEventAction.DUPLICATE);
			    UserContext.getInstance().decorateClientSessionId(action);
			    UserContext.getInstance().getDispatchClient().execute(action,
				    new AsyncCallback<NoticeAndEventActionResult>() {
					@Override
					public void onFailure(Throwable throwable) {
					    Window.alert("Unable to duplicate the message. Please try again later");
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
		rowTab.setWidget(DELETE_WIDGET_ROW, 1, copyImg);
	    } else {
		rowTab.setWidget(DELETE_WIDGET_ROW, 1, new HTML("&nbsp;"));
	    }

	    Image editImg = new Image("images/app/smsEdit.png");
	    editImg.setTitle("Edit message");
	    editImg.setPixelSize(25, 25);
	    editImg.setStylePrimaryName("handPointer");
	    editImg.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new PostNoticeOrEventChurchAppPlace(dto.getMessageId(), dto.getIdentifier()));
		}
	    });
	    rowTab.setWidget(DELETE_WIDGET_ROW, 2, editImg);

	    Image deleteImg = new Image("images/app/bin.png");
	    deleteImg.setTitle("Delete message");
	    deleteImg.setStylePrimaryName("handPointer");
	    deleteImg.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    if (Window.confirm(dto.getTitle() + ". Are you sure you want to delete this message?")) {
			presenter.deleteMessage(dto.getMessageId(), dto.getIdentifier(), rowTab);
		    }
		}
	    });
	    rowTab.setWidget(DELETE_WIDGET_ROW, 3, deleteImg);

	    rowTab.getFlexCellFormatter().setHorizontalAlignment(DELETE_WIDGET_ROW, 1,
		    HasHorizontalAlignment.ALIGN_RIGHT);

	    rowTab.getFlexCellFormatter().setHorizontalAlignment(DELETE_WIDGET_ROW, 2,
		    HasHorizontalAlignment.ALIGN_RIGHT);

	    rowTab.getFlexCellFormatter().setHorizontalAlignment(DELETE_WIDGET_ROW, 3,
		    HasHorizontalAlignment.ALIGN_RIGHT);

	    if (!"".equals(dto.getEventDateAsStr()) && dto.getEventDateAsStr() != null) {
		rowTab.setHTML(1, 0, "Event Date: " + dto.getEventDateAsStr());
	    }
	    rowTab.getFlexCellFormatter().setColSpan(2, 0, 4);
	    rowTab.setHTML(2, 0, dto.getBody());
	    rowTab.setHTML(3, 0, "&nbsp;<u/>");
	    rowTab.setWidth("100%");

	    resultTable.setWidget(++currentRow, 0, rowTab);
	}
    }

    @Override
    public int getCurrentIndex() {
	return currentRow;
    }

    @Override
    public int getCount() {
	return MAX_RECORD;
    }

    @Override
    public void refresh() {
    }

    @Override
    public void disableRow(FlexTable rowTab) {
	Widget widget = rowTab.getWidget(DELETE_WIDGET_ROW, 1);
	rowTab.remove(widget);
	rowTab.setWidget(DELETE_WIDGET_ROW, 1, new HTML("<i>Deleted</i>"));
    }
}
