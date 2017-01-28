package com.laotek.churchguru.daos.user;

import java.util.List;

import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.UserProfile;
import com.laotek.churchguru.model.UserRole;
import com.laotek.churchguru.model.shared.enums.UserRoleName;

public interface UserRoleDao {
    List<UserRole> getUserRoles();

    void createUserProfile(UserProfile userProfile, List<UserRoleName> roleNames);

    void updateUserProfile(UserProfile userProfile);

    List<UserProfile> getUserProfiles(Organisation organisation);

    UserProfile getUserProfileByName(String profileName,
	    Organisation organisation);

    UserProfile getUserProfileByIdentifier(String identifier);

    UserRole getUserRole(UserRoleName userRoleName);

    void verifyUserInRole(String currentSessionId, UserRoleName userRoleName);

    void load(long orgId);

    void initialiseAdminUserProfile(String adminUserIdentiier);

}
