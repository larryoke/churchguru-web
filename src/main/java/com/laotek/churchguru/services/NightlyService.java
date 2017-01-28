package com.laotek.churchguru.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.nightly.NightlyDao;
import com.laotek.churchguru.daos.notification.NotificationDao;
import com.laotek.churchguru.services.htmlformatter.BirthdayHtmlFormatter;
import com.laotek.churchguru.services.htmlformatter.DemographicsHtmlFormatter;

@Component
public class NightlyService {

    static Logger log = LoggerFactory.getLogger(NightlyService.class);

    @Autowired
    private NightlyDao nightlyDao;

    @Autowired
    private NotificationDao notificationDao;

    @Scheduled(cron = "10 0 0 * * ?")
    public void execute() {
	log.debug("execute->");

	nightlyDao.cleanUpPasswordResetCache();

	nightlyDao.sendFullMemberInvitationReminderToMember();

	notificationDao.createActivatableNotifications(
		new BirthdayHtmlFormatter(), new DemographicsHtmlFormatter());
	log.debug("notificationDao.createActivatableNotifications()");

	log.debug("<-execute");
    }
}