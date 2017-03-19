package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.activity.SendQuickEmailDialog;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.shared.PhoneDto;
import com.laotek.churchguru.web.shared.UserAuditDto;
import com.laotek.churchguru.web.shared.UserDto;

public class SingleUserViewImpl extends Composite implements SingleUserView {
    SimplePanel viewPanel = new SimplePanel();
    Element nameSpan = DOM.createSpan();

    interface MyUiBinder extends UiBinder<Widget, SingleUserViewImpl> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private Presenter presenter;

    @UiField
    VerticalPanel pageLayout;

    private FlexTable userGrid = new FlexTable();

    private FlexTable permissionGrid = new FlexTable();

    private FlexTable history = new FlexTable();

    private FlexTable auditGrid = new FlexTable();

    private HTML fullname = new HTML();

    private UserDto currentUserDto;

    private int auditRowIndex = 0;
    private int rowIndexOfMoreAuditTrailAnchor = 0;

    private Anchor moreAuditTrailAnchor = new Anchor("View more...");

    public SingleUserViewImpl() {
	initWidget(uiBinder.createAndBindUi(this));

	String width = "500px";
	userGrid.setWidth(width);
	permissionGrid.setWidth(width);
	history.setWidth(width);
	auditGrid.setWidth(width);

	pageLayout.add(new HTML("&nbsp;"));
	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/user_details.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(fullname);
	pageLayout.add(topPanel);

	moreAuditTrailAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		presenter.collectAuditTrail();
	    }
	});

	Anchor newAdminAnchor = new Anchor("Create a new user");
	newAdminAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NewUserDialog.getInstance().init("Add new user");
	    }
	});

	Anchor backAnchor = new Anchor("Back to all users");
	backAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		ApplicationContext.getInstance().getPlaceController().goTo(new AllUserPlace("allUsers"));
	    }
	});

	HorizontalPanel linksPanel = new HorizontalPanel();
	linksPanel.add(backAnchor);
	linksPanel.add(new HTML("&nbsp;"));
	linksPanel.add(new HTML("&nbsp;"));
	linksPanel.add(new HTML("|"));
	linksPanel.add(new HTML("&nbsp;"));
	linksPanel.add(new HTML("&nbsp;"));
	linksPanel.add(newAdminAnchor);
	pageLayout.add(linksPanel);
	pageLayout.add(new HTML("&nbsp;"));

	pageLayout.add(new HTML("<h3>User Details, Roles and Permissions</h3>"));
	userGrid.setBorderWidth(0);
	userGrid.setCellPadding(0);
	userGrid.setCellSpacing(0);

	permissionGrid.setBorderWidth(0);
	permissionGrid.setCellPadding(0);
	permissionGrid.setCellSpacing(0);

	history.setWidget(0, 0, new HTML("&nbsp;"));
	history.setWidget(1, 0, new HTML("&nbsp;"));
	history.setWidget(2, 0, new HTML("&nbsp;"));

	TabPanel tabPanel = new TabPanel();
	tabPanel.add(userGrid, "Personal Details");
	tabPanel.add(permissionGrid, "Profile Details");
	tabPanel.add(history, "History");
	tabPanel.setAnimationEnabled(true);
	tabPanel.selectTab(0);

	VerticalPanel tabAndAnchor = new VerticalPanel();
	tabAndAnchor.add(tabPanel);
	tabAndAnchor.add(new HTML("&nbsp;"));
	tabAndAnchor.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	tabAndAnchor.add(getUserDetailsEditAnchor());
	tabAndAnchor.add(new HTML("&nbsp;"));

	pageLayout.add(new RoundedCornerPanel("User Details", tabAndAnchor));

	pageLayout.add(new HTML("<h3>User Activity History</h3>"));
	VerticalPanel auditInnerPanel = new VerticalPanel();
	auditInnerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	auditInnerPanel.add(startAuditTrail());
	auditInnerPanel.add(auditGrid);
	pageLayout.add(new RoundedCornerPanel(auditInnerPanel));
	auditGrid.setBorderWidth(0);
	auditGrid.setCellPadding(0);
	auditGrid.setCellSpacing(0);

	pageLayout.add(getBackToTopAnchor());
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
    public void initUser(final UserDto dto) {

	currentUserDto = dto;
	fullname.setHTML("<h2>" + dto.getFullname() + "</h2>");

	userGrid.removeAllRows();
	userGrid.setHTML(0, 0, "&nbsp;");
	userGrid.setHTML(1, 0, "<b>User Name</b>");
	userGrid.setHTML(1, 1, dto.getUsername());
	userGrid.setHTML(2, 0, "<b>Full Name</b>");
	userGrid.setHTML(2, 1, dto.getFullname());
	userGrid.setHTML(3, 0, "<b>Email Address</b>");

	Anchor emailAnchor = new Anchor(dto.getEmailAddress());
	emailAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		SendQuickEmailDialog.getInstance().init(EmailRecipientType.USER, dto.getIdentifier(),
			dto.getEmailAddress());
	    }
	});
	userGrid.setWidget(3, 1, emailAnchor);
	userGrid.setHTML(4, 0, "<b>Mobile Number</b>");

	HorizontalPanel mobilePanel = new HorizontalPanel();
	mobilePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	mobilePanel.add(new Image(dto.getMobile().getCountryCode().getImageUrl()));
	mobilePanel.add(new HTML("&nbsp;"));
	mobilePanel.add(new HTML(dto.getMobile().getNumber()));
	userGrid.setWidget(4, 1, mobilePanel);

	userGrid.setHTML(5, 0, "<b>Account Status</b>");
	userGrid.setHTML(5, 1, dto.getUserAccountStatus().getDesc());
	userGrid.setHTML(6, 0, "&nbsp;");

	permissionGrid.removeAllRows();
	permissionGrid.setHTML(0, 0, "&nbsp;");
	permissionGrid.setHTML(1, 0, "<b>Profile Name</b>");
	permissionGrid.setHTML(1, 1, String.valueOf(dto.getUserProfileName()));
	permissionGrid.setHTML(2, 0, "<b>Email Permission</b>");
	permissionGrid.setHTML(2, 1, String.valueOf(dto.getQuickEmailRole().getDesc()));
	permissionGrid.setHTML(3, 0, "<b>SMS Permission</b>");
	permissionGrid.setHTML(3, 1, String.valueOf(dto.getSmsRole().getDesc()));
	permissionGrid.setHTML(4, 0, "<b>Member Adding Permission</b>");
	permissionGrid.setHTML(4, 1, String.valueOf(dto.getMemberRole().getDesc()));
	permissionGrid.setHTML(5, 0, "<b>Department</b>");
	permissionGrid.setHTML(5, 1, String.valueOf(dto.getDepartmentRole().getDesc()));
	permissionGrid.setHTML(6, 0, "<b>Users</b>");
	permissionGrid.setHTML(6, 1, String.valueOf(dto.getAppUserRole().getDesc()));

	permissionGrid.setHTML(7, 0, "<b>Organisation</b>");
	permissionGrid.setHTML(7, 1, String.valueOf(dto.getOrganisationRole().getDesc()));

	permissionGrid.setHTML(8, 0, "<b>Notifications</b>");
	permissionGrid.setHTML(8, 1, String.valueOf(dto.getNotificationRole().getDesc()));

	permissionGrid.setHTML(9, 0, "&nbsp;");
	permissionGrid.setHTML(9, 1, "&nbsp;");

	auditGrid.removeAllRows();

    }

    private Anchor startAuditTrail() {
	final Anchor auditAnchor = new Anchor("View History");
	auditAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		presenter.collectAuditTrail();
	    }
	});
	return auditAnchor;
    }

    private Anchor getUserDetailsEditAnchor() {
	Anchor userDetailsEditAnchor = new Anchor("Edit");
	userDetailsEditAnchor.addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		EditUserDialog.getInstance().init("Edit user details", currentUserDto);
	    }
	});
	return userDetailsEditAnchor;
    }

    private Anchor getBackToTopAnchor() {
	Anchor backToTopAnchor = new Anchor("Back To Top");
	backToTopAnchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent arg0) {
		ApplicationContext.getInstance().getMainScrollPanel().scrollToTop();
	    }
	});
	return backToTopAnchor;
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showUserPanel("");
    }

    @Override
    public void addUserAuditTrail(List<UserAuditDto> dtos, boolean hasMore) {
	if (dtos.size() > 0) {
	    FlexCellFormatter formatter = auditGrid.getFlexCellFormatter();

	    if (auditRowIndex == 0) {
		auditGrid.setHTML(auditRowIndex, 0, "<b>Type</b>");
		auditGrid.setHTML(auditRowIndex, 1, "<b>Description</b>");
		auditGrid.setHTML(auditRowIndex, 2, "<b>Time</b>");
		auditRowIndex = auditRowIndex + 1;
	    }

	    for (int i = 0; i < dtos.size(); i++) {
		if (rowIndexOfMoreAuditTrailAnchor == auditRowIndex) {
		    moreAuditTrailAnchor.removeFromParent();
		    auditGrid.setHTML(rowIndexOfMoreAuditTrailAnchor, 0, "more...");
		    auditRowIndex = auditRowIndex + 1;
		}
		auditGrid.setHTML(auditRowIndex, 0, dtos.get(i).getUserAuditTypeName().getDesc());
		auditGrid.setHTML(auditRowIndex, 1, dtos.get(i).getDetails());
		auditGrid.setHTML(auditRowIndex, 2, dtos.get(i).getDateAndTime());
		formatter.setStylePrimaryName(auditRowIndex, 0, "dottedBottomBorder");
		formatter.setStylePrimaryName(auditRowIndex, 1, "dottedBottomBorder");
		formatter.setStylePrimaryName(auditRowIndex, 2, "dottedBottomBorder");
		auditRowIndex = auditRowIndex + 1;
	    }

	    if (hasMore) {
		formatter = auditGrid.getFlexCellFormatter();
		formatter.setColSpan(auditRowIndex, 0, 3);
		formatter.setHorizontalAlignment(auditRowIndex, 0, HasHorizontalAlignment.ALIGN_CENTER);
		auditGrid.setWidget(auditRowIndex, 0, moreAuditTrailAnchor);
		rowIndexOfMoreAuditTrailAnchor = auditRowIndex;
	    }

	} else if (auditRowIndex == 0) {
	    Window.alert("User has no history.");

	} else {
	    Window.alert("End of history is reached.");
	}
    }

    private HorizontalPanel createMobilePanel(PhoneDto mobileDto, Anchor anchor) {
	HorizontalPanel mobilePanel = new HorizontalPanel();
	mobilePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	mobilePanel.add(new Image(mobileDto.getCountryCode().getImageUrl()));
	mobilePanel.add(new HTML("&nbsp;"));
	mobilePanel.add(anchor);
	return mobilePanel;
    }

}
