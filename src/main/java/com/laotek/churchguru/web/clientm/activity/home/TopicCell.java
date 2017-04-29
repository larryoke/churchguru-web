package com.laotek.churchguru.web.clientm.activity.home;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;

public abstract class TopicCell<T> extends BasicCell<T> {

    @Override
    public void render(SafeHtmlBuilder safeHtmlBuilder, T model) {
	String displayString = getDisplayString(model);
	String picUrl = getDescPicUrl(model);
	;
	StringBuffer sb = new StringBuffer();
	sb.append("<table class=\"topicCell\">");
	sb.append("<tr>");
	sb.append("<td align=left  width=35 \">");
	sb.append("<img width=\"35\" height=\"35\" src=\"" + picUrl + "\">");
	sb.append("</td>");
	sb.append("<td align=left  width=30></td>");
	sb.append("<td align=left  width=100%>");

	sb.append("<div class=\"headerLabel\">");
	sb.append("<b>" + displayString + "</b>");
	sb.append("</div>");

	sb.append("</td>");
	sb.append("</tr>");
	sb.append("</table>");
	safeHtmlBuilder.appendHtmlConstant(sb.toString());
    }

    public abstract String getDescPicUrl(T model);

    public abstract String getMediaType(T model);
}
