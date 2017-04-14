package com.laotek.churchguru.web.client.activity.website.media.play;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.MediaType;
import com.laotek.churchguru.web.client.activity.website.media.BaseViewImpl;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;

public class MediaMessagePlayViewImpl extends BaseViewImpl implements MediaMessagePlayView {

    private Presenter presenter;
    private FlexTable messagePanels = new FlexTable();
    private static HTML player = new HTML();

    public MediaMessagePlayViewImpl() {

    }

    @Override
    public Widget asWidget() {

	VerticalPanel panel = new VerticalPanel();
	panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	panel.add(new RoundedCornerPanel("Play a Message...", messagePanels));

	Button back = new Button("Back");
	back.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		History.back();
	    }
	});
	panel.add(back);

	return getMainLayout("images/app/download.png", "Manage Messages", panel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init(String mediaUrl, MediaType mediaType) {
	Window.setTitle("Play message ...");
	messagePanels.setWidget(1, 0, new HTML("Message Media Play"));

	messagePanels.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

	StringBuffer sb = new StringBuffer();
	if (MediaType.MP3.equals(mediaType)) {
	    sb.append("<audio controls>");
	    sb.append("<source src=\"");
	    sb.append(mediaUrl);
	    sb.append("\" type=\"audio/mpeg\">");
	    sb.append("Your browser does not support the audio element.");
	    sb.append("</audio>");
	} else if (MediaType.MP4.equals(mediaType)) {
	    sb.append("<video width=\"400\" controls>");
	    sb.append("<source src=\"");
	    sb.append(mediaUrl);
	    sb.append("\" type=\"video/mp4\">");
	    sb.append("Your browser does not support HTML5 video.");
	    sb.append("</video>");
	}
	player.setHTML(sb.toString());
	messagePanels.setWidget(2, 0, player);
	messagePanels.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	messagePanels.setWidget(4, 0, new HTML("&nbsp;"));
    }

    @Override
    public void initTab() {
    }
}
