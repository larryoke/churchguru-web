package com.laotek.churchguru.web.server.handler.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.user.SubmitUserDetailsResult;
import com.laotek.churchguru.web.client.activity.user.SubmitUserProfileAction;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.DuplicateUserProfileException;
import com.laotek.churchguru.web.shared.UserProfileDto;

@Component
public class SubmitUserProfileHandler extends AbstractCommandHandler implements
	ActionHandler<SubmitUserProfileAction, SubmitUserDetailsResult> {

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserAuditDao userAuditService;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public SubmitUserDetailsResult execute(SubmitUserProfileAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation organisation = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	UserProfileDto dto = action.getNewUserProfileDto();

	// avoid duplicate
	UserProfile duplicate = userRoleDao.getUserProfileByName(
		dto.getUserProfileName(), organisation);
	if (duplicate != null
		&& dto.getUserProfileName().equalsIgnoreCase(
			duplicate.getName())) {
	    // TODO
	    throw new DuplicateUserProfileException(duplicate.getName()
		    + " already exists. Please choose a different profile name");
	}

	UserProfile userProfile = new UserProfile();
	userProfile.setName(dto.getUserProfileName());
	userProfile.setCreatedDate(new Date());
	userProfile.setLastUpdatedDate(new Date());
	userProfile.setOrganisation(organisation);
	userProfile.setIdentifier(RandomStringUtils.random(30, true, true));

	List<UserRoleName> roleNames = new ArrayList<UserRoleName>();
	roleNames.add(dto.getAppUserRole());
	roleNames.add(dto.getDepartmentRole());
	roleNames.add(dto.getMemberRole());
	roleNames.add(dto.getOrganisationRole());
	roleNames.add(dto.getQuickEmailRole());
	roleNames.add(dto.getSmsRole());
	roleNames.add(dto.getNotificationRole());

	userRoleDao.createUserProfile(userProfile, roleNames);

	userAuditService.audit(action.getClientSessionId(),
		UserAuditTypeName.ADD_USER_PROFILE, "User Profile "
			+ userProfile.getName() + " added");

	return new SubmitUserDetailsResult();
    }

    @Override
    public Class<SubmitUserProfileAction> getActionType() {
	return SubmitUserProfileAction.class;
    }

    @Override
    public void rollback(SubmitUserProfileAction action,
	    SubmitUserDetailsResult result, ExecutionContext context)
	    throws DispatchException {

    }

}
