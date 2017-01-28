package com.laotek.churchguru.daos.notification;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.MemberMetaData;
import com.laotek.churchguru.model.Notification;
import com.laotek.churchguru.model.NotificationEmail;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.shared.enums.MemberSearchType;
import com.laotek.churchguru.model.shared.enums.NotificationSchedule;
import com.laotek.churchguru.model.shared.enums.NotificationStatus;
import com.laotek.churchguru.model.shared.enums.NotificationType;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NotificationDaoImpl extends BaseSessionFactory implements
	NotificationDao {

    static Logger log = LoggerFactory.getLogger(NotificationDaoImpl.class);

    @Autowired
    @Value("${mail.sender.username}")
    private String mailSenderUsername;

    @Autowired
    @Value("${mail.host}")
    private String mailHost;

    @Override
    public List<Notification> getNotifications() {
	return getCurrentSession()
		.createQuery("from Notification notification").list();
    }

    @Override
    public void load() {
	log.debug("load->");
	Query query = getCurrentSession()
		.createQuery(
			"from Notification n where n.notificationType = :notificationType");
	query.setParameter("notificationType", NotificationType.BIRTHDAY);
	Notification bday = (Notification) query.uniqueResult();

	query = getCurrentSession()
		.createQuery(
			"from Notification n where n.notificationType = :notificationType");
	query.setParameter("notificationType", NotificationType.DEMOGRAPHICS);
	Notification demographics = (Notification) query.uniqueResult();

	User user = null;
	Organisation org = null;
	if (bday == null || demographics == null) {
	    user = (User) getCurrentSession().get(User.class, 1L);
	    org = (Organisation) getCurrentSession()
		    .get(Organisation.class, 1L);

	    if (bday == null) {
		bday = new Notification();
		bday.setCreatedDate(new Date());
		bday.setLastUpdatedDate(new Date());
		bday.setEmailAddress(user.getEmailAddress());
		bday.setFullname(user.getFullname());
		bday.setOrganisation(org);
		bday.setNotificationSchedule(NotificationSchedule.WEELKLY);
		bday.setNotificationStatus(NotificationStatus.RUNNING);
		bday.setNotificationType(NotificationType.BIRTHDAY);
		bday.setIdentifier(RandomStringUtils.random(10, true, true));
		getCurrentSession().persist(bday);
	    }

	    if (demographics == null) {
		demographics = new Notification();
		demographics.setCreatedDate(new Date());
		demographics.setLastUpdatedDate(new Date());
		demographics.setEmailAddress(user.getEmailAddress());
		demographics.setFullname(user.getFullname());
		demographics.setOrganisation(org);
		demographics
			.setNotificationSchedule(NotificationSchedule.WEELKLY);
		demographics.setNotificationStatus(NotificationStatus.RUNNING);
		demographics.setNotificationType(NotificationType.DEMOGRAPHICS);
		demographics.setIdentifier(RandomStringUtils.random(10, true,
			true));
		getCurrentSession().persist(demographics);
	    }
	}
	log.debug("<-load");
    }

    @Override
    public void updateNotification(Notification update) {

	log.debug("updateNotification->");
	Query query = getCurrentSession()
		.createQuery(
			"from Notification n where n.notificationType = :notificationType");
	query.setParameter("notificationType", update.getNotificationType());
	Notification current = (Notification) query.uniqueResult();

	current.setEmailAddress(update.getEmailAddress());
	current.setFullname(update.getFullname());
	current.setLastUpdatedDate(new Date());
	current.setNotificationSchedule(update.getNotificationSchedule());
	current.setNotificationStatus(update.getNotificationStatus());
	log.debug("<-updateNotification");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createActivatableNotifications(
	    BirthdayHTMLFormatter birthdayHTMLFormatter,
	    DemographicsHTMLFormatter demographicsHTMLFormatter) {

	log.debug("createActivatableNotification->");
	Query query = getCurrentSession()
		.createQuery(
			"from Notification n where n.notificationStatus = :notificationStatus");
	query.setParameter("notificationStatus", NotificationStatus.RUNNING);

	Calendar cal = Calendar.getInstance();

	boolean isFirstDayOfMonth = cal.get(Calendar.DAY_OF_MONTH) == 1;

	boolean isFirstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;

	List<Notification> active = query.list();

	for (Notification notification : active) {
	    if (NotificationSchedule.DAILY.equals(notification
		    .getNotificationSchedule())) {
		if (NotificationType.BIRTHDAY.equals(notification
			.getNotificationType())) {
		    createActivatableDailyNotificationBirthday(notification,
			    birthdayHTMLFormatter);

		} else if (NotificationType.DEMOGRAPHICS.equals(notification
			.getNotificationType())) {
		    createActivatableNotificationDemographics(notification,
			    demographicsHTMLFormatter);
		}

	    } else if (isFirstDayOfWeek
		    && NotificationSchedule.WEELKLY.equals(notification
			    .getNotificationSchedule())) {
		if (NotificationType.BIRTHDAY.equals(notification
			.getNotificationType())) {
		    createActivatableWeeklyNotificationBirthday(notification,
			    birthdayHTMLFormatter);
		} else if (NotificationType.DEMOGRAPHICS.equals(notification
			.getNotificationType())) {
		    createActivatableNotificationDemographics(notification,
			    demographicsHTMLFormatter);
		}

	    } else if (isFirstDayOfMonth
		    && NotificationSchedule.MONTHLY.equals(notification
			    .getNotificationSchedule())) {
		if (NotificationType.BIRTHDAY.equals(notification
			.getNotificationType())) {
		    createActivatableMonthlyNotificationBirthday(notification,
			    birthdayHTMLFormatter);
		} else if (NotificationType.DEMOGRAPHICS.equals(notification
			.getNotificationType())) {
		    createActivatableNotificationDemographics(notification,
			    demographicsHTMLFormatter);
		}
	    }
	}

	log.debug("<-createActivatableNotification");
    }

    private void createActivatableDailyNotificationBirthday(
	    Notification notification, BirthdayHTMLFormatter htmlFormatter) {

	log.debug("createActivatableDailyNotificationBirthday->");
	int yesterdayBday = getMemberMetaData(
		MemberSearchType.BIRTHDAY_YESTERDAY,
		notification.getOrganisation()).getCurrentCount().intValue();

	int todayBday = getMemberMetaData(MemberSearchType.BIRTHDAY_TODAY,
		notification.getOrganisation()).getCurrentCount().intValue();

	int tomorrowBday = getMemberMetaData(
		MemberSearchType.BIRTHDAY_TOMORROW,
		notification.getOrganisation()).getCurrentCount().intValue();

	String orgName = notification.getOrganisation().getOrgName();

	int total = yesterdayBday + todayBday + tomorrowBday;
	if (total > 0) {
	    String message = htmlFormatter.formatDaily(yesterdayBday,
		    todayBday, tomorrowBday, mailHost, orgName);
	    createNotificationEmail(notification, message);
	    log.info("Total daily birthday data found: " + total);

	} else {
	    log.info("No daily birthday data available found");
	}

	log.debug("<-createActivatableDailyNotificationBirthday");
    }

    private void createActivatableNotificationDemographics(
	    Notification notification, DemographicsHTMLFormatter htmlFormatter) {

	log.debug("createActivatableNotificationDemographics->");

	Map<MemberSearchType, Integer> counts = new LinkedHashMap<MemberSearchType, Integer>();
	for (MemberSearchType memberSearchType : MemberSearchType.values()) {
	    MemberMetaData memberMetaData = getMemberMetaData(memberSearchType,
		    notification.getOrganisation());
	    if (memberMetaData != null) {
		int count = memberMetaData.getCurrentCount().intValue();
		counts.put(memberSearchType, count);
	    } else {
		log.info(memberSearchType + " is null");
	    }
	}

	String message = htmlFormatter.format(counts, mailHost, notification
		.getOrganisation().getOrgName());

	createNotificationEmail(notification, message);

	log.debug("<-createActivatableNotificationDemographics");
    }

    // private void createActivatableNotificationDemographics(
    // Notification notification, DemographicsHTMLFormatter htmlFormatter) {
    //
    // log.debug("createActivatableNotificationDemographics->");
    //
    // int allMembers = getMemberMetaData(MemberSearchType.ALL_MEMBERS,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allMen = getMemberMetaData(MemberSearchType.ALL_MEN,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allWomen = getMemberMetaData(MemberSearchType.ALL_WOMEN,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allWorkers = getMemberMetaData(MemberSearchType.ALL_DEPT_WORKERS,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allNonWorkers = getMemberMetaData(MemberSearchType.ALL_NON_WORKERS,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allParents = getMemberMetaData(MemberSearchType.ALL_PARENTS,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allLeadersAndDeputies =
    // getMemberMetaData(MemberSearchType.ALL_DEPT_LEADERS_AND_DEPUTIES,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    // int allLeadersAndDeputies = getMemberMetaData(MemberSearchType.,
    // notification.getOrganisation()).getCurrentCount().intValue();
    //
    //
    //
    // String message = htmlFormatter.format(allMembers, allMen,
    // allWomen, allNonWorkers, mailHost);
    //
    // createNotificationEmail(notification, message);
    //
    // log.debug("<-createActivatableNotificationDemographics");
    // }

    private void createActivatableWeeklyNotificationBirthday(
	    Notification notification, BirthdayHTMLFormatter htmlFormatter) {

	log.debug("createActivatableWeeklyNotificationBirthday->");

	int lastWeekBday = getMemberMetaData(
		MemberSearchType.BIRTHDAY_LAST_WEEK,
		notification.getOrganisation()).getCurrentCount().intValue();

	int thisWeekBday = getMemberMetaData(
		MemberSearchType.BIRTHDAY_THIS_WEEK,
		notification.getOrganisation()).getCurrentCount().intValue();

	int nextWeekBday = getMemberMetaData(
		MemberSearchType.BIRTHDAY_NEXT_WEEK,
		notification.getOrganisation()).getCurrentCount().intValue();

	String orgName = notification.getOrganisation().getOrgName();

	String message = htmlFormatter.formatWeekly(lastWeekBday, thisWeekBday,
		nextWeekBday, mailHost, orgName);

	createNotificationEmail(notification, message);

	log.debug("<-createActivatableWeeklyNotificationBirthday");
    }

    private void createActivatableMonthlyNotificationBirthday(
	    Notification notification, BirthdayHTMLFormatter htmlFormatter) {

	MemberSearchType bdayMonth = getCurrentBirthMonth();

	int thisMonthBday = getMemberMetaData(bdayMonth,
		notification.getOrganisation()).getCurrentCount().intValue();

	String orgName = notification.getOrganisation().getOrgName();

	String message = htmlFormatter.formatMonthly(thisMonthBday, mailHost,
		orgName);

	createNotificationEmail(notification, message);
    }

    private MemberSearchType getCurrentBirthMonth() {
	MemberSearchType bdayMonth = null;
	Calendar cal = Calendar.getInstance();
	int monthIndex = cal.get(Calendar.MONTH);

	if (Calendar.JANUARY == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_JAN;

	} else if (Calendar.FEBRUARY == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_FEB;

	} else if (Calendar.MARCH == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_MAR;

	} else if (Calendar.APRIL == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_APR;

	} else if (Calendar.MAY == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_MAY;

	} else if (Calendar.JUNE == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_JUN;

	} else if (Calendar.JULY == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_JUL;

	} else if (Calendar.AUGUST == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_AUG;

	} else if (Calendar.SEPTEMBER == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_SEP;

	} else if (Calendar.OCTOBER == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_OCT;

	} else if (Calendar.NOVEMBER == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_NOV;

	} else if (Calendar.DECEMBER == monthIndex) {
	    bdayMonth = MemberSearchType.BIRTH_MONTTH_DEC;
	}

	return bdayMonth;
    }

    private MemberMetaData getMemberMetaData(MemberSearchType searchType,
	    Organisation org) {
	Query query = getCurrentSession()
		.createQuery(
			"from MemberMetaData mmd where mmd.searchType = :searchType and mmd.organisation = :org");
	query.setParameter("searchType", searchType);
	query.setParameter("org", org);

	return (MemberMetaData) query.uniqueResult();
    }

    private void createNotificationEmail(Notification notification,
	    String message) {
	log.debug("createNotificationEmail->");
	NotificationEmail notificationEmail = new NotificationEmail();
	notificationEmail.setSubject(notification.getNotificationType()
		.getDesc());
	notificationEmail.setReplyToFullname(notification.getFullname());
	notificationEmail.setFromAddr(getMailSenderAddress());
	notificationEmail.setCreatedDate(new Date());
	notificationEmail.setFullname(notification.getFullname());
	notificationEmail.setRecipientIdentifier(RandomStringUtils.random(10,
		true, true));
	notificationEmail.setMessage(message);
	notificationEmail.setToAddr(notification.getEmailAddress());
	notificationEmail.setOrganisation(notification.getOrganisation());
	getCurrentSession().persist(notificationEmail);
	log.debug("<-createNotificationEmail");
    }

    private String getMailSenderAddress() {
	return mailSenderUsername + "@" + mailHost;
    }
}
