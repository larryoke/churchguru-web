package com.laotek.churchguru.web.clientm.activity.home;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;

public class IconWidget extends Composite {

    SimplePanel bg = new SimplePanel();

    public static int WIDTH = 100;

    public IconWidget(final String title, IconHandler iconHandler) {
	this.initWidget(initPanel(title, iconHandler));
    }

    private HorizontalPanel initPanel(final String title, final IconHandler iconHandler) {
	HTML titleHtml = new HTML(title);
	titleHtml.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		iconHandler.open();
	    }
	});
	Image icon = iconHandler.getIcon();

	icon.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		iconHandler.open();
	    }
	});

	bg.add(titleHtml);
	bg.getElement().setAttribute("style",
		"font-family: 'Tangerine', serif;font-size: 17px;text-shadow: 4px 4px 4px #aaa;");

	// VerticalPanel panel = new VerticalPanel();
	// panel.setWidth(WIDTH + "px");
	// panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	// panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	// panel.add(new HTML("&nbsp;"));
	// panel.add(bg);
	// panel.add(titleHtml);

	HorizontalPanel panel = new HorizontalPanel();
	panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	panel.add(new HTML("&nbsp;&nbsp;&nbsp;"));
	panel.add(icon);
	panel.add(new HTML("&nbsp;&nbsp;&nbsp;"));
	panel.add(bg);
	return panel;
    }
}
