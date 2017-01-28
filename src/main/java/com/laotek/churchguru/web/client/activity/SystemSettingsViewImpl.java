package com.laotek.churchguru.web.client.activity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SystemSettingsViewImpl extends Composite implements SystemSettingsView{

	interface MyUiBinder extends UiBinder<Widget, SystemSettingsViewImpl> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    @UiField
    VerticalPanel pageLayout;
    
    private FlexTable optionsGrid = new FlexTable();
    
    private FlexTable adminPermissionGrid = new FlexTable();
    
	public SystemSettingsViewImpl(PlaceController placeController){
		initWidget(uiBinder.createAndBindUi(this));


		pageLayout.add(new HTML("&nbsp;"));
		HorizontalPanel topPanel = new HorizontalPanel();
	    topPanel.add(new Image("images/app/settings.png"));
	    topPanel.add(new HTML("&nbsp;"));
	    topPanel.add(new HTML("&nbsp;"));
	    topPanel.add(new HTML("<h2>System Settings</h2>"));
    	pageLayout.add(topPanel);
    	
    	optionsGrid.setBorderWidth(1);
    	optionsGrid.setWidth("600px");
    	optionsGrid.setCellPadding(2);
    	optionsGrid.setCellSpacing(0);
    	pageLayout.add(optionsGrid);
    	
    	int row = 0;
    	optionsGrid.setWidget(row, 0, new HTML("<b>Change Setting</b>"));
    	optionsGrid.setWidget(row, 1, new HTML("<b>Description</b>"));
    	
    	row = row + 1;
    	optionsGrid.setWidget(row, 0, new HTML("Church Name"));
    	optionsGrid.setWidget(row, 1, new HTML("Name goes here"));
    	
    	
    	adminPermissionGrid.setBorderWidth(0);    	
    	adminPermissionGrid.setWidth("100%");    	
    	pageLayout.add(adminPermissionGrid);
    }

	@Override
	public void setPresenter(Presenter presenter) {
		
	}

	@Override
	public void initTab() {
		
	}

	@Override
	public void init() {
		Window.setTitle("System Settings");
	}
}
