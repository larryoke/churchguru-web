package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryPlace;
import com.laotek.churchguru.web.client.activity.home.HomePlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserDialog;
import com.laotek.churchguru.web.client.activity.user.NewUserProfileDialog;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.client.activity.website.listening.ListeningMessagesPlace;
import com.laotek.churchguru.web.shared.UserDto;

public class MainMenuContext extends BaseMainMenuContext {

    private static MainMenuContext mainMenuContext = new MainMenuContext();

    private PlaceController placeController;
    private StackLayoutPanel stackMenu;
    private DockLayoutPanel dockLayoutPanel;

    public static MainMenuContext getInstance() {
        return mainMenuContext;
    }

    public void initStackMenu(DockLayoutPanel dockLayoutPanel, StackLayoutPanel stackMenu) {
        this.dockLayoutPanel = dockLayoutPanel;
        this.stackMenu = stackMenu;

        createDashboardPanel(stackMenu);

        createWebsitePanel(stackMenu);

        createNoticeAndEventPanel(stackMenu);

        createEStorePanel(stackMenu);

        createUserPanel(stackMenu);

        initBlinking();
    }

    private boolean initStackLayoutPanel(FlexTable panel, String imagePath, String label,
                                         StackLayoutPanel stackLayoutPanel, Place place, UserRoleName requiredUserRoleName,
                                         UserRoleName currentUserRoleName) {
        boolean hasRoleRequired = hasRoleRequired(requiredUserRoleName, currentUserRoleName);
        if (hasRoleRequired) {
            panel.setWidth("100%");
            stackLayoutPanel.add(panel, createHeader(new Image(imagePath), label, place), 2);
        }
        return hasRoleRequired;
    }

