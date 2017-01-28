package com.laotek.churchguru.web.client.widget.indexsearch;

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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;


class DropDownTopOrBottomItem extends Composite implements HasWidgets, HasAllKeyHandlers, HasClickHandlers, HasMouseOverHandlers, HasMouseOutHandlers {

    private FlexTable table;
    private FocusPanel focusPanel;

    public DropDownTopOrBottomItem(String label) {	    	
    	table = new FlexTable();
    	table.setWidth("240px");
    	table.setStylePrimaryName("handPointer");
    	table.setWidget(0, 0, new HTML("<b>" + label + "</b>"));
    	table.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
    	
        focusPanel = new FocusPanel();
        focusPanel.setWidget(table);
        initWidget(focusPanel);
    }
    
    public void select(){
    	table.getCellFormatter().addStyleName(0, 0, "selectRowBlue");
    	focusPanel.setFocus(true);
    }
    
    public void deSelect(){
    	table.getCellFormatter().removeStyleName(0, 0, "selectRowBlue");
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
}