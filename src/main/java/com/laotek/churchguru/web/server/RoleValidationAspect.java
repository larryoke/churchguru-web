package com.laotek.churchguru.web.server;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.user.UserRoleDao;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.HasDonationViewRole;
import com.laotek.churchguru.web.shared.role.member.HasMemberAndGuestCrudRole;
import com.laotek.churchguru.web.shared.role.member.HasMemberAndGuestViewOnlyRole;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationCrudRole;
import com.laotek.churchguru.web.shared.role.org.HasOrganisationViewRole;
import com.laotek.churchguru.web.shared.role.user.HasUserCrudRole;
import com.laotek.churchguru.web.shared.role.user.HasUserViewOnlyRole;

/**
 * 
 * @author churchguru
 * 
 */
@Aspect
@Component
public class RoleValidationAspect {

    private static Logger LOG = LoggerFactory.getLogger(RoleValidationAspect.class);

    @Autowired
    private UserRoleDao userRoleDao;

    @Around("within(com.laotek.churchguru.web.server.handler..*)")
    public Object validateRole(ProceedingJoinPoint joinPoint) throws Throwable {
	LOG.debug("validateRole->");
	if (joinPoint.getArgs().length > 0) {
	    Object arg = joinPoint.getArgs()[0];

	    if (arg instanceof AbstractDispatchAction) {
		String currentSessionId = ((AbstractDispatchAction) arg).getClientSessionId();

		if (arg instanceof HasMemberAndGuestCrudRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD);

		} else if (arg instanceof HasMemberAndGuestViewOnlyRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY);

		} else if (arg instanceof HasUserCrudRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.USER_CRUD);

		} else if (arg instanceof HasUserViewOnlyRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.USER_VIEW_ALLOWED);

		} else if (arg instanceof HasOrganisationViewRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.ORGANISATION_DATA_VIEW_ONLY);

		} else if (arg instanceof HasOrganisationCrudRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.ORGANISATION_DATA_CRUD);
		} else if (arg instanceof HasDonationViewRole) {
		    userRoleDao.verifyUserInRole(currentSessionId, UserRoleName.DONATION_VIEW_ALLOWED);
		}
	    }

	}
	Object object = joinPoint.proceed();
	LOG.debug("<-validateRole");
	return object;
    }

}
