package com.laotek.churchguru.commons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class DeprecatedSmsResultMessagesItem {
    private String id;
    private String recipient;

    public String getId() {
	return id;
    }

    @XmlElement
    public void setId(String id) {
	this.id = id;
    }

    public String getRecipient() {
	return recipient;
    }

    @XmlElement
    public void setRecipient(String recipient) {
	this.recipient = recipient;
    }
}
