package com.laotek.churchguru.commons;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class DeprecatedSmsSendResult {
    private List<SmsSendResultError> errors = new ArrayList<SmsSendResultError>();

    private BigDecimal balance;
    private String batchId;
    private BigDecimal cost;
    private int numberOfMessage;

    private SmsResultMessage resultMessage = new SmsResultMessage();

    private Set<DeprecatedSmsResultMessagesItem> recipients = new HashSet<DeprecatedSmsResultMessagesItem>();

    private String status;

    public BigDecimal getBalance() {
	return balance;
    }

    @XmlElement(name = "balance")
    public void setBalance(BigDecimal balance) {
	this.balance = balance;
    }

    public String getBatchId() {
	return batchId;
    }

    @XmlElement(name = "batch_id")
    public void setBatchId(String batchId) {
	this.batchId = batchId;
    }

    public BigDecimal getCost() {
	return cost;
    }

    @XmlElement(name = "cost")
    public void setCost(BigDecimal cost) {
	this.cost = cost;
    }

    public int getNumberOfMessage() {
	return numberOfMessage;
    }

    @XmlElement(name = "num_messages")
    public void setNumberOfMessage(int numberOfMessage) {
	this.numberOfMessage = numberOfMessage;
    }

    public String getStatus() {
	return status;
    }

    @XmlElement
    public void setStatus(String status) {
	this.status = status;
    }

    public SmsResultMessage getResultMessage() {
	return resultMessage;
    }

    // should I
    @XmlElement(name = "message")
    public void setResultMessage(SmsResultMessage resultMessage) {
	this.resultMessage = resultMessage;
    }

    public Set<DeprecatedSmsResultMessagesItem> getRecipients() {
	return recipients;
    }

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    public void setRecipients(Set<DeprecatedSmsResultMessagesItem> recipients) {
	this.recipients = recipients;
    }

    public List<SmsSendResultError> getErrors() {
	return errors;
    }

    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    public void setErrors(List<SmsSendResultError> errors) {
	this.errors = errors;
    }
}
