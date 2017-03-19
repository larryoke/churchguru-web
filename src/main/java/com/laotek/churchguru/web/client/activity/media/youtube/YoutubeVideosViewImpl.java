package com.laotek.churchguru.web.client.activity.media.youtube;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.website.audio.BaseViewImpl;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.youtube.YoutubeVideoDto;

public class YoutubeVideosViewImpl extends BaseViewImpl implements YoutubeVideosView {

    private Presenter presenter;

    private FlexTable mainPanel = new FlexTable();

    private VerticalPanel panelRows = new VerticalPanel();

    public YoutubeVideosViewImpl() {

    }

    @Override
    public Widget asWidget() {

	mainPanel.setBorderWidth(0);

	mainPanel.setWidget(0, 0, panelRows);
	mainPanel.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	Button newMessageButton = new Button("New Youtube Video");
	newMessageButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String title = Window.prompt("Please provide the title of the new YouTube video to be embedded", "");
		if (!"".equals(title) && title != null) {
		    presenter.createMessage(title);
		}
	    }
	});
	return getMainLayout("images/app/youtube.png", "Manage YouTube Videos",
		new RoundedCornerPanel(mainPanel, newMessageButton));
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("Watch Youtube videos");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showMessageMediaPanel("youtube");
    }

    @Override
    public void initWidgets() {

    }

    @Override
    public void init(List<YoutubeVideoDto> dtos) {
	panelRows.clear();

	HorizontalPanel hPanel = new HorizontalPanel();
	cell(hPanel, "<b>Title</b>", 200);
	cell(hPanel, "<b>Video</b>", 120);
	cell(hPanel, "<b>Speaker</b>", 120);
	cell(hPanel, "<b>Status</b>", 60);
	cell(hPanel, "<b>Date</b>", 100);
	cell(hPanel, "<b>Location</b>", 100);
	panelRows.add(hPanel);

	for (final YoutubeVideoDto dto : dtos) {
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
			    .goTo(new YoutubeVideoNewPlace(dto.getIdentifier()));

		}
	    });
	    cell(hPanel, anchor, 200);

	    if (dto.getYoutubeUrl() != null && !dto.getYoutubeUrl().trim().equals("")) {
		Frame frame = new Frame(dto.getYoutubeUrl());
		frame.setWidth("120px");
		cell(hPanel, frame, 120);
	    } else {
		cell(hPanel, "", 120);
	    }

	    String speakerDetails = dto.getSpeakers();
	    cell(hPanel, speakerDetails, 120);
	    cell(hPanel, "New", 60);
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

    private void cell(HorizontalPanel hPanel, Widget value, int width) {
	SimplePanel panel = new SimplePanel(value);
	panel.setStylePrimaryName("eStoreTableCellBorder");
	panel.setWidth(new StringBuffer().append(width).append("px").toString());
	hPanel.add(panel);
    }
}
