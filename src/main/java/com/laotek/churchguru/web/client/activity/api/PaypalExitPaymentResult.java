package com.laotek.churchguru.web.client.activity.api;

import java.math.BigDecimal;

import net.customware.gwt.dispatch.shared.Result;

public class PaypalExitPaymentResult implements Result {

    private BigDecimal amountPaid;
    private int quantityBought;
    private BigDecimal latestQuantity;
    private boolean cancelled;

    public PaypalExitPaymentResult() {
    }

    public PaypalExitPaymentResult(BigDecimal amountPaid, int quantityBought,
	    BigDecimal latestQuantity, boolean cancelled) {
	super();
	this.amountPaid = amountPaid;
	this.quantityBought = quantityBought;
	this.latestQuantity = latestQuantity;
	this.cancelled = cancelled;
    }

    public BigDecimal getAmountPaid() {
	return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
	this.amountPaid = amountPaid;
    }

    public int getQuantityBought() {
	return quantityBought;
    }

    public void setQuantityBought(int quantityBought) {
	this.quantityBought = quantityBought;
    }

    public boolean isCancelled() {
	return cancelled;
    }

    public void setCancelled(boolean cancelled) {
	this.cancelled = cancelled;
    }

    public BigDecimal getLatestQuantity() {
	return latestQuantity;
    }

    public void setLatestQuantity(BigDecimal latestQuantity) {
	this.latestQuantity = latestQuantity;
    }

}
