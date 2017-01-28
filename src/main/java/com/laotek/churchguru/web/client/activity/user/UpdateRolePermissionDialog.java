package com.laotek.churchguru.web.client.activity.user;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.laotek.churchguru.web.client.UserContext;
import com.laotek.churchguru.web.client.widget.SelectItem;
import com.laotek.churchguru.web.shared.UserRoleDto;

@Deprecated
public class UpdateRolePermissionDialog extends DialogBox {

    private FlexTable layout = new FlexTable();

    private SelectItem adminPermission = new SelectItem("Allowed Access", true);

    private UserRoleDto adminRoleDto;

    public UpdateRolePermissionDialog(String caption) {
	super(true);
	setText(caption);
	setSize("400px", "150px");
	setGlassEnabled(true);
	setWidget();
    }

    private void setWidget() {
	layout.setWidth("100%");
	layout.setBorderWidth(0);

	layout.setWidget(2, 0, adminPermission);
	layout.setWidget(1, 1, new HTML("&nbsp;"));
	layout.setWidget(1, 2, new HTML("&nbsp;"));
	layout.setWidget(1, 3, new HTML("&nbsp;"));
	layout.setWidget(1, 4, new HTML("&nbsp;"));

	layout.setWidget(2, 1, new HTML("&nbsp;"));
	layout.setWidget(2, 2, cancel());
	layout.setWidget(2, 3, new HTML("&nbsp;"));
	layout.setWidget(2, 4, save());

	layout.setWidget(3, 1, new HTML("&nbsp;"));
	layout.setWidget(3, 2, new HTML("&nbsp;"));
	layout.setWidget(3, 3, new HTML("&nbsp;"));
	layout.setWidget(3, 4, new HTML("&nbsp;"));

	setWidget(layout);
    }

    private Anchor cancel() {
	Anchor anchor = new Anchor("Cancel");
	anchor.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		hide();
	    }
	});
	return anchor;
    }

    private Button save() {
	Button button = new Button("Update");
	button.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		UpdateRolePermissionAction action = new UpdateRolePermissionAction();

		action.setAdminRoleName(adminRoleDto.getAdminRoleName());
		UserContext
			.getInstance()
			.getDispatchClient()
			.execute(
				action,
				new AsyncCallback<UpdateRolePermissionResult>() {
				    @Override
				    public void onFailure(Throwable throwable) {
					hide();
				    }

				    @Override
				    public void onSuccess(
					    UpdateRolePermissionResult result) {
					hide();
					// ApplicationContext.getInstance().getPlaceController().goTo(
					// new
					// ManageUserRoleAndPermissionsPlace("roleAndPerm"));
				    }
				});

	    }
	});
	return button;
    }

    public void initAdminRoleDto(UserRoleDto adminRoleDto) {
	this.adminRoleDto = adminRoleDto;
	layout.setHTML(0, 0, "<b><big>"
		+ adminRoleDto.getAdminRoleName().getDesc() + "</big></b>");
	FlexCellFormatter formatter = layout.getFlexCellFormatter();
	formatter.setColSpan(0, 0, 5);
	formatter.setHorizontalAlignment(0, 0,
		HasHorizontalAlignment.ALIGN_CENTER);
    }

}
