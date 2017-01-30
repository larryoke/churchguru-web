package com.laotek.churchguru.web.client.activity.website.estore;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;

public abstract class BaseViewImpl {

    private static final String TABLES_WIDTH = "100%";

    protected Widget getMainLayout(String headerPath, String headerName,
	    Widget widget) {

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image(headerPath));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>" + headerName + "</h2>"));

	FlexTable headerPanel = new FlexTable();
	headerPanel.setWidth(TABLES_WIDTH);
	headerPanel.setWidget(1, 0, topPanel);
	headerPanel.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	FlexTable flextable = new FlexTable();
	flextable.setWidth("100%");
	flextable.setBorderWidth(0);
	flextable.setWidget(0, 0, new RoundedCornerPanel(headerPanel));
	flextable.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	flextable.setWidget(1, 0, widget);
	flextable.getCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	return flextable;

	// VerticalPanel layout = new VerticalPanel();
	// layout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	// layout.setWidth("100%");
	// layout.add(flextable);
	// layout.add(widget);
	// return layout;
    }
}
