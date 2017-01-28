package com.laotek.churchguru.daos.user;

import com.laotek.churchguru.daos.BaseServiceHelper;

public class UserRoleServiceHelper extends BaseServiceHelper {
    private static UserRoleServiceHelper userRoleServiceHelper = null;

    private UserRoleServiceHelper() {

    }

    public static UserRoleServiceHelper getInstance() {
	if (userRoleServiceHelper == null) {
	    userRoleServiceHelper = new UserRoleServiceHelper();
	}
	return userRoleServiceHelper;

    }

    public UserRoleDao getService() {
	return (UserRoleDao) getService("userRoleDao");
    }
}
