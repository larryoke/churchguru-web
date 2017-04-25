package com.laotek.churchguru.web.clientm.activity.message.category;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.list.celllist.BasicCell;

public abstract class MessageCell<T> extends BasicCell<T> {

    @Override
    public void render(SafeHtmlBuilder safeHtmlBuilder, T model) {
	String displayString = getDisplayString(model);
	String picUrl = getDescPicUrl(model);
	String mediaType = getMediaType(model);
	StringBuffer sb = new StringBuffer();
	sb.append("<table>");
	sb.append("<tr>");
	sb.append("<td align=left  width=30>");
	sb.append("<img width=\"30\" height=\"30\" src=\"" + picUrl + "\">");
	sb.append("</td>");
	sb.append("<td align=left  width=30></td>");
	sb.append("<td align=left  width=100%>");

	sb.append("<div>");
	sb.append("<b>" + displayString + "</b>");
	sb.append("</div>");
	sb.append("<div>");
	sb.append("<small><i>" + mediaType + "</i></small>");
	sb.append("</div>");

	sb.append("</td>");
	sb.append("</tr>");
	sb.append("</table>");
	safeHtmlBuilder.appendHtmlConstant(sb.toString());
    }

    public abstract String getDescPicUrl(T model);

    public abstract String getMediaType(T model);
}
