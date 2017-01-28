package com.laotek.churchguru.web.client.activity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MessageRecipientDialobBox extends DialogBox {
	protected VerticalPanel pageLayout = new VerticalPanel();
	
	public MessageRecipientDialobBox(boolean autoHide) {
		super(autoHide);
	}


	protected void closeImagePanel() {
		pageLayout.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		pageLayout.add(closeImage());
	}
	
	private Widget closeImage(){
		Anchor anchor = new Anchor("Close");
		anchor.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		Image closeImage = new Image("images/app/gtk_close.png");
		closeImage.setStylePrimaryName("handPointer");
		closeImage.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		HorizontalPanel panel = new HorizontalPanel();
		panel.add(anchor);
		panel.add(new HTML("&nbsp;"));
		panel.add(closeImage);
		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.add(panel);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(decPanel);
		vPanel.add(new HTML("&nbsp;"));
		return vPanel;
	}
}
