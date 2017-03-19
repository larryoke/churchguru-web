package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.UserProfileDto;

public class UserProfilesViewImpl extends Composite implements UserProfilesView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    interface MyUiBinder extends UiBinder<Widget, UserProfilesViewImpl> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private Presenter presenter;

    private FlexTable heading = new FlexTable();

    @UiField
    VerticalPanel pageLayout;

    public UserProfilesViewImpl() {
	initWidget(uiBinder.createAndBindUi(this));

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/users.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>User Profiles</h2>"));

	heading.setBorderWidth(0);
	heading.setWidget(0, 0, topPanel);
	heading.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	Anchor newAdminAnchor = new Anchor("Create a new user");
	newAdminAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NewUserDialog.getInstance().init("Add new user");
	    }
	});
	pageLayout.add(new HTML("&nbsp;"));
    }

    @Override
    public void setName(String name) {
	nameSpan.setInnerText("Good-bye, " + name);
    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initUserProfiles(List<UserProfileDto> profiles) {
	pageLayout.clear();
	pageLayout.add(new RoundedCornerPanel(heading));

	for (UserProfileDto dto : profiles) {

	    pageLayout.add(new HTML("&nbsp;"));

	    FlexTable permissionGrid = new FlexTable();

	    permissionGrid.setWidth("500px");
	    permissionGrid.setBorderWidth(0);
	    permissionGrid.setCellPadding(0);
	    permissionGrid.setCellSpacing(0);

	    int row = -1;

	    if (dto.getQuickEmailRole() != null) {
		permissionGrid.setHTML(++row, 0, "<b>Email Permission</b>");
		permissionGrid.setHTML(row, 1, String.valueOf(dto.getQuickEmailRole().getDesc()));
	    }

	    if (dto.getSmsRole() != null) {
		permissionGrid.setHTML(++row, 0, "<b>SMS Permission</b>");
		permissionGrid.setHTML(row, 1, String.valueOf(dto.getSmsRole().getDesc()));
	    }

	    if (dto.getAppUserRole() != null) {
		permissionGrid.setHTML(++row, 0, "<b>Users</b>");
		permissionGrid.setHTML(row, 1, String.valueOf(dto.getAppUserRole().getDesc()));
	    }

	    if (dto.getOrganisationRole() != null) {
		permissionGrid.setHTML(++row, 0, "<b>Organisation</b>");
		permissionGrid.setHTML(row, 1, String.valueOf(dto.getOrganisationRole().getDesc()));
	    }
	    // if (dto.getMemberRole() != null) {
	    // permissionGrid.setHTML(++row, 0,
	    // "<b>Member Adding Permission</b>");
	    // permissionGrid.setHTML(row, 1,
	    // String.valueOf(dto.getMemberRole().getDesc()));
	    // }

	    // if (dto.getDepartmentRole() != null) {
	    // permissionGrid.setHTML(++row, 0, "<b>Department</b>");
	    // permissionGrid.setHTML(row, 1,
	    // String.valueOf(dto.getDepartmentRole().getDesc()));
	    // }
	    // if (dto.getNotificationRole() != null) {
	    // permissionGrid.setHTML(++row, 0, "<b>Notifications</b>");
	    // permissionGrid.setHTML(row, 1,
	    // String.valueOf(dto.getNotificationRole().getDesc()));
	    // }

	    if (dto.getDonationRole() != null) {
		permissionGrid.setHTML(++row, 0, "<b>Donations</b>");
		permissionGrid.setHTML(row, 1, String.valueOf(dto.getDonationRole().getDesc()));
	    }

	    FlexTable usersInRole = new FlexTable();
	    usersInRole.setWidth("500px");
	    usersInRole.setWidget(usersInRole.getRowCount(), 0, new HTML("&nbsp;"));

	    for (UserDto userDto : dto.getUserDtos()) {
		usersInRole.setHTML(usersInRole.getRowCount(), 0, userDto.getFullname());
	    }
	    usersInRole.setWidget(usersInRole.getRowCount(), 0, new HTML("&nbsp;"));

	    FlexTable dates = new FlexTable();
	    dates.setWidth("500px");
	    dates.setHTML(0, 1, "&nbsp;");
	    dates.setHTML(1, 0, "Created:");
	    dates.setHTML(1, 1, dto.getCreatedDate());
	    dates.setHTML(2, 0, "Last Updated:");
	    dates.setHTML(2, 1, dto.getUpdatedDate());
	    dates.setHTML(3, 1, "&nbsp;");

	    TabPanel tabPanel = new TabPanel();
	    tabPanel.add(permissionGrid, "Roles in profile");
	    tabPanel.add(usersInRole, "Users in profile");
	    tabPanel.add(dates, "History");
	    tabPanel.setAnimationEnabled(true);
	    tabPanel.selectTab(0);

	    VerticalPanel tabAndAnchor = new VerticalPanel();
	    tabAndAnchor.add(tabPanel);
	    tabAndAnchor.add(new HTML("&nbsp;"));
	    tabAndAnchor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    tabAndAnchor.add(getUserPermissionEditAnchor(dto));
	    tabAndAnchor.add(new HTML("&nbsp;"));

	    Anchor newUserProfileAnchor = new Anchor("Add New User Profile");
	    newUserProfileAnchor.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    NewUserProfileDialog.getInstance().init("Add New User Profile");
		}
	    });
	    pageLayout.add(newUserProfileAnchor);
	    pageLayout.add(new HTML("&nbsp;"));
	    pageLayout.add(new HTML("&nbsp;"));
	    pageLayout.add(new RoundedCornerPanel(dto.getUserProfileName() + " profile", tabAndAnchor));
	}

    }

    private Anchor getUserPermissionEditAnchor(final UserProfileDto userProfileDto) {

	Anchor userPermissionEditAnchor = new Anchor("Edit");
	userPermissionEditAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		UserRoleName userRole = UserContext.getInstance().getUserDto().getAppUserRole();
		if (UserRoleName.USER_CRUD.equals(userRole)) {
		    EditUserProfileDialog.getInstance().init("Edit user details", userProfileDto);
		}
	    }
	});
	return userPermissionEditAnchor;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showUserPanel("");
    }

}
