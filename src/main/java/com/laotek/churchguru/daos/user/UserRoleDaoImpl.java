package com.laotek.churchguru.daos.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.daos.shared.exceptions.ExpiredSessionException;
import com.laotek.churchguru.daos.shared.exceptions.UserRoleMissingException;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.UserRole;
import com.laotek.churchguru.model.shared.enums.UserRoleName;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserRoleDaoImpl extends BaseSessionFactory implements UserRoleDao {

    private static Logger LOG = LoggerFactory.getLogger(UserRoleDaoImpl.class);

    @Override
    public UserRole getUserRole(UserRoleName userRoleName) {
	LOG.debug("getUserRole->");
	Query query = getCurrentSession().createQuery(
		"from UserRole opr where opr.userRoleName = :userRoleName");
	query.setParameter("userRoleName", userRoleName);
	LOG.debug("<-getUserRole");
	return (UserRole) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRole> getUserRoles() {
	LOG.debug("getUserRoles->");
	Query query = getCurrentSession().createQuery("from UserRole role");
	LOG.debug("<-getUserRoles");
	return query.list();
    }

    @Override
    public void load(long orgId) {
	LOG.debug("load->");
	List<UserRole> userRoles = getUserRoles();

	// create roles
	for (UserRoleName name : UserRoleName.values()) {
	    UserRole role = new UserRole(name);
	    if (!userRoles.contains(role)) {
		getCurrentSession().persist(role);
	    }
	}
	createSuperUserProfile(orgId);
	LOG.debug("<-load");
    }

    private void createSuperUserProfile(long orgId) {
	LOG.debug("createSuperUserProfile->");
	Organisation org = (Organisation) getCurrentSession().get(
		Organisation.class, orgId);
	UserProfile exists = getUserProfileByName("admin", org);

	if (exists == null) {

	    UserProfile userProfile = createUserProfile("admin", orgId);

	    for (UserRole role : getUserRoles()) {

		// if
		// (UserRoleName.DEPARTMENT_CRUD.equals(role.getUserRoleName()))
		// {
		// userProfile.getUserRoles().add(role);
		//
		// } else

		if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.equals(role
			.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);

		} else if (UserRoleName.QUICK_EMAIL_ALLOWED.equals(role
			.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);

		    // } else if
		    // (UserRoleName.SMS_SEND_AND_BUY_ALLOWED.equals(role
		    // .getUserRoleName())) {
		    // userProfile.getUserRoles().add(role);

		} else if (UserRoleName.USER_CRUD
			.equals(role.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);

		} else if (UserRoleName.ORGANISATION_DATA_CRUD.equals(role
			.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);

		} else if (UserRoleName.NOTIFICATION_CRUD.equals(role
			.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);

		} else if (UserRoleName.DONATION_VIEW_ALLOWED.equals(role
			.getUserRoleName())) {
		    userProfile.getUserRoles().add(role);
		}
	    }
	    updateUserProfile(userProfile);

	}
	LOG.debug("<-createSuperUserProfile");
    }

    private UserProfile createUserProfile(String name, long orgId) {
	LOG.debug("createUserProfile->");

	Organisation org = (Organisation) getCurrentSession().get(
		Organisation.class, orgId);

	UserProfile userProfile = new UserProfile();
	userProfile.setName(name);
	userProfile.setIdentifier(RandomStringUtils.random(10, true, true));
	userProfile.setLastUpdatedDate(new Date());
	userProfile.setCreatedDate(new Date());
	userProfile.setOrganisation(org);
	LOG.debug("<-createUserProfile");
	return userProfile;
    }

    @Override
    public void verifyUserInRole(String currentSessionId,
	    UserRoleName userRoleName) {
	LOG.debug("verifyUserInRole->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", currentSessionId);
	@SuppressWarnings("unchecked")
	User user = (User) query.uniqueResult();

	if (user == null) {
	    throw new ExpiredSessionException();
	}

	UserProfile userProfile = user.getUserProfile();
	for (UserRole role : userProfile.getUserRoles()) {
	    UserRoleName current = role.getUserRoleName();
	    if (current.equals(userRoleName)) {
		return;
	    } else {
		for (UserRoleName child : current.getChildren()) {
		    if (child.equals(userRoleName)) {
			LOG.debug("<-verifyUserInRole");
			return;
		    }
		}
	    }
	}
	throw new UserRoleMissingException();
    }

    @Override
    public UserProfile getUserProfileByName(String profileName,
	    Organisation organisation) {
	LOG.debug("getUserProfileByName->");
	Query query = getCurrentSession()
		.createQuery(
			"from UserProfile o where o.name = :name and  o.organisation = :organisation");
	query.setParameter("name", profileName);
	query.setParameter("organisation", organisation);
	@SuppressWarnings("unchecked")
	UserProfile userProfile = (UserProfile) query.uniqueResult();
	LOG.debug("<-getUserProfileByName");
	return userProfile;
    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
	LOG.debug("updateUserProfile->");
	userProfile = (UserProfile) getCurrentSession().merge(userProfile);
	getCurrentSession().update(userProfile);
	LOG.debug("<-updateUserProfile");
    }

    @Override
    public List<UserProfile> getUserProfiles(Organisation organisation) {
	LOG.debug("getUserProfiles->");
	Query query = getCurrentSession().createQuery(
		"from UserProfile o where o.organisation = :organisation");
	query.setParameter("organisation", organisation);

	@SuppressWarnings("unchecked")
	List<UserProfile> profiles = query.list();
	for (UserProfile profile : profiles) {
	    profile.getUserRoles().size();
	    profile.getUsers().size();
	}
	LOG.debug("<-getUserProfiles");
	return profiles;
    }

    @Override
    public void createUserProfile(UserProfile userProfile,
	    List<UserRoleName> roleNames) {
	LOG.debug("createUserProfile->");
	for (UserRoleName userRoleName : roleNames) {
	    UserRole userRole = getUserRole(userRoleName);
	    userProfile.getUserRoles().add(userRole);
	}
	getCurrentSession().persist(userProfile);
	LOG.debug("<-createUserProfile");
    }

    @Override
    public UserProfile getUserProfileByIdentifier(String identifier) {
	LOG.debug("getUserProfileByIdentifier->");
	Query query = getCurrentSession().createQuery(
		"from UserProfile o where o.identifier = :identifier");
	query.setParameter("identifier", identifier);
	@SuppressWarnings("unchecked")
	UserProfile userProfile = (UserProfile) query.uniqueResult();
	userProfile.getUserRoles().size();
	LOG.debug("<-getUserProfileByIdentifier");
	return userProfile;
    }

    @Override
    public void initialiseAdminUserProfile(String adminUserIdentiier) {
	LOG.debug("initialiseAdminUserProfile->");
	Query query = getCurrentSession().createQuery(
		"from User o where o.identifier = :identifier");
	query.setParameter("identifier", adminUserIdentiier);
	User user = (User) query.uniqueResult();
	UserProfile userProfile = user.getUserProfile();
	for (UserRole role : getUserRoles()) {

	    // if (UserRoleName.DEPARTMENT_CRUD.equals(role.getUserRoleName()))
	    // {
	    // userProfile.getUserRoles().add(role);
	    //
	    // } else

	    if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.equals(role
		    .getUserRoleName())) {
		userProfile.getUserRoles().add(role);

	    } else if (UserRoleName.QUICK_EMAIL_ALLOWED.equals(role
		    .getUserRoleName())) {
		userProfile.getUserRoles().add(role);

		// } else if (UserRoleName.SMS_SEND_AND_BUY_ALLOWED.equals(role
		// .getUserRoleName())) {
		// userProfile.getUserRoles().add(role);

	    } else if (UserRoleName.USER_CRUD.equals(role.getUserRoleName())) {
		userProfile.getUserRoles().add(role);

	    } else if (UserRoleName.ORGANISATION_DATA_CRUD.equals(role
		    .getUserRoleName())) {
		userProfile.getUserRoles().add(role);

	    } else if (UserRoleName.NOTIFICATION_CRUD.equals(role
		    .getUserRoleName())) {
		userProfile.getUserRoles().add(role);

	    } else if (UserRoleName.DONATION_VIEW_ALLOWED.equals(role
		    .getUserRoleName())) {
		userProfile.getUserRoles().add(role);
	    }
	}
	updateUserProfile(userProfile);
	LOG.debug("<-initialiseAdminUserProfile");
    }
}
