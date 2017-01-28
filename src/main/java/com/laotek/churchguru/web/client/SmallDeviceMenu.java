package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserDialog;
import com.laotek.churchguru.web.shared.UserDto;

public class SmallDeviceMenu extends Composite {

    public SmallDeviceMenu() {

	UserDto userDto = UserContext.getInstance().getUserDto();

	MenuBar dropdown = new MenuBar(true);
	dropdown.setAnimationEnabled(true);

	UserRoleName userRole = userDto.getAppUserRole();
	if (UserRoleName.USER_CRUD.equals(userRole)) {
	    dropdown.addSeparator();
	    dropdown.addItem("User ", new ScheduledCommand() {
		@Override
		public void execute() {
		    NewUserDialog.getInstance().init("Add New User");
		    NewUserDialog.getInstance().center();
		    ApplicationContext.getInstance().getPlaceController()
			    .goTo(new AllUserPlace("allUserPlace"));
		}
	    });
	}

	MenuBar menuBar = new MenuBar();
	menuBar.setAutoOpen(true);

	final String image = "<img src='images/app/menu.png' />";

	SafeHtml addActivityImagePath = new SafeHtml() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public String asString() {
		return image;
	    }
	};

	MenuItem menuItem = new MenuItem(addActivityImagePath, dropdown);
	menuItem.setWidth("32px");
	menuBar.addItem(menuItem);
	menuBar.setWidth("32px");
	initWidget(menuBar);
    }

}
