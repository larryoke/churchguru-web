package com.laotek.churchguru.web.client.widget.indexsearch;

import java.util.HashMap;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DropDownItem extends Composite
	implements HasWidgets, HasAllKeyHandlers, HasClickHandlers, HasMouseOverHandlers, HasMouseOutHandlers {

    private FlexTable table;
    private FocusPanel focusPanel;
    private HashMap<String, String> memberMap;

    public DropDownItem(String currentKeyword, HashMap<String, String> memberMap) {
	this.memberMap = memberMap;
	String fullname = memberMap.get("fullname");
	fullname = highlightSubstring(currentKeyword, fullname);

	table = new FlexTable();
	table.setBorderWidth(0);
	table.setCellPadding(0);
	table.setCellSpacing(0);
	table.setWidth("240px");
	table.setStylePrimaryName("handPointer");

	String address = memberMap.get("address");
	if (address != null) {
	    address = highlightSubstring(currentKeyword, address);
	}

	String personIndex = memberMap.get("personIndex");
	table.setHTML(0, 0, personIndex);
	table.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);

	String picture = memberMap.get("picture");
	if (picture != null) {
	    Image image = new Image(picture);
	    image.setHeight("50px");
	    table.setWidget(0, 1, image);
	} else {
	    table.setWidget(0, 1, new HTML("&nbsp;"));
	}

	VerticalPanel verticalPanel = new VerticalPanel();
	verticalPanel.add(new HTML(fullname));
	if (address != null) {
	    verticalPanel.add(new HTML(address));
	}
	table.setWidget(0, 2, verticalPanel);

	focusPanel = new FocusPanel();
	focusPanel.setWidget(table);
	initWidget(focusPanel);
    }

    public String getIdentifier() {
	return memberMap.get("identifier");
    }

    public IndexSearchType getIndexSearchType() {
	String searchType = memberMap.get("searchType");
	if ("guest".equals(searchType)) {
	    return IndexSearchType.GUEST;

	} else if ("member".equals(searchType)) {
	    return IndexSearchType.MEMBER;

	} else {
	    return IndexSearchType.MEMBER_AND_GUEST;
	}
    }

    public void highlight() {
	table.getCellFormatter().addStyleName(0, 0, "selectRowBlue");
	table.getCellFormatter().addStyleName(0, 1, "selectRowBlue");
	table.getCellFormatter().addStyleName(0, 2, "selectRowBlue");
    }

    public void select() {
	table.getCellFormatter().addStyleName(0, 0, "selectRowBlue");
	table.getCellFormatter().addStyleName(0, 1, "selectRowBlue");
	table.getCellFormatter().addStyleName(0, 2, "selectRowBlue");
	focusPanel.setFocus(true);
    }

    public void deSelect() {
	table.getCellFormatter().removeStyleName(0, 0, "selectRowBlue");
	table.getCellFormatter().removeStyleName(0, 1, "selectRowBlue");
	table.getCellFormatter().removeStyleName(0, 2, "selectRowBlue");
	focusPanel.setFocus(false);
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
	return focusPanel.addKeyUpHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
	return focusPanel.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
	return focusPanel.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
	return focusPanel.addClickHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
	return focusPanel.addMouseOutHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
	return focusPanel.addMouseOverHandler(handler);
    }

    @Override
    public void add(Widget w) {
	table.add(w);
    }

    @Override
    public void clear() {
	table.clear();
    }

    @Override
    public Iterator<Widget> iterator() {
	return table.iterator();
    }

    @Override
    public boolean remove(Widget w) {
	return false;
    }

    private String highlightSubstring(String substring, String value) {
	int startIndex = value.toLowerCase().indexOf(substring.toLowerCase());
	if (startIndex != -1) {
	    StringBuffer sb = new StringBuffer(value);
	    sb.insert(startIndex, "<b>");
	    int endIndex = sb.toString().toLowerCase().indexOf(substring.toLowerCase()) + substring.length();
	    sb.insert(endIndex, "</b>");
	    return sb.toString();
	}
	return value;
    }
}
