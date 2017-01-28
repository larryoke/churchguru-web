package com.laotek.churchguru.web.server.handler.user;

import java.util.Date;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.shared.enums.UserAccountStatus;
import com.laotek.churchguru.model.shared.enums.UserAuditTypeName;
import com.laotek.churchguru.web.client.activity.user.SubmitUserDetailsAction;
import com.laotek.churchguru.web.client.activity.user.SubmitUserDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.UserDto;

@Component
public class SubmitUserDetailsHandler extends AbstractCommandHandler implements
	ActionHandler<SubmitUserDetailsAction, SubmitUserDetailsResult> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserAuditDao userAuditService;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public SubmitUserDetailsResult execute(SubmitUserDetailsAction action,
	    ExecutionContext context) throws DispatchException {

	Organisation organisation = organisationDao
		.getOrganisationFromClientSessionId(action.getClientSessionId());

	UserDto dto = action.getNewUserDto();

	String identifier = RandomStringUtils.random(30, true, true);

	User user = new User();
	user.setUsername(dto.getUsername());
	user.setForenames(dto.getForenames());
	user.setSurname(dto.getSurname());
	user.setMobileCountryCode(dto.getMobile().getCountryCode());
	user.setMobile(dto.getMobile().getNumber());
	user.setEmailAddress(dto.getEmailAddress());
	user.setUserAccountStatus(UserAccountStatus.INVITED);
	user.setOrganisation(organisation);
	user.setCreatedDate(new Date());
	user.setLastUpdatedDate(new Date());
	user.setIdentifier(identifier);

	UserProfile userProfile = userRoleDao.getUserProfileByIdentifier(dto
		.getUserProfileIdentifier());

	user.setUserProfile(userProfile);
	userDao.newUser(user);

	userDao.updateUser(user);

	userAuditService.audit(action.getClientSessionId(),
		UserAuditTypeName.ADD_USER, "User " + user.getFullname()
			+ "added");

	// Send the email
	userDao.createNewUserSetupEmail(identifier);

	return new SubmitUserDetailsResult();
    }

    @Override
    public Class<SubmitUserDetailsAction> getActionType() {
	return SubmitUserDetailsAction.class;
    }

    @Override
    public void rollback(SubmitUserDetailsAction action,
	    SubmitUserDetailsResult result, ExecutionContext context)
	    throws DispatchException {

    }

}
