package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;

public class MenuLayout extends Composite {

    interface MyUiBinder extends UiBinder<Widget, MenuLayout> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private ClientFactory clientFactory;

    @UiField
    VerticalPanel menuPanel;

    @UiField
    VerticalPanel homeMenuPanel;

    @UiField
    VerticalPanel searchMenuPanel;

    @UiField
    VerticalPanel memberAddingMenuPanel;

    @UiField
    VerticalPanel adminMenuPanel;

    @UiField
    Anchor myAccountAnchor;

    @UiField
    Anchor allAdminAnchor;

    @UiField
    Anchor emailNotificationsAnchor;

    @UiField
    Anchor permissionsAnchor;

    public MenuLayout(ClientFactory clientFactory) {
	initWidget(uiBinder.createAndBindUi(this));
	this.clientFactory = clientFactory;
    }

    @UiHandler("myAccountAnchor")
    void onWelcomeAnchorClick(ClickEvent event) {
	clientFactory.getPlaceController().goTo(new GeneralChurchAppPlace("churchApp"));
    }

    @UiHandler("allAdminAnchor")
    void onViewAdminAnchorClick(ClickEvent event) {
	clientFactory.getPlaceController().goTo(new AllUserPlace("allAdmin"));
    }
}
