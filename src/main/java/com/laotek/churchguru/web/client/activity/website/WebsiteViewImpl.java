package com.laotek.churchguru.web.client.activity.website;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.website.audio.MediaMessagesPlace;
import com.laotek.churchguru.web.client.activity.website.audio.BaseViewImpl;
import com.laotek.churchguru.web.client.activity.website.audio.cat.MediaMessageCategoriesPlace;
import com.laotek.churchguru.web.client.activity.website.audio.speaker.MediaMessageSpeakersPlace;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;

public class WebsiteViewImpl extends BaseViewImpl implements WebsiteView {

    private Presenter presenter;

    @Override
    public Widget asWidget() {
	FlowPanel flowPanel = new FlowPanel();
	flowPanel.setWidth("100%");

	Widget widget = getBorderedButton("Messages", new MediaMessagesPlace("messages"));
	widget.setStylePrimaryName("flowPanel");
	flowPanel.add(widget);

	widget = getNewMessageButton("New Message");
	widget.setStylePrimaryName("flowPanel");
	flowPanel.add(widget);

	widget = getBorderedButton("Speakers", new MediaMessageSpeakersPlace("Speakers"));
	widget.setStylePrimaryName("flowPanel");
	flowPanel.add(widget);

	widget = getBorderedButton("Categories", new MediaMessageCategoriesPlace("Categories"));
	widget.setStylePrimaryName("flowPanel");
	flowPanel.add(widget);

	FlexTable ft = new FlexTable();
	ft.setWidget(0, 0, flowPanel);
	RoundedCornerPanel panel = new RoundedCornerPanel("E-Store", ft);

	return getMainLayout("images/app/website.png", "Manage Mobile Application", panel);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void init() {
	Window.setTitle("Website Management");
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showWebsitePanel("Website");
    }

    @Override
    public void initWidgets() {

    }

    private Widget getBorderedButton(String label, final Place place) {
	Button speakersButton = new Button(label);
	speakersButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(place);
	    }
	});
	FlexTable panel = new FlexTable();
	panel.setCellPadding(20);
	panel.setWidget(0, 0, speakersButton);
	panel.setStylePrimaryName("flowPanel");
	return panel;
    }

    private Widget getNewMessageButton(String label) {
	Button speakersButton = new Button(label);
	speakersButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		String title = Window.prompt("Please provide the title of the new message to be uploaded", "");
		if (!"".equals(title) && title != null) {
		    presenter.createMessage(title);
		}
	    }
	});
	FlexTable panel = new FlexTable();
	panel.setCellPadding(20);
	panel.setWidget(0, 0, speakersButton);
	panel.setStylePrimaryName("flowPanel");
	return panel;
    }

}
