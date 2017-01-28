package com.laotek.churchguru.daos.user;

import com.laotek.churchguru.daos.BaseServiceHelper;

public class UserServiceHelper extends BaseServiceHelper {
	
	private static UserServiceHelper userServiceHelper = null;	
	private UserServiceHelper(){}
	
	public static UserServiceHelper getInstance(){
		if (userServiceHelper == null)
		{
			userServiceHelper = new UserServiceHelper();
		}
		return userServiceHelper;
		
	}

	public UserDao getService(){
		return (UserDao) getService("userDao");
	}
}
