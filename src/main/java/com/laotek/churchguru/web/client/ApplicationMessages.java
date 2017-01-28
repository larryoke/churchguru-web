package com.laotek.churchguru.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ApplicationMessages extends Messages {
    public static ApplicationMessages instance = GWT
	    .create(ApplicationMessages.class);

    @Key("website.title")
    String websiteTitle();

    @Key("learn.more.dashboard.home.key.q1")
    String learnMoreDashboardHomeKeyQ1();

    @Key("learn.more.dashboard.home.key.a1")
    String learnMoreDashboardHomeKeyA1();

    @Key("learn.more.dashboard.people.chart.key.q1")
    String learnMoreDashboardPeopleChartKeyQ1();

    @Key("learn.more.dashboard.people.chart.key.a1")
    String learnMoreDashboardPeopleChartKeyA1();

    @Key("learn.more.dashboard.newsletter.chart.key.q1")
    String learnMoreDashboardNewsletterChartKeyQ1();

    @Key("learn.more.dashboard.newsletter.chart.key.a1")
    String learnMoreDashboardNewsletterChartKeyA1();

    @Key("learn.more.dashboard.weekly.attendance.chart.key.q1")
    String learnMoreDashboardWeeklyAttendanceChartKeyQ1();

    @Key("learn.more.dashboard.weekly.attendance.chart.key.a1")
    String learnMoreDashboardWeeklyAttendanceChartKeyA1();

    @Key("learn.more.dashboard.account.quotas.chart.key.q1")
    String learnMoreDashboardAccountQuotasChartKeyQ1();

    @Key("learn.more.dashboard.account.quotas.chart.key.a1")
    String learnMoreDashboardAccountQuotasChartKeyA1();

    @Key("learn.more.search.member.key.q1")
    String learnMoreSearchMemberKeyQ1();

    @Key("learn.more.search.member.key.a1")
    String learnMoreSearchMemberKeyA1();

    @Key("learn.more.send.member.invitation.key.q1")
    String learnMoreSendMemberInvitationKeyQ1();

    @Key("learn.more.send.member.invitation.key.a1")
    String learnMoreSendMemberInvitationKeyA1();

    @Key("learn.more.send.member.invitation.key.q2")
    String learnMoreSendMemberInvitationKeyQ2();

    @Key("learn.more.send.member.invitation.key.a2.para1")
    String learnMoreSendMemberInvitationKeyA2Para1();

    @Key("learn.more.send.member.invitation.key.a2.para2")
    String learnMoreSendMemberInvitationKeyA2Para2();

    @Key("new.user.email.send.message")
    String newUserEmailSendMessage();

    @Key("notification.email.send.warning.message")
    String notificationEmailSendWarningMessage();
}
