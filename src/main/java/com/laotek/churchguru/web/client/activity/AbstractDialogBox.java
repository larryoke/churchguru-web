package com.laotek.churchguru.web.client.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class AbstractDialogBox extends DialogBox {

    public AbstractDialogBox() {
    }

    public AbstractDialogBox(boolean autoHide, boolean modal) {
	super(autoHide, modal);
    }

    public AbstractDialogBox(boolean autoHide) {
	super(autoHide);
    }

    public void setWidget(Widget widget) {
	FlexTable inner = new FlexTable();
	Image close = new Image("images/app/gtk_close.png");
	close.setStylePrimaryName("handPointer");
	close.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	inner.setWidget(0, 0, close);
	inner.getCellFormatter().setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_RIGHT);
	inner.setWidget(1, 0, widget);
	setWidget(inner);
    }

}
