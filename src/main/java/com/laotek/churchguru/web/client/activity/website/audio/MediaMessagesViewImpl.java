package com.laotek.churchguru.web.client.activity.website.audio;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.FullnameDto;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;

public class MediaMessagesViewImpl extends BaseViewImpl implements MediaMessagesView {

    private Presenter presenter;

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel panelRows = new VerticalPanel();

    public MediaMessagesViewImpl() {

    }

    @Override
    public Widget asWidget() {

	mainPanel.setBorderWidth(0);

	mainPanel.setWidget(0, 0, panelRows);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	Button newMessageButton = new Button("New Message");
	newMessageButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String title = Window.prompt("Please provide the title of the new message to be uploaded", "");
		if (!"".equals(title) && title != null) {
		    presenter.createMessage(title);
		}
	    }
	});
	return getMainLayout("images/app/download.png", "Manage Message Downloads",
		new RoundedCornerPanel(mainPanel, newMessageButton));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("EStore Messages");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("eStore");
    }

    @Override
    public void initWidgets() {

    }

    @Override
    public void init(List<AudioMessageDto> dtos) {
	panelRows.clear();

	HorizontalPanel hPanel = new HorizontalPanel();
	cell(hPanel, "<b>Title</b>", 200);
	cell(hPanel, "<b>Speaker</b>", 120);
	cell(hPanel, "<b>Status</b>", 60);
	cell(hPanel, "<b>Sales Points</b>", 70);
	cell(hPanel, "<b>Date</b>", 100);
	cell(hPanel, "<b>Location</b>", 100);
	panelRows.add(hPanel);

	for (final AudioMessageDto dto : dtos) {
	    hPanel = new HorizontalPanel();
	    if (dtos.indexOf(dto) % 2 == 0) {
		hPanel.setStylePrimaryName("estoreGreybg");
	    } else {
		hPanel.setStylePrimaryName("estoreWhitebg");
	    }
	    Anchor anchor = new Anchor(dto.getTitle());
	    anchor.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new MediaMessageNewPlace(dto.getIdentifier()));

		}
	    });
	    cell(hPanel, anchor, 200);

	    String speakerDetails = "";
	    if (dto.getSpeakerDto() != null && dto.getSpeakerDto().getFullnameDto() != null) {
		FullnameDto fdto = dto.getSpeakerDto().getFullnameDto();
		speakerDetails = fdto.getFullname();
	    }
	    cell(hPanel, speakerDetails, 120);
	    cell(hPanel, "New", 60);
	    cell(hPanel, dto.getSalePoints(), 70);
	    cell(hPanel, dto.getMessageDateAsString(), 100);
	    cell(hPanel, dto.getLocation(), 100);
	    panelRows.add(hPanel);
	}
    }

    private void cell(HorizontalPanel hPanel, String value, int width) {
	SimplePanel panel = new SimplePanel(new HTML(value));
	panel.setStylePrimaryName("eStoreTableCellBorder");
	panel.setWidth(new StringBuffer().append(width).append("px").toString());
	hPanel.add(panel);
    }

    private void cell(HorizontalPanel hPanel, int value, int width) {
	SimplePanel panel = new SimplePanel(new HTML(String.valueOf(value)));
	panel.setStylePrimaryName("eStoreTableCellBorder");
	panel.setWidth(new StringBuffer().append(width).append("px").toString());
	hPanel.add(panel);
    }

    private void cell(HorizontalPanel hPanel, Widget value, int width) {
	SimplePanel panel = new SimplePanel(value);
	panel.setStylePrimaryName("eStoreTableCellBorder");
	panel.setWidth(new StringBuffer().append(width).append("px").toString());
	hPanel.add(panel);
    }
}
