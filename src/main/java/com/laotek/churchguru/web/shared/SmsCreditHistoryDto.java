package com.laotek.churchguru.web.shared;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SmsCreditHistoryDto implements Serializable, IsSerializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String date;
    private boolean isCredit;
    private BigDecimal creditBalance;
    private BigDecimal eventCredit;
    private String desc;

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public boolean isCredit() {
	return isCredit;
    }

    public void setCredit(boolean isCredit) {
	this.isCredit = isCredit;
    }

    public BigDecimal getCreditBalance() {
	return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
	this.creditBalance = creditBalance;
    }

    public BigDecimal getEventCredit() {
	return eventCredit;
    }

    public void setEventCredit(BigDecimal eventCredit) {
	this.eventCredit = eventCredit;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

}
