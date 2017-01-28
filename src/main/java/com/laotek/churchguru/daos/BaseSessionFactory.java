package com.laotek.churchguru.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseSessionFactory {

    @Autowired
    private SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    protected Session getCurrentSession() {
	return getSessionFactory().getCurrentSession();
    }
}
