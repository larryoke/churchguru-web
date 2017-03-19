package com.laotek.churchguru.web.client.activity.user;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.laotek.churchguru.model.shared.enums.EmailRecipientType;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;
import com.laotek.churchguru.web.client.ApplicationContext;
import com.laotek.churchguru.web.client.UserRowSelectionContext;
import com.laotek.churchguru.web.client.activity.SendQuickEmailDialog;
import com.laotek.churchguru.web.client.widget.IdCheckBox;
import com.laotek.churchguru.web.client.widget.RoundedCornerPanel;
import com.laotek.churchguru.web.client.widget.UserListTable;
import com.laotek.churchguru.web.shared.PhoneDto;
import com.laotek.churchguru.web.shared.UserDto;

public class AllUserViewImpl extends Composite implements AllUserView {

    interface MyUiBinder extends UiBinder<Widget, AllUserViewImpl> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    VerticalPanel pageLayout;

    private UserSelectionMenu menu = new UserSelectionMenu();
    private UserListTable userGrid = new UserListTable();
    private CheckBox mainCheckBox = new CheckBox();

    private Presenter presenter;

    public AllUserViewImpl() {
	initWidget(uiBinder.createAndBindUi(this));

	pageLayout.add(new HTML("&nbsp;"));

	HorizontalPanel topPanel = new HorizontalPanel();
	topPanel.add(new Image("images/app/users.png"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("&nbsp;"));
	topPanel.add(new HTML("<h2>All Users</h2>"));

	FlexTable flexTable = new FlexTable();
	flexTable.setBorderWidth(0);
	flexTable.setWidget(0, 0, topPanel);
	flexTable.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	// flexTable.setWidth("700px");

	pageLayout.add(new RoundedCornerPanel(flexTable));

	userGrid.setCellPadding(2);
	userGrid.setCellSpacing(0);
	userGrid.setBorderWidth(0);
	userGrid.setWidth("800px");

	HorizontalPanel hpanel = new HorizontalPanel();
	hpanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
	hpanel.add(mainCheckBox);
	hpanel.add(new HTML("&nbsp;"));
	hpanel.add(menu);

	VerticalPanel outerPanel = new VerticalPanel();
	outerPanel.add(hpanel);
	outerPanel.add(userGrid);

	Button userButton = new Button("Add New User");
	userButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NewUserDialog.getInstance().init("Add New User");
	    }
	});
	Button userProfileButton = new Button("Add New User Profile");
	userProfileButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		NewUserProfileDialog.getInstance().init("Add New User Profile");
	    }
	});
	pageLayout.add(new RoundedCornerPanel(outerPanel, userButton, userProfileButton));

    }

    @Override
    public void setPresenter(Presenter presenter) {
	this.presenter = presenter;
    }

    @Override
    public void initUsers(List<UserDto> dtos) {

	mainCheckBox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (mainCheckBox.getValue()) {
		    userGrid.selectAllPageCheckBoxes(true);
		} else {
		    userGrid.selectAllPageCheckBoxes(false);
		}

		if (UserRowSelectionContext.getInstance().size() > 0) {
		    menu.enableButtons(true);
		} else {
		    menu.enableButtons(false);
		}
	    }
	});

	userGrid.setWidget(0, 1, new HTML("<b>Username</b>"));
	userGrid.setWidget(0, 2, new HTML("<b>Full Name</b>"));
	userGrid.setWidget(0, 3, new HTML("<b>Email Address</b>"));
	userGrid.setWidget(0, 4, new HTML("<b>Mobile</b>"));
	userGrid.setWidget(0, 5, new HTML("<b>Account Status</b>"));

	for (int row = 0; row < dtos.size(); row++) {

	    userGrid.setWidget(row + 1, 0, initCheckBox(dtos.get(row), row + 1, row + 1, menu));

	    // process user account status
	    UserAccountStatus userAccountStatus = dtos.get(row).getUserAccountStatus();
	    String status = userAccountStatus.getDesc();

	    final String fullname = dtos.get(row).getForenames() + " " + dtos.get(row).getSurname();

	    String email = dtos.get(row).getEmailAddress();
	    PhoneDto mobileDto = dtos.get(row).getMobile();
	    String mobile = mobileDto.getNumber();

	    // process username
	    String username = dtos.get(row).getUsername();
	    if (username == null || (username != null && username.equals(""))) {
		username = "-";
	    } else if (UserAccountStatus.ACTIVE.equals(userAccountStatus)) {
		username = "<b>" + username + "</b>";
		email = "<b>" + email + "</b>";
		mobile = "<b>" + mobile + "</b>";
		status = "<b>" + status + "</b>";
	    }

	    // show username
	    userGrid.setWidget(row + 1, 1, new HTML(username));
	    userGrid.getCellFormatter().setHorizontalAlignment(row + 1, 1, HasHorizontalAlignment.ALIGN_CENTER);

	    Anchor anchor = new Anchor(fullname);
	    userGrid.setWidget(row + 1, 2, anchor);
	    final String identifier = dtos.get(row).getIdentifier();
	    anchor.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
		    ApplicationContext.getInstance().getPlaceController().goTo(new SingleUserPlace(identifier));
		}
	    });

	    final String currentEmail = dtos.get(row).getEmailAddress();
	    ;
	    Anchor emailAnchor = new Anchor(currentEmail);
	    emailAnchor.addClickHandler(new ClickHandler() {

		@Override
		public void onClick(ClickEvent event) {
		    SendQuickEmailDialog.getInstance().init(EmailRecipientType.USER, identifier, currentEmail);

		}
	    });
	    userGrid.setWidget(row + 1, 3, emailAnchor);
	    userGrid.setWidget(row + 1, 4, new HTML());
	    ;
	    userGrid.setWidget(row + 1, 5, new HTML(status));

	    styleRow(userGrid, dtos.get(row).getIdentifier(), row + 1);

	}
	userGrid.validateMainCheckBox(mainCheckBox);
    }

    private FlexTable initCheckBox(final UserDto dto, final int userNum, final int currentRowIndex,
	    final UserSelectionMenu menu) {
	final IdCheckBox checkbox = new IdCheckBox(dto.getIdentifier(),
		UserRowSelectionContext.getInstance().isSelected(dto.getIdentifier()));

	checkbox.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (checkbox.getValue()) {
		    userGrid.styleRowSelection(currentRowIndex);
		    UserRowSelectionContext.getInstance().selectId(dto.getIdentifier(), true);
		} else {
		    userGrid.removeStyleRowSelection(currentRowIndex);
		    UserRowSelectionContext.getInstance().selectId(dto.getIdentifier(), false);
		}
		userGrid.validateMainCheckBox(mainCheckBox);

		if (UserRowSelectionContext.getInstance().size() > 0) {
		    menu.enableButtons(true);
		} else {
		    menu.enableButtons(false);
		}
	    }
	});
	FlexTable firstColAsTab = new FlexTable();
	firstColAsTab.setWidget(0, 0, checkbox);
	firstColAsTab.setHTML(0, 1, "" + userNum);
	return firstColAsTab;
    }

    private void styleRow(UserListTable userListTable, String id, int currentRowIndex) {
	if (UserRowSelectionContext.getInstance().isSelected(id)) {
	    userListTable.styleRowSelection(currentRowIndex);
	} else {
	    userListTable.removeStyleRowSelection(currentRowIndex);
	}
    }

    @Override
    public void initTab() {
	// MainMenuContext.getInstance().showUserPanel("Create a new user");
    }

    @Override
    public void init() {
	Window.setTitle("All Users");
    }

}
