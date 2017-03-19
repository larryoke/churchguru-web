package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.churchapp.general.GeneralChurchAppPlace;
import com.laotek.churchguru.web.client.activity.user.AllUserPlace;
import com.laotek.churchguru.web.client.activity.user.NewUserDialog;
import com.laotek.churchguru.web.client.activity.user.NewUserProfileDialog;
import com.laotek.churchguru.web.client.activity.user.UserProfilesPlace;
import com.laotek.churchguru.web.shared.UserDto;

public class AddMenu extends Composite {

    public AddMenu() {

	UserDto userDto = UserContext.getInstance().getUserDto();

	MenuBar menuBar = new MenuBar();
	menuBar.setAutoOpen(true);
	SafeHtmlBuilder builder = new SafeHtmlBuilder();

	{

	    MenuBar dropdown = new MenuBar(true);
	    dropdown.setAnimationEnabled(true);

	    dropdown.addItem("Welcome", new ScheduledCommand() {
		@Override
		public void execute() {
		    ApplicationContext.getInstance().getPlaceController().goTo(new GeneralChurchAppPlace("churchApp"));
		}
	    });

	    builder = new SafeHtmlBuilder();
	    builder.appendHtmlConstant("Home");
	    builder = new SafeHtmlBuilder();
	    builder.appendHtmlConstant("Home");
	    menuBar.addItem(new MenuItem(builder.toSafeHtml(), dropdown));
	}

	{
	    builder.appendHtmlConstant("Home");
	    builder = new SafeHtmlBuilder();
	    builder.appendHtmlConstant("Manage Users");

	    MenuBar dropdown = new MenuBar(true);
	    dropdown.setAnimationEnabled(true);

	    UserRoleName userRole = userDto.getAppUserRole();
	    if (UserRoleName.USER_CRUD.equals(userRole)) {
		dropdown.addItem("Add New User", new ScheduledCommand() {
		    @Override
		    public void execute() {
			NewUserDialog.getInstance().init("Add New User");
			NewUserDialog.getInstance().center();
		    }
		});
		dropdown.addItem("Add New User Profile", new ScheduledCommand() {
		    @Override
		    public void execute() {
			NewUserProfileDialog.getInstance().init("Add New User Profile");
			NewUserProfileDialog.getInstance().center();
		    }
		});
		dropdown.addSeparator();
		dropdown.addItem("All Users", new ScheduledCommand() {
		    @Override
		    public void execute() {
			ApplicationContext.getInstance().getPlaceController().goTo(new AllUserPlace("allUserPlace"));
		    }
		});
		dropdown.addItem("All User Profiles", new ScheduledCommand() {
		    @Override
		    public void execute() {
			ApplicationContext.getInstance().getPlaceController()
				.goTo(new UserProfilesPlace("userProfiles"));
		    }
		});
	    }
	    menuBar.addItem(new MenuItem(builder.toSafeHtml(), dropdown));
	}
	initWidget(menuBar);
    }

}
