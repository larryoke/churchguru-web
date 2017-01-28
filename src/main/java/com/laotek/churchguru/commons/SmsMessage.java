package com.laotek.churchguru.commons;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "smsMessage")
public class SmsMessage {

    private String senderName;

    private Set<SmsRecipient> recipients = new HashSet<SmsRecipient>();

    private String body;

    public String getBody() {
	return body;
    }

    @XmlElement
    public void setBody(String body) {
	this.body = body;
    }

    public Set<SmsRecipient> getRecipients() {
	return recipients;
    }

    @XmlElementWrapper(name = "recipients")
    @XmlElement(name = "recipient")
    public void setRecipients(Set<SmsRecipient> recipients) {
	this.recipients = recipients;
    }

    public String getSenderName() {
	return senderName;
    }

    @XmlElement
    public void setSenderName(String senderName) {
	this.senderName = senderName;
    }

}
