package com.laotek.churchguru.daos.send;

public class EmailProcessTO {
    private String recipientEmailAddress;
    private String recipientFullname;
    private String replyToEmailAddress;
    private String senderEmailAddress;
    private String senderFullname;
    private String subject;
    private String message;
    private String orgName;
    private String orgAddress;
    private String logoBase64Str;
    private String logoContentType;
    private String orgTelephone;

    public EmailProcessTO(String senderEmailAddress) {
	this.senderEmailAddress = senderEmailAddress;
    }

    public String getRecipientEmailAddress() {
	return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(String recipientEmailAddress) {
	this.recipientEmailAddress = recipientEmailAddress;
    }

    public String getRecipientFullname() {
	return recipientFullname;
    }

    public void setRecipientFullname(String recipientFullname) {
	this.recipientFullname = recipientFullname;
    }

    public String getReplyToEmailAddress() {
	return replyToEmailAddress;
    }

    public void setReplyToEmailAddress(String replyToEmailAddress) {
	this.replyToEmailAddress = replyToEmailAddress;
    }

    public String getSenderEmailAddress() {
	return senderEmailAddress;
    }

    public String getSenderFullname() {
	return senderFullname;
    }

    public void setSenderFullname(String senderFullname) {
	this.senderFullname = senderFullname;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getMessage() {
	return message;
    }

    public String getOrgName() {
	return orgName;
    }

    public void setOrgName(String orgName) {
	this.orgName = orgName;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getLogoBase64Str() {
	return logoBase64Str;
    }

    public String getLogoContentType() {
	return logoContentType;
    }

    public void setLogoBase64Str(String logoBase64Str) {
	this.logoBase64Str = logoBase64Str;
    }

    public void setLogoContentType(String logoContentType) {
	this.logoContentType = logoContentType;
    }

    public String getOrgTelephone() {
	return orgTelephone;
    }

    public void setOrgTelephone(String orgTelephone) {
	this.orgTelephone = orgTelephone;
    }

    public String getOrgAddress() {
	return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
	this.orgAddress = orgAddress;
    }

}
