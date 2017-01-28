package com.laotek.churchguru.services.htmlformatter;

public class HTML {
    private StringBuffer html = new StringBuffer();

    HTML appendHeader(String value) {
	html.append("<h3>");
	html.append(value);
	html.append("</h3>");
	return this;
    }

    HTML appendDiv(String value) {
	html.append("<div>");
	html.append(value);
	html.append("</div>");
	return this;
    }

    HTML appendTable() {
	html.append("<table border='1' cellpadding='0' cellspacing='0' width='400px'></table>");
	return this;
    }

    HTML appendTableRow() {
	int index = html.toString().lastIndexOf("</table>");
	html.insert(index, "<tr></tr>");
	return this;
    }

    HTML appendTableRowCell(String value) {
	int index = html.toString().lastIndexOf("</tr></table>");
	html.insert(index, "<td align='center'>" + value + "</td>");
	return this;
    }

    HTML appendTableRowHeaderCell(String value) {
	int index = html.toString().lastIndexOf("</tr></table>");
	html.insert(index, "<th align='center' bgcolor='#DADADA'>" + value
		+ "</th>");
	return this;
    }

    HTML appendBR() {
	html.append("<br/>");
	return this;
    }

    HTML appendHRef(String message, String label, String url) {
	StringBuffer sb = new StringBuffer();
	sb.append("<div> ");
	sb.append(message);
	sb.append(" <a href='");
	sb.append(url);
	sb.append("'>");
	sb.append(label);
	sb.append("</a> ");
	sb.append("</div>");
	html.append(sb.toString());
	return this;
    }

    HTML appendTableRowCell(int value) {
	return appendTableRowCell(String.valueOf(value));
    }

    @Override
    public String toString() {
	return html.toString();
    }

}
