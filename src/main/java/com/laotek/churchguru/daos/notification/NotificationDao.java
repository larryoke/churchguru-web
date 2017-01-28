package com.laotek.churchguru.daos.notification;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.Notification;
import com.laotek.churchguru.model.shared.enums.MemberSearchType;

public interface NotificationDao {
    List<Notification> getNotifications();

    void load();

    void updateNotification(Notification notification);

    void createActivatableNotifications(
	    BirthdayHTMLFormatter birthdayHTMLFormatter,
	    DemographicsHTMLFormatter demographicsHTMLFormatter);

    public interface BirthdayHTMLFormatter {

	String formatWeekly(int lastWeekCount, int thisWeekCount,
		int nextWeekCount, String servername, String orgName);

	String formatDaily(int yesterday, int today, int tomorrow,
		String servername, String orgName);

	String formatMonthly(int thisMonthCount, String servername,
		String orgName);
    }

    public interface DemographicsHTMLFormatter {

	String format(Map<MemberSearchType, Integer> counts, String servername,
		String orgName);
    }
}
