package com.laotek.churchguru.daos;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context = null;

    public void setApplicationContext(ApplicationContext applicationContext)
	    throws BeansException {
	context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
	return context;
    }
}
