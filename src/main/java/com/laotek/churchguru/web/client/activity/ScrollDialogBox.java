package com.laotek.churchguru.web.client.activity;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class ScrollDialogBox extends DialogBox {

    private ScrollPanel scrollPanel;

    public ScrollDialogBox(boolean autoHide) {
	super(autoHide);
    }

    @Override
    public void setWidget(Widget w) {
	scrollPanel = new ScrollPanel(w);
	scrollPanel.setSize("500px", "550px");
	super.setWidget(scrollPanel);
    }

    public void scrollSlowlyToTop() {
	Timer scrollTimer = new Timer() {
	    @SuppressWarnings("unused")
	    public void run() {
		int currentVerticalScrollPosition = scrollPanel
			.getVerticalScrollPosition();

		if (currentVerticalScrollPosition > 0) {

		    currentVerticalScrollPosition = scrollPanel
			    .getVerticalScrollPosition() - 15;

		    scrollPanel
			    .setVerticalScrollPosition(currentVerticalScrollPosition);
		} else {
		    cancel();
		}
	    }
	};
	scrollTimer.scheduleRepeating(10);
    }
}
