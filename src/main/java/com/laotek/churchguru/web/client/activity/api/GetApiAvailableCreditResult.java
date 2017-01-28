package com.laotek.churchguru.web.client.activity.api;

import java.math.BigDecimal;

import net.customware.gwt.dispatch.shared.Result;

public class GetApiAvailableCreditResult implements Result {
    private BigDecimal orgCreditBalance;
    private BigDecimal creditCount;
    private BigDecimal price;

    public GetApiAvailableCreditResult(BigDecimal orgCreditBalance,
	    BigDecimal creditCount, BigDecimal price) {
	super();
	this.orgCreditBalance = orgCreditBalance;
	this.creditCount = creditCount;
	this.price = price;
    }

    public GetApiAvailableCreditResult() {
    }

    public BigDecimal getCreditCount() {
	return creditCount;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public BigDecimal getOrgCreditBalance() {
	return orgCreditBalance;
    }

    public void setOrgCreditBalance(BigDecimal orgCreditBalance) {
	this.orgCreditBalance = orgCreditBalance;
    }

}
