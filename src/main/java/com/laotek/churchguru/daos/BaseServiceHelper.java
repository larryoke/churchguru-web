package com.laotek.churchguru.daos;

import org.springframework.context.ApplicationContext;

public class BaseServiceHelper {
	protected static Object getService(String service){
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		
		return context.getBean(service);
	}

}
