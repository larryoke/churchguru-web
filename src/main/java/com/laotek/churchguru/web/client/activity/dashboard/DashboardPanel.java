package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.activity.LearnMorePanel;

public abstract class DashboardPanel extends FlexTable {

    private LearnMorePanel disclosurePanel = new LearnMorePanel();

    private static final String MAIN_WIDTH = "600px";
    private static final String SCROLL_HEIGHT = "150px";

    public DashboardPanel() {
	setBorderWidth(0);
	setWidth(MAIN_WIDTH);
	setHeight("250px");

	setWidget(0, 0, getDecoratorTable());
	getFlexCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	getFlexCellFormatter().setVerticalAlignment(0, 0,
		HasVerticalAlignment.ALIGN_TOP);
	getFlexCellFormatter().setWidth(0, 0, MAIN_WIDTH);
    }

    private DecoratorPanel getDecoratorTable() {
	DecoratorPanel decoratorPanel = new DecoratorPanel();
	decoratorPanel.setWidth(MAIN_WIDTH);

	Widget widget = getWidget();
	widget.setWidth(MAIN_WIDTH);
	ScrollPanel scroller = new ScrollPanel(widget);
	scroller.setHeight(SCROLL_HEIGHT);
	decoratorPanel.add(scroller);

	FlexTable decoratorTable = new FlexTable();

	decoratorTable.setWidget(0, 0, new HTML("<b>" + getLabel() + "</b>"));
	decoratorTable.getFlexCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	disclosurePanel.setContent(getLearnMoreInfo());
	decoratorTable.setWidget(1, 0, disclosurePanel);
	decoratorTable.getFlexCellFormatter().setHorizontalAlignment(1, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	decoratorTable.setWidget(2, 0, new HTML("&nbsp;"));

	decoratorTable.setWidget(3, 0, scroller);
	decoratorTable.getFlexCellFormatter().setHorizontalAlignment(3, 0,
		HasHorizontalAlignment.ALIGN_LEFT);

	decoratorPanel.add(decoratorTable);
	return decoratorPanel;
    }

    public abstract Widget getWidget();

    public abstract Image getIcon();

    public abstract String getLabel();

    public abstract HTML getLearnMoreInfo();
}
