package com.laotek.churchguru.web.client.activity;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.EmailRecipientType;

public class SendQuickEmailAction extends AbstractDispatchAction implements
	Action<SendQuickEmailResult> {
    private String replyToAddr;
    private String toAddr;
    private String subject;
    private String body;
    private String recipientIdentifier;
    private EmailRecipientType emailRecipientType;
    private boolean isSendCopyToSender;

    public String getReplyToAddr() {
	return replyToAddr;
    }

    public void setReplyToAddr(String replyToAddr) {
	this.replyToAddr = replyToAddr;
    }

    public String getToAddr() {
	return toAddr;
    }

    public void setToAddr(String toAddr) {
	this.toAddr = toAddr;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getBody() {
	return body;
    }

    public void setBody(String body) {
	this.body = body;
    }

    public boolean isSendCopyToSender() {
	return isSendCopyToSender;
    }

    public void setSendCopyToSender(boolean isSendCopyToSender) {
	this.isSendCopyToSender = isSendCopyToSender;
    }

    public String getRecipientIdentifier() {
	return recipientIdentifier;
    }

    public void setRecipientIdentifier(String recipientIdentifier) {
	this.recipientIdentifier = recipientIdentifier;
    }

    public EmailRecipientType getEmailRecipientType() {
	return emailRecipientType;
    }

    public void setEmailRecipientType(EmailRecipientType emailRecipientType) {
	this.emailRecipientType = emailRecipientType;
    }
}
