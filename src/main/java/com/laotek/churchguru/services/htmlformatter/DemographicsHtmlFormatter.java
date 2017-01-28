package com.laotek.churchguru.services.htmlformatter;

import java.util.Map;

import com.laotek.churchguru.daos.notification.NotificationDao.DemographicsHTMLFormatter;
import com.laotek.churchguru.model.shared.enums.MemberSearchType;

public class DemographicsHtmlFormatter extends BaseHtmlFormatter implements
	DemographicsHTMLFormatter {

    @Override
    public String format(Map<MemberSearchType, Integer> counts,
	    String servername, String orgName) {
	HTML html = new HTML();
	html.appendHeader(orgName + " demographics as at " + formatNowDate());

	html.appendTable();
	html.appendTableRow();
	html.appendTableRowHeaderCell("Demographics");
	html.appendTableRowHeaderCell("Count");

	for (Map.Entry<MemberSearchType, Integer> entry : counts.entrySet()) {
	    html.appendTableRow();
	    html.appendTableRowCell(entry.getKey().getDesc());
	    html.appendTableRowCell(entry.getValue());
	}

	html.appendBR();

	footer(servername, html);
	return html.toString();
    }

}
