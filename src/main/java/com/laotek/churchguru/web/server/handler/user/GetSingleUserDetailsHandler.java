package com.laotek.churchguru.web.server.handler.user;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.web.client.activity.user.GetSingleUserDetailsAction;
import com.laotek.churchguru.web.client.activity.user.GetSingleUserDetailsResult;
import com.laotek.churchguru.web.server.UserRoleHelper;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.PhoneDto;
import com.laotek.churchguru.web.shared.SecurityException;
import com.laotek.churchguru.web.shared.UserDto;

@Component
public class GetSingleUserDetailsHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetSingleUserDetailsAction, GetSingleUserDetailsResult> {

    @Autowired
    private UserDao userService;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    private UserAuditDao userAuditService;

    @Override
    public GetSingleUserDetailsResult execute(
	    GetSingleUserDetailsAction action, ExecutionContext context)
	    throws DispatchException {

	Organisation org = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	User user = userService.getUserByIdentifier(action.getUserIdentifier());

	if (!org.equals(user.getOrganisation())) {
	    throw new SecurityException();
	}

	GetSingleUserDetailsResult result = new GetSingleUserDetailsResult();
	result.setDto(convert(user));

	userAuditService.audit(action.getClientSessionId(),
		UserAuditTypeName.VIEW_USER_DETAILS, "Viewed details of "
			+ user.getFullname());

	return result;
    }

    @Override
    public Class<GetSingleUserDetailsAction> getActionType() {
	return GetSingleUserDetailsAction.class;
    }

    @Override
    public void rollback(GetSingleUserDetailsAction action,
	    GetSingleUserDetailsResult result, ExecutionContext context)
	    throws DispatchException {
    }

    public UserDto convert(User user) {
	UserDto dto = new UserDto();
	dto.setId(user.getId());
	dto.setIdentifier(user.getIdentifier());
	dto.setUsername(user.getUsername());
	dto.setEmailAddress(user.getEmailAddress());
	dto.setForenames(user.getForenames());
	dto.setSurname(user.getSurname());
	dto.setMobile(new PhoneDto(user.getMobileCountryCode(), user
		.getMobile()));
	dto.setUserAccountStatus(user.getUserAccountStatus());
	dto.setUserProfileName(user.getUserProfile().getName());
	dto.setUserProfileIdentifier(user.getUserProfile().getIdentifier());
	UserRoleHelper.populateDto(user, dto);
	return dto;
    }

}
