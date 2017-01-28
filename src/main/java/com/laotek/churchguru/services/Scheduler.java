package com.laotek.churchguru.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.daos.send.EmailProcessorDao;
import com.laotek.churchguru.model.Organisation;

@Component
public class Scheduler {

    static Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private EmailProcessorDao emailProcessorDao;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;

    @Scheduled(fixedDelay = 20000)
    public void execute() {
	log.debug("execute->");

	try {
	    sendEmailMessages();

	} catch (Exception e) {
	    log.error(e.getMessage());
	    e.printStackTrace();
	}

	log.debug("<-execute");
    }

    private void sendEmailMessages() {

	Organisation org = organisationDao.getOrganisation(1L);

	emailProcessorDao.processMessage(new EmailProcessImpl(sendgridApiKey), org);
	// for (Organisation org : organisationDao.getOrganisations()) {
	// emailProcessorDao.processMessage(new EmailProcessImpl(
	// sendgridApiKey), org);
	// }
    }
}