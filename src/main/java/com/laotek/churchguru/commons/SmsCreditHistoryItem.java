package com.laotek.churchguru.commons;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "smsAccountCreditHistoryItem")
public class SmsCreditHistoryItem {

    private SmsCreditHistoryStatus smsCreditHistoryStatus;
    private BigDecimal currentCreditBalance;
    private BigDecimal currentEventCredit;
    private Date createdDate;

    public Date getCreatedDate() {
	return createdDate;
    }

    @XmlElement
    public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
    }

    public BigDecimal getCurrentCreditBalance() {
	return currentCreditBalance;
    }

    @XmlElement
    public void setCurrentCreditBalance(BigDecimal currentCreditBlance) {
	this.currentCreditBalance = currentCreditBlance;
    }

    public BigDecimal getCurrentEventCredit() {
	return currentEventCredit;
    }

    @XmlElement
    public void setCurrentEventCredit(BigDecimal currentEventCredit) {
	this.currentEventCredit = currentEventCredit;
    }

    public SmsCreditHistoryStatus getSmsCreditHistoryStatus() {
	return smsCreditHistoryStatus;
    }

    @XmlElement
    public void setSmsCreditHistoryStatus(
	    SmsCreditHistoryStatus smsCreditHistoryStatus) {
	this.smsCreditHistoryStatus = smsCreditHistoryStatus;
    }
}
