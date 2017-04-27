package com.laotek.churchguru.web.clientm.activity.message.category;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;

public abstract class MessageCatCell<T> extends BasicCell<T> {

    @Override
    public void render(SafeHtmlBuilder safeHtmlBuilder, T model) {
	String displayString = getDisplayString(model);
	int count = getCount(model);
	StringBuffer sb = new StringBuffer();
	sb.append("<table>");
	sb.append("<tr>");
	sb.append("<td align=left  width=30>");
	sb.append("<img width=\"30\" height=\"30\" src=\"/uploadedphotos/photos/org/logo\">");
	sb.append("</td>");
	sb.append("<td align=left  width=30></td>");
	sb.append("<td align=left  width=100%>");

	sb.append("<div class=\"headerLabel\">");
	sb.append("<b><big>" + displayString + "</big></b>");
	sb.append("&nbsp;<small>(" + count + ")</small>");
	sb.append("</div>");

	sb.append("</td>");
	sb.append("</tr>");
	sb.append("</table>");
	safeHtmlBuilder.appendHtmlConstant(sb.toString());
    }

    public abstract int getCount(T model);
}
