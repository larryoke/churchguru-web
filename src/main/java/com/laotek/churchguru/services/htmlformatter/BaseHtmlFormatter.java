package com.laotek.churchguru.services.htmlformatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseHtmlFormatter {
    protected void footer(String servername, HTML html) {
	StringBuffer sb = new StringBuffer();
	sb.append("https://");
	sb.append(servername);
	sb.append("/communicator/index.htm#SearchMemberPlace:searchMember");

	html.appendHRef(
		"<br>To view more details or send a message, please click",
		"here", sb.toString());
    }

    protected String formatNowDate() {
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
	return sdf.format(new Date());
    }
}
