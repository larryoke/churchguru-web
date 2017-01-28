package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserDialog;
import com.laotek.churchguru.web.shared.UserDto;

public class AddMenu extends Composite {

    public AddMenu() {

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
	SafeHtmlBuilder builder = new SafeHtmlBuilder();
	builder.appendHtmlConstant("Add");
	menuBar.setAutoOpen(true);
	menuBar.addItem(new MenuItem(builder.toSafeHtml(), dropdown));
	initWidget(menuBar);
    }

}
