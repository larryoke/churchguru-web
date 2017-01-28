package com.laotek.churchguru.commons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "smsRecipient")
public class SmsRecipient {

    private String number;

    private long recipientId;
    private long textMessageId;

    private SmsRecipientType smsRecipientType;

    private String body;

    public String getBody() {
	return body;
    }

    @XmlElement
    public void setBody(String body) {
	this.body = body;
    }

    public String getNumber() {
	return number;
    }

    @XmlElement
    public void setNumber(String number) {
	this.number = number;
    }

    @Override
    public String toString() {
	return "SmsRecipient [code=" + ", number=" + number + "]";
    }

    public long getRecipientId() {
	return recipientId;
    }

    @XmlElement
    public void setRecipientId(long recipientId) {
	this.recipientId = recipientId;
    }

    public long getTextMessageId() {
	return textMessageId;
    }

    @XmlElement
    public void setTextMessageId(long textMessageId) {
	this.textMessageId = textMessageId;
    }

    public SmsRecipientType getSmsRecipientType() {
	return smsRecipientType;
    }

    @XmlElement
    public void setSmsRecipientType(SmsRecipientType smsRecipientType) {
	this.smsRecipientType = smsRecipientType;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SmsRecipient other = (SmsRecipient) obj;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	return true;
    }
}
