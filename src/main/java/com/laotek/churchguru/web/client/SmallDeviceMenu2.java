package com.laotek.churchguru.web.client;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
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
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;

public class SmallDeviceMenu2 extends Composite
	implements HasWidgets, HasAllKeyHandlers, HasClickHandlers, HasMouseOverHandlers, HasMouseOutHandlers {

    private PopupPanel popupPanel = new PopupPanel(true);
    private FlexTable layout = new FlexTable();
    private FocusPanel focusPanel = new FocusPanel();

    public SmallDeviceMenu2() {
	layout.setBorderWidth(1);
	layout.setCellPadding(0);
	layout.setCellSpacing(0);
	popupPanel.setWidth("160px");
	int row = 0;
	addRow(row, 0, "Home", new GeneralChurchAppPlace("churchApp"));
	addRow(++row, 0, "Manage Mobile Application", new GeneralChurchAppPlace("churchApp"));
	addRow(++row, 0, "Manage Users", new AllUserPlace("guest"));
	popupPanel.setWidget(layout);
	popupPanel.setAnimationEnabled(true);
    }

    void show() {
	popupPanel.show();
    }

    void hide() {
	popupPanel.hide();
    }

    void setPopupPosition(int left, int height) {
	popupPanel.setPopupPosition(left, height);
    }

    private void addRow(int row, int col, String label, final Place place) {
	layout.setWidget(row, col, getAnchor(label, place));
    }

    private Anchor getAnchor(String label, final Place place) {
	Anchor anchor = new Anchor(label);
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		popupPanel.hide();
		ApplicationContext.getInstance().getPlaceController().goTo(place);
	    }
	});
	return anchor;
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
	return focusPanel.addMouseOutHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
	return null;
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
	return null;
    }

    @Override
    public void add(Widget w) {

    }

    @Override
    public void clear() {
    }

    @Override
    public Iterator<Widget> iterator() {
	return null;
    }

    @Override
    public boolean remove(Widget w) {
	// TODO Auto-generated method stub
	return false;
    }
}
