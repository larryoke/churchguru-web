package com.laotek.churchguru.services.htmlformatter;

import com.laotek.churchguru.daos.notification.NotificationDao;

public class BirthdayHtmlFormatter extends BaseHtmlFormatter implements
	NotificationDao.BirthdayHTMLFormatter {

    @Override
    public String formatWeekly(int lastWeekCount, int thisWeekCount,
	    int nextWeekCount, String servername, String orgName) {
	HTML html = new HTML();
	html.appendHeader(orgName + " weekly birthday notification as at "
		+ formatNowDate());

	html.appendTable();
	html.appendTableRow();
	html.appendTableRowHeaderCell("Week");
	html.appendTableRowHeaderCell("Count");

	html.appendTableRow();
	html.appendTableRowCell("Last Week");
	html.appendTableRowCell(lastWeekCount);

	html.appendTableRow();
	html.appendTableRowCell("This Week");
	html.appendTableRowCell(thisWeekCount);

	html.appendTableRow();
	html.appendTableRowCell("Next Week");
	html.appendTableRowCell(nextWeekCount);

	footer(servername, html);
	return html.toString();
    }

    @Override
    public String formatDaily(int yesterday, int today, int tomorrow,
	    String servername, String orgName) {
	HTML html = new HTML();
	html.appendHeader(orgName + " daily birthday notification as at "
		+ formatNowDate());

	html.appendTable();
	html.appendTableRow();
	html.appendTableRowHeaderCell("Day");
	html.appendTableRowHeaderCell("Count");

	html.appendTableRow();
	html.appendTableRowCell("Yesterday");
	html.appendTableRowCell(yesterday);

	html.appendTableRow();
	html.appendTableRowCell("Today");
	html.appendTableRowCell(today);

	html.appendTableRow();
	html.appendTableRowCell("Tomorrow");
	html.appendTableRowCell(tomorrow);

	html.appendBR();
	footer(servername, html);
	return html.toString();
    }

    @Override
    public String formatMonthly(int thisMonthCount, String servername,
	    String orgName) {
	HTML html = new HTML();
	html.appendHeader(orgName + " monthly birthday notification as at "
		+ formatNowDate());
	html.appendTable();

	html.appendTableRow();
	html.appendTableRowHeaderCell("Month");
	html.appendTableRowHeaderCell("Count");

	html.appendTableRow();
	html.appendTableRowCell("This Month");
	html.appendTableRowCell(thisMonthCount);

	footer(servername, html);
	return html.toString();
    }
}
