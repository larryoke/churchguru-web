package com.laotek.churchguru.web.server;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.laotek.churchguru.daos.listening.ListeningDao;
import com.laotek.churchguru.daos.notification.NotificationDao;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.daos.user.UserDao;
import com.laotek.churchguru.daos.user.UserRoleDao;

@WebListener
public class ApplicationContextStartupListener implements ServletContextListener {

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserAuditDao userAuditDao;

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    private ListeningDao eStoreDao;

    @Autowired
    @Value("${org.name}")
    private String orgName;

    @Autowired
    @Value("${hostname}")
    private String hostname;

    @Autowired
    @Value("${subdomain}")
    private String subdomain;

    @Autowired
    @Value("${addressLine1}")
    private String addressLine1;

    @Autowired
    @Value("${addressLine2}")
    private String addressLine2;

    @Autowired
    @Value("${country}")
    private String country;

    @Autowired
    @Value("${postcode}")
    private String postcode;

    @Autowired
    @Value("${org.identifier}")
    private String orgIdentifier;

    @Override
    public void contextInitialized(ServletContextEvent event) {
	try {

	    // init spring
	    init(event);

	    // init dkim
	    initDkimData(event);

	    organisationDao.load(orgName, hostname, subdomain, addressLine1, addressLine2, country, postcode,
		    orgIdentifier);

	    userRoleDao.load(1);

	    userDao.load();

	    userAuditDao.load();

	    notificationDao.load();

	    eStoreDao.loadNotifications();

	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    private void init(ServletContextEvent event) {

	WebApplicationContext ctx = WebApplicationContextUtils
		.getRequiredWebApplicationContext(event.getServletContext());
	AutowireCapableBeanFactory beanFactory = ctx.getAutowireCapableBeanFactory();
	beanFactory.autowireBean(this);
    }

    private void initDkimData(ServletContextEvent event) throws Exception {

	File dkimDir = new File(System.getProperty("user.home") + "/.churchguru-deploy/dkim");
	if (!dkimDir.exists()) {
	    if (!dkimDir.mkdir()) {
		throw new RuntimeException(dkimDir.getAbsolutePath() + " does not exist. Failed to create");
	    }
	}

	File privateKeyDerFile = new File(dkimDir, "private.key.der");
	File privateKeyPemFile = new File(dkimDir, "private.key.pem");
	if (!privateKeyDerFile.exists()) {
	    String command = "openssl genrsa -out " + privateKeyPemFile.getAbsolutePath();
	    System.out.println(command);
	    Runtime.getRuntime().exec(command);
	    Thread.sleep(2000);

	    if (!privateKeyPemFile.exists()) {
		throw new RuntimeException(privateKeyPemFile.getAbsolutePath() + " does not exist");

	    } else {
		Runtime.getRuntime().exec("openssl pkcs8 -topk8 -nocrypt -in " + privateKeyPemFile.getAbsolutePath()
			+ " -out " + privateKeyDerFile.getAbsolutePath() + " -outform der");
		Thread.sleep(2000);

		if (!privateKeyDerFile.exists()) {
		    throw new RuntimeException(privateKeyDerFile.getAbsolutePath() + " does not exist");
		}
	    }

	}
	Process process = Runtime.getRuntime()
		.exec("openssl rsa -inform PEM -in " + privateKeyPemFile.getAbsolutePath() + " -pubout");
	String publicKey = IOUtils.toString(process.getInputStream(), "UTF-8");
	System.out.println(publicKey);
    }

}