    private HorizontalPanel createHeader(Image image, String label, final Place place) {
        HorizontalPanel panel = new HorizontalPanel();
        panel.setWidth("100%");
        panel.add(image);
        image.setSize("25px", "25px");
        HTML header = new HTML("<small>" + label + "</small>");
        header.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ApplicationContext.getInstance().getPlaceController().goTo(place);
            }
        });
        panel.add(header);
        return panel;
    }

    private void createDashboardPanel(StackLayoutPanel stackMenu) {
        dashboardPanel.setHeight("100%");
        UserDto dto = UserContext.getInstance().getUserDto();
        initStackLayoutPanel(dashboardPanel, "images/app/dashboard.png", "Dashboard", stackMenu, new HomePlace("home"),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());
        createLink(new Image("images/app/home.png"), "Home", false, dashboardPanel, new HomePlace("home"),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        // createHelpLink(new Image("images/app/help.png"),
        // createHelpAnchor("/help"), dashboardPanel,
        // "/help/dashboardHelp");

        // createLink(new Image("images/app/homeMenu.png"), "People Chart",
        // dashboardPanel, new PeopleChartPlace("peopleChart"),
        // UserRoleName.ORGANISATION_DATA_VIEW_ONLY,
        // dto.getOrganisationRole());
        // createLink("Newsletters Chart", dashboardPanel, new
        // NewslettersChartPlace("newsLettersChart"));
        // createLink("Weekly Attendance Chart", dashboardPanel, new
        // WeeklyAttendanceChartPlace("weeklyAttendance"));
        // createLink("Account Quotas", dashboardPanel, new
        // AccountQuotaPlace("accountQuotas"));
    }

    private void createWebsitePanel(StackLayoutPanel stackMenu) {
        websitePanel.setHeight("100%");
        UserDto dto = UserContext.getInstance().getUserDto();
        initStackLayoutPanel(websitePanel, "images/app/website.png", "Manage Mobile Application", stackMenu,
                new GeneralChurchAppPlace("churchApp"), UserRoleName.ORGANISATION_DATA_VIEW_ONLY,
                dto.getOrganisationRole());

        createLink(new Image("images/app/system_update.png"), "Mobile App Options", false, websitePanel,
                new GeneralChurchAppPlace("churchApp"), UserRoleName.ORGANISATION_DATA_VIEW_ONLY,
                dto.getOrganisationRole());
    }

    private void createEStorePanel(StackLayoutPanel stackMenu) {
        listeningPanel.setHeight("100%");
        UserDto dto = UserContext.getInstance().getUserDto();
        initStackLayoutPanel(listeningPanel, "images/app/cart.png", "Manage Listening and Watching", stackMenu,
                new ListeningMessagesPlace("messages"), UserRoleName.ORGANISATION_DATA_VIEW_ONLY,
                dto.getOrganisationRole());

        createLink(new Image("images/app/cart.png"), "Manage Listening", false, listeningPanel, new ListeningMessagesPlace("messages"),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createNewListeningMessageLink(new Image("images/app/sendMail.png"), listeningPanel,
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createLink(new Image("images/app/cart.png"), "Manage Watching", false, listeningPanel, new ListeningMessagesPlace("messages"),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createNewWatchingMessageLink(new Image("images/app/watch_live.png"), listeningPanel,
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());
    }

    private void createNoticeAndEventPanel(StackLayoutPanel stackMenu) {
        noticesAndEventsPanel.setHeight("100%");
        UserDto dto = UserContext.getInstance().getUserDto();
        initStackLayoutPanel(noticesAndEventsPanel, "images/app/sms.png", "Manage Notices and Events", stackMenu,
                new GetCurrentNoticesAndEventsHistoryPlace(BrowseMessagesType.POSTED.name()),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createLink(new Image("images/app/searchResult.png"), "Posted Notices and Events", false, noticesAndEventsPanel,
                new GetCurrentNoticesAndEventsHistoryPlace(BrowseMessagesType.POSTED.name()),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createLink(new Image("images/app/searchResult.png"), "Draft Notices and Events", false, noticesAndEventsPanel,
                new GetCurrentNoticesAndEventsHistoryPlace(BrowseMessagesType.DRAFT.name()),
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

        createPostNoticeOrEventLink(new Image("images/app/sendMail.png"), noticesAndEventsPanel,
                UserRoleName.ORGANISATION_DATA_VIEW_ONLY, dto.getOrganisationRole());

    }

    private void createUserPanel(StackLayoutPanel stackMenu) {
        userPanel.setHeight("100%");
        UserDto dto = UserContext.getInstance().getUserDto();
        if (initStackLayoutPanel(userPanel, "images/app/operator.png", "Manage Users", stackMenu,
                new AllUserPlace("allUser"), UserRoleName.USER_VIEW_ALLOWED, dto.getAppUserRole())) {
            createLink(new Image("images/app/users.png"), "All users", false, userPanel, new AllUserPlace("allUser"),
                    UserRoleName.USER_VIEW_ALLOWED, dto.getAppUserRole());

            createLink(new Image("images/app/users.png"), "All user profiles", false, userPanel,
                    new UserProfilesPlace("userProfiles"), UserRoleName.USER_VIEW_ALLOWED, dto.getAppUserRole());

            Anchor newUserAnchor = new Anchor("Add New User");
            newUserAnchor.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    NewUserDialog.getInstance().init("Add New User");
                }
            });
            createLink(new Image("images/app/user_add.png"), userPanel, newUserAnchor, UserRoleName.USER_VIEW_ALLOWED,
                    dto.getAppUserRole());

            Anchor newUserProfileAnchor = new Anchor("Add New User Profile");
            newUserProfileAnchor.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    NewUserProfileDialog.getInstance().init("Add New User Profile");
                }
            });
            createLink(new Image("images/app/user_add.png"), userPanel, newUserProfileAnchor,
                    UserRoleName.USER_VIEW_ALLOWED, dto.getAppUserRole());

            // createHelpLink(new Image("images/app/help.png"),
            // createHelpAnchor("/help#7"), userPanel, "/help/userHelp");
        }
    }

    private void highlightSelection(final FlexTable panel, final String anchorLabel) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                for (int i = 0; 0 < panel.getRowCount(); ++i) {
                    Widget widget = panel.getWidget(i, 0);
                    if (widget instanceof Anchor) {
                        Anchor anchor = (Anchor) widget;
                        if (anchorLabel.equals(anchor.getText())) {
                            anchor.setStylePrimaryName("boldMainTabBarAnchor");
                        } else {
                            anchor.removeStyleName("boldMainTabBarAnchor");
                        }
                    }
                }
            }
        });
    }

    public void initPlaceController(PlaceController placeController) {
        this.placeController = placeController;
    }

    public PlaceController getPlaceController() {
        return placeController;
    }

    public StackLayoutPanel getStackMenu() {
        return stackMenu;
    }

    public void showDashboardPanel(String anchorLabel) {
        stackMenu.showWidget(dashboardPanel);
        highlightSelection(dashboardPanel, anchorLabel);
    }

    public void showWebsitePanel(String anchorLabel) {
        stackMenu.showWidget(websitePanel);
        highlightSelection(websitePanel, anchorLabel);
    }

    public void showSocialMediaPanel(String anchorLabel) {
        stackMenu.showWidget(socialMediaPanel);
        highlightSelection(socialMediaPanel, anchorLabel);
    }

    public void showListeningPanel(String anchorLabel) {
        stackMenu.showWidget(listeningPanel);
        highlightSelection(listeningPanel, anchorLabel);
    }

    public void showInstantMessagePanel(String anchorLabel) {
        stackMenu.showWidget(noticesAndEventsPanel);
        highlightSelection(noticesAndEventsPanel, anchorLabel);
    }

    public void showDonationPanel(String anchorLabel) {
        stackMenu.showWidget(donationPanel);
        highlightSelection(donationPanel, anchorLabel);
    }

    public void showUserPanel(String anchorLabel) {
        stackMenu.showWidget(userPanel);
        highlightSelection(userPanel, anchorLabel);
    }

    public void hideMenu() {
        dockLayoutPanel.setWidgetHidden(stackMenu, true);
        dockLayoutPanel.setWidgetSize(stackMenu, 0);
        dockLayoutPanel.animate(500);
    }

    public void showMenu() {
        dockLayoutPanel.setWidgetHidden(stackMenu, false);
        dockLayoutPanel.setWidgetSize(stackMenu, 18);
        dockLayoutPanel.animate(500);
    }

}