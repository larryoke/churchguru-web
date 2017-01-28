package com.laotek.churchguru.web.client.widget.indexsearch;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.laotek.churchguru.web.client.ApplicationContext;

public class NotFoundPanel extends PopupPanel {

    public NotFoundPanel() {
	super(true);
	FlexTable inner = new FlexTable();
	inner.setWidget(0, 0, new Image("images/app/cross.png"));
	inner.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	inner.getFlexCellFormatter().setColSpan(0, 0, 3);

	inner.setHTML(0, 0, "Not Found...");
	inner.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	inner.getFlexCellFormatter().setColSpan(1, 0, 1);
	inner.getFlexCellFormatter().setColSpan(1, 1, 1);
	inner.getFlexCellFormatter().setColSpan(1, 2, 1);

	setWidget(inner);
    }

    private void add(FlexTable panel, int row, String imageUrl,
	    String anchorLabel, final Place place) {
	Image image = new Image(imageUrl);
	image.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NotFoundPanel.this.hide();
		ApplicationContext.getInstance().getPlaceController()
			.goTo(place);
	    }
	});
	Anchor anchor = new Anchor(anchorLabel);
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NotFoundPanel.this.hide();
		ApplicationContext.getInstance().getPlaceController()
			.goTo(place);
	    }
	});

	panel.getCellFormatter().setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	panel.getCellFormatter().setVerticalAlignment(row, 0,
		HasVerticalAlignment.ALIGN_MIDDLE);

	image.setStylePrimaryName("handPointer");

	panel.setWidget(row, 0, image);
	panel.setWidget(row, 1, new HTML("&nbsp;"));
	panel.setWidget(row, 2, anchor);
    }

}
