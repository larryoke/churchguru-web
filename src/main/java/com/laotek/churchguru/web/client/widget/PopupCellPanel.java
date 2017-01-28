package com.laotek.churchguru.web.client.widget;

import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopupCellPanel extends PopupPanel {
    private static PopupCellPanel popupCellPanel = new PopupCellPanel();
    private Widget widget = null;

    private PopupCellPanel() {
	super(true);
    }

    public static PopupCellPanel getInstance() {
	return popupCellPanel;
    }
}
