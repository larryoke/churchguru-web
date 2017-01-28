package com.laotek.churchguru.commons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class SmsResultMessage {
    private int numParts;
    private String sender;
    private String content;

    public int getNumParts() {
	return numParts;
    }

    @XmlElement(name = "num_parts")
    public void setNumParts(int numParts) {
	this.numParts = numParts;
    }

    public String getSender() {
	return sender;
    }

    @XmlElement(name = "sender")
    public void setSender(String sender) {
	this.sender = sender;
    }

    public String getContent() {
	return content;
    }

    @XmlElement(name = "content")
    public void setContent(String content) {
	this.content = content;
    }

}
