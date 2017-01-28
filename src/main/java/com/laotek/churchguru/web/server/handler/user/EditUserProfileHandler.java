package com.laotek.churchguru.web.server.handler.user;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.UserRole;
import com.laotek.churchguru.web.client.activity.user.EditUserProfileAction;
import com.laotek.churchguru.web.client.activity.user.EditUserProfileResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserProfileDto;

@Component
public class EditUserProfileHandler extends AbstractCommandHandler implements
	ActionHandler<EditUserProfileAction, EditUserProfileResult> {

    @Autowired
    private UserDao userService;

    @Autowired
    private UserRoleDao userRoleService;

    @Autowired
    private UserAuditDao userAuditService;

    @Override
    public EditUserProfileResult execute(EditUserProfileAction action,
	    ExecutionContext context) throws DispatchException {
	UserProfileDto dto = action.getProfileUserDto();

	UserProfile userProfile = userRoleService
		.getUserProfileByIdentifier(dto.getUserProfileIdentifier());

	userProfile.getUserRoles().clear();

	UserRole role = userRoleService.getUserRole(dto.getQuickEmailRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getDepartmentRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getMemberRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getSmsRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getAppUserRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getOrganisationRole());
	userProfile.getUserRoles().add(role);

	role = userRoleService.getUserRole(dto.getNotificationRole());
	userProfile.getUserRoles().add(role);

	userRoleService.updateUserProfile(userProfile);

	return new EditUserProfileResult();
    }

    @Override
    public Class<EditUserProfileAction> getActionType() {
	return EditUserProfileAction.class;
    }

    @Override
    public void rollback(EditUserProfileAction action,
	    EditUserProfileResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
