package com.laotek.churchguru.daos.send;

public enum MailMergeConstant {
    TITLE("<<title>>"),

    FORENAME("<<forename>>"),

    FORENAMES("<<forenames>>"),

    SURNAME("<<surname>>"),

    FULLNAME("<<fullname>>"),

    ADDRESS("<<address>>");

    private String placeholder;

    private MailMergeConstant(String placeholder) {
	this.setPlaceholder(placeholder);
    }

    public String getPlaceholder() {
	return placeholder;
    }

    public void setPlaceholder(String placeholder) {
	this.placeholder = placeholder;
    }

    public String getEscapedPlaceholder() {
	StringBuffer sb = new StringBuffer();
	for (char c : placeholder.toCharArray()) {
	    if (c == '<') {
		sb.append("&lt;");

	    } else if (c == '>') {
		sb.append("&gt;");
	    } else {
		sb.append(c);
	    }
	}
	return sb.toString();
    }
}
