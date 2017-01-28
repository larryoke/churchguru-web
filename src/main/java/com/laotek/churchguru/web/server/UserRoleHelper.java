package com.laotek.churchguru.web.server;

import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.UserRole;
import com.laotek.churchguru.model.shared.enums.UserRoleName;
import com.laotek.churchguru.web.shared.UserDto;
import com.laotek.churchguru.web.shared.UserProfileDto;

public class UserRoleHelper {

    // public static void populateNewProfileUserRoles(UserRoleDao userRoleDao,
    // UserProfileDto dto, UserProfile userProfile) {
    // UserRole role = userRoleDao.getUserRole(dto.getQuickEmailRole());
    // userProfile.getUserRoles().add(role);
    //
    // role = userRoleDao.getUserRole(dto.getDepartmentRole());
    // userProfile.getUserRoles().add(role);
    //
    // role = userRoleDao.getUserRole(dto.getMemberRole());
    // userProfile.getUserRoles().add(role);
    //
    // role = userRoleDao.getUserRole(dto.getSmsRole());
    // userProfile.getUserRoles().add(role);
    //
    // role = userRoleDao.getUserRole(dto.getAppUserRole());
    // userProfile.getUserRoles().add(role);
    //
    // role = userRoleDao.getUserRole(dto.getOrganisationRole());
    // userProfile.getUserRoles().add(role);
    // }

    public static void populateDto(User user, UserDto dto) {
	for (UserRole role : user.getUserProfile().getUserRoles()) {
	    if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setMemberRole(UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY);

	    } else if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setMemberRole(UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD);

	    } else if (UserRoleName.QUICK_EMAIL_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setQuickEmailRole(UserRoleName.QUICK_EMAIL_NOT_ALLOWED);

	    } else if (UserRoleName.QUICK_EMAIL_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setQuickEmailRole(UserRoleName.QUICK_EMAIL_ALLOWED);

	    } else if (UserRoleName.USER_VIEW_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_VIEW_NOT_ALLOWED);

	    } else if (UserRoleName.USER_VIEW_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_VIEW_ALLOWED);

	    } else if (UserRoleName.USER_CRUD.equals(role.getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_CRUD);

	    } else if (UserRoleName.ORGANISATION_DATA_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setOrganisationRole(UserRoleName.ORGANISATION_DATA_VIEW_ONLY);

	    } else if (UserRoleName.ORGANISATION_DATA_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setOrganisationRole(UserRoleName.ORGANISATION_DATA_CRUD);

	    } else if (UserRoleName.NOTIFICATION_VIEW_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_VIEW_NOT_ALLOWED);

	    } else if (UserRoleName.NOTIFICATION_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_VIEW_ONLY);

	    } else if (UserRoleName.NOTIFICATION_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_CRUD);
	    }
	}
    }

    public static void populateDto(UserProfile userProfile, UserProfileDto dto) {
	for (UserRole role : userProfile.getUserRoles()) {
	    if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setMemberRole(UserRoleName.MEMBERS_AND_GUESTS_DETAILS_VIEW_ONLY);

	    } else if (UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setMemberRole(UserRoleName.MEMBERS_AND_GUESTS_DETAILS_CRUD);

	    } else if (UserRoleName.QUICK_EMAIL_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setQuickEmailRole(UserRoleName.QUICK_EMAIL_NOT_ALLOWED);

	    } else if (UserRoleName.QUICK_EMAIL_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setQuickEmailRole(UserRoleName.QUICK_EMAIL_ALLOWED);

	    } else if (UserRoleName.USER_VIEW_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_VIEW_NOT_ALLOWED);

	    } else if (UserRoleName.USER_VIEW_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_VIEW_ALLOWED);

	    } else if (UserRoleName.USER_CRUD.equals(role.getUserRoleName())) {
		dto.setAppUserRole(UserRoleName.USER_CRUD);

	    } else if (UserRoleName.ORGANISATION_DATA_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setOrganisationRole(UserRoleName.ORGANISATION_DATA_VIEW_ONLY);

	    } else if (UserRoleName.ORGANISATION_DATA_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setOrganisationRole(UserRoleName.ORGANISATION_DATA_CRUD);

	    } else if (UserRoleName.NOTIFICATION_VIEW_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_VIEW_NOT_ALLOWED);

	    } else if (UserRoleName.NOTIFICATION_VIEW_ONLY.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_VIEW_ONLY);

	    } else if (UserRoleName.NOTIFICATION_CRUD.equals(role
		    .getUserRoleName())) {
		dto.setNotificationRole(UserRoleName.NOTIFICATION_CRUD);

	    } else if (UserRoleName.DONATION_VIEW_NOT_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setDonationRole(UserRoleName.DONATION_VIEW_NOT_ALLOWED);

	    } else if (UserRoleName.DONATION_VIEW_ALLOWED.equals(role
		    .getUserRoleName())) {
		dto.setDonationRole(UserRoleName.DONATION_VIEW_ALLOWED);
	    }
	}
    }
}
