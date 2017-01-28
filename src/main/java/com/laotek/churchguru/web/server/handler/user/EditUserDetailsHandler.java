package com.laotek.churchguru.web.server.handler.user;

import java.util.Date;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.web.client.activity.user.EditUserDetailsAction;
import com.laotek.churchguru.web.client.activity.user.EditUserDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserDto;

@Component
public class EditUserDetailsHandler extends AbstractCommandHandler implements
	ActionHandler<EditUserDetailsAction, EditUserDetailsResult> {

    @Autowired
    private UserDao userService;

    @Autowired
    private UserRoleDao userRoleService;

    @Autowired
    private UserAuditDao userAuditService;

    @Override
    public EditUserDetailsResult execute(EditUserDetailsAction action,
	    ExecutionContext context) throws DispatchException {
	UserDto dto = action.getCurrentUserDto();

	User user = userService.getUser(dto.getId());
	user.setForenames(dto.getForenames());
	user.setSurname(dto.getSurname());
	user.setMobileCountryCode(dto.getMobile().getCountryCode());
	user.setMobile(dto.getMobile().getNumber());
	user.setEmailAddress(dto.getEmailAddress());
	user.setUserAccountStatus(dto.getUserAccountStatus());
	user.setLastUpdatedDate(new Date());
	UserProfile userProfile = userRoleService
		.getUserProfileByIdentifier(dto.getUserProfileIdentifier());
	user.setUserProfile(userProfile);

	userService.updateUser(user);

	userAuditService.audit(action.getClientSessionId(),
		UserAuditTypeName.UPDATE_USER_DETAILS,
		"For " + user.getFullname());
	return new EditUserDetailsResult();
    }

    @Override
    public Class<EditUserDetailsAction> getActionType() {
	return EditUserDetailsAction.class;
    }

    @Override
    public void rollback(EditUserDetailsAction action,
	    EditUserDetailsResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
