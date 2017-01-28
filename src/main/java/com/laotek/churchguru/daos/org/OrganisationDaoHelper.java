package com.laotek.churchguru.daos.org;

import com.laotek.churchguru.daos.BaseServiceHelper;

public class OrganisationDaoHelper extends BaseServiceHelper {

	private static OrganisationDaoHelper organisationDaoHelper = null;
	
	private OrganisationDaoHelper()
	{
		
	}
	
	public static OrganisationDaoHelper getInstance(){
		if (organisationDaoHelper == null)
		{
			organisationDaoHelper = new OrganisationDaoHelper();
		}
		return organisationDaoHelper;
		
	}

	public OrganisationDao getOrganisationDao(){
		return (OrganisationDao) getService("organisationDao");
	}
}
