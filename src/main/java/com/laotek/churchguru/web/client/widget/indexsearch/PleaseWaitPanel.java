package com.laotek.churchguru.web.client.widget.indexsearch;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class PleaseWaitPanel extends PopupPanel {
	
	public PleaseWaitPanel(){
		super(true);	
		FlexTable inner = new FlexTable();
		inner.setWidget(0, 0, new Image("images/app/ajax-loader.gif"));
		inner.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		setWidget(inner);	
	}


}
