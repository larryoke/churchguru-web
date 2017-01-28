package com.laotek.churchguru.web.client.activity.dashboard;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DashWidget extends Composite {
    private SimplePanel outer = new SimplePanel();
    private FlexTable panel = new FlexTable();
    private DecoratorPanel decoratorPanel = new DecoratorPanel();
    private String label;
    private ClickHandler clickHandler;
    private Image spinningWheel = new Image("images/app/ajax-loader.gif");

    public DashWidget(String header, String label, ClickHandler clickHandler) {
	initWidget(outer);
	this.label = label;
	this.clickHandler = clickHandler;
	outer.getElement().getStyle().setProperty("margin", "10px");
	outer.add(decoratorPanel);
	decoratorPanel.add(panel);
	panel.setBorderWidth(0);
	panel.setHTML(0, 0, "<b>" + header + "</b>");
	getElement().getStyle().setProperty("float", "left");
	Anchor anchor = new Anchor(label);
	anchor.addClickHandler(clickHandler);
	panel.setWidget(3, 0, anchor);

    }

    public DashWidget(String header) {
	initWidget(outer);
	outer.getElement().getStyle().setProperty("margin", "10px");
	outer.add(decoratorPanel);
	decoratorPanel.add(panel);
	panel.setBorderWidth(0);
	panel.setHTML(0, 0, "<b>" + header + "</b>");
	getElement().getStyle().setProperty("float", "left");
    }

    public void startSpinningWheel() {
	panel.setWidget(3, 1, spinningWheel);
    }

    public void stopSpinningWheel() {
	panel.remove(spinningWheel);
    }

    public void setWidget(Widget widget) {
	panel.setWidget(1, 0, widget);
    }

    public Widget getWidget() {
	return panel.getWidget(1, 0);
    }

    public void setSize(String width, String height) {
	decoratorPanel.setSize(width, height);
    }

    public void clear() {
	panel.clear();
	if (label != null) {
	    Anchor anchor = new Anchor(label);
	    anchor.addClickHandler(clickHandler);
	    panel.setWidget(3, 0, anchor);
	}
    }

}
