package com.laotek.churchguru.web.client.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MessageDialog extends DialogBox {

    private static MessageDialog dialog = new MessageDialog();

    private MessageDialog() {
	super(true);
	setGlassEnabled(true);
	setAnimationEnabled(true);
    }

    public static MessageDialog getInstance() {
	return dialog;
    }

    public void display(String title, String message, String learnMore,
	    boolean autoHide) {
	if (title != null) {
	    setText(title);
	}
	handleLayout(message, learnMore, null);
	handleAutoHide(autoHide);
	center();
    }

    public void display(String title, String message, String learnMore,
	    boolean autoHide, Widget widget) {
	if (title != null) {
	    setText(title);
	}
	handleLayout(message, learnMore, widget);
	handleAutoHide(autoHide);
	center();
    }

    private void handleLayout(String message, String learnMore, Widget widget) {
	FlexTable layout = new FlexTable();
	layout.setBorderWidth(0);
	FlexCellFormatter formatter = layout.getFlexCellFormatter();
	layout.setWidth("100%");

	int row = 0;
	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
	formatter.setColSpan(row, 0, 2);
	layout.setWidget(row, 0, new Image("images/app/alert.png"));

	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_LEFT);
	formatter.setColSpan(row, 0, 2);
	layout.setWidget(++row, 0, new HTML(message + "</center>" + "<br>"));

	row = handleLearnMore(learnMore, layout, formatter, row);

	if (widget != null) {
	    layout.setWidget(++row, 0, widget);
	    layout.setWidget(++row, 0, new HTML("&nbsp;"));
	}

	layout.setWidget(++row, 0, ok());
	formatter.setHorizontalAlignment(row, 0,
		HasHorizontalAlignment.ALIGN_CENTER);

	layout.setWidget(++row, 0, new HTML("&nbsp;"));

	setWidget(layout);
    }

    private int handleLearnMore(String learnMore, FlexTable layout,
	    FlexCellFormatter formatter, int row) {
	if (learnMore != null) {

	    layout.setWidget(++row, 0, new LearnMorePanel(new HTML(learnMore)));

	    formatter.setHorizontalAlignment(row, 0,
		    HasHorizontalAlignment.ALIGN_CENTER);
	    formatter.setColSpan(row, 0, 2);
	}
	return row;
    }

    private void handleAutoHide(boolean autoHide) {
	if (autoHide) {
	    Timer timer = new Timer() {
		@Override
		public void run() {
		    hide();
		    cancel();
		}
	    };
	    timer.schedule(4000);
	}
    }

    private Button ok() {
	Button button = new Button("OK");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	return button;
    }
}
