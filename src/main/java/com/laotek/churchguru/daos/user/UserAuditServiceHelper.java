package com.laotek.churchguru.daos.user;

import com.laotek.churchguru.daos.BaseServiceHelper;

public class UserAuditServiceHelper extends BaseServiceHelper {
private static UserAuditServiceHelper userAuditServiceHelper = null;
	
	private UserAuditServiceHelper()
	{
		
	}
	
	public static UserAuditServiceHelper getInstance(){
		if (userAuditServiceHelper == null)
		{
			userAuditServiceHelper = new UserAuditServiceHelper();
		}
		return userAuditServiceHelper;
		
	}

	public UserAuditDao getService(){
		return (UserAuditDao) getService("userAuditDao");
	}
}
