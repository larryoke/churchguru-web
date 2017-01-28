package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RoundedCornerPanel extends Composite {

    private SimplePanel topLeftCorner = new SimplePanel();
    private SimplePanel topRightCorner = new SimplePanel();
    private SimplePanel bottomLeftCorner = new SimplePanel();
    private SimplePanel bottomRightCorner = new SimplePanel();

    private SimplePanel topSide = new SimplePanel();
    private SimplePanel rightSide = new SimplePanel();
    private SimplePanel leftSide = new SimplePanel();
    private SimplePanel bottomSide = new SimplePanel();

    public RoundedCornerPanel(String title, Widget widget) {
	VerticalPanel vPanel = new VerticalPanel();
	vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	vPanel.add(new HTML("<h2>" + title + "</h2>"));
	vPanel.add(initPanel(widget));
	this.initWidget(vPanel);
    }

    public RoundedCornerPanel(Widget widget, Button button) {
	button.getElement().setAttribute("style", "margin:0px 40px 0px 25px;");
	String label = button.getHTML();
	button.setHTML("<img border='0' src='images/app/button-add.png' />"
		+ label);
	VerticalPanel vPanel = new VerticalPanel();
	vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	vPanel.add(button);
	vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	vPanel.add(initPanel(widget));
	this.initWidget(vPanel);
    }

    public RoundedCornerPanel(Widget widget, Button button1, Button button2) {
	VerticalPanel vPanel = new VerticalPanel();
	vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	HorizontalPanel buttonPanel = new HorizontalPanel();
	buttonPanel.getElement().setAttribute("style",
		"margin:0px 40px 0px 25px;");
	buttonPanel.add(button1);
	buttonPanel.add(new HTML("&nbsp;"));
	buttonPanel.add(new HTML("&nbsp;"));
	buttonPanel.add(new HTML("&nbsp;"));
	buttonPanel.add(button2);

	String label = button1.getHTML();
	button1.setHTML("<img border='0' src='images/app/button-add.png' />"
		+ label);

	label = button2.getHTML();
	button2.setHTML("<img border='0' src='images/app/button-add.png' />"
		+ label);

	vPanel.add(buttonPanel);
	vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	vPanel.add(initPanel(widget));
	this.initWidget(vPanel);
    }

    public RoundedCornerPanel(Widget widget) {
	VerticalPanel vPanel = new VerticalPanel();
	vPanel.add(initPanel(widget));
	this.initWidget(vPanel);
    }

    private SimplePanel initPanel(final Widget widget) {
	// style
	topLeftCorner.setStylePrimaryName("cornerTL");
	topRightCorner.setStylePrimaryName("cornerTR");
	bottomLeftCorner.setStylePrimaryName("cornerBL");
	bottomRightCorner.setStylePrimaryName("cornerBR");

	// style
	topSide.setStylePrimaryName("topSide");
	rightSide.setStylePrimaryName("rightSide");
	leftSide.setStylePrimaryName("leftSide");
	bottomSide.setStylePrimaryName("bottomSide");

	// add
	topRightCorner.add(widget);
	topLeftCorner.add(topRightCorner);
	bottomRightCorner.add(topLeftCorner);
	bottomLeftCorner.add(bottomRightCorner);

	// add
	rightSide.add(bottomLeftCorner);
	leftSide.add(rightSide);
	bottomSide.add(leftSide);
	topSide.add(bottomSide);
	return topSide;
    }
}
