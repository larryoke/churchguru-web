package com.laotek.churchguru.commons;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "paymentResult")
public class PaymentResult {
    private BigDecimal amountPaid;
    private int quantityBought;
    private BigDecimal latestQuantity;
    private boolean cancelled;

    public BigDecimal getAmountPaid() {
	return amountPaid;
    }

    @XmlElement
    public void setAmountPaid(BigDecimal amountPaid) {
	this.amountPaid = amountPaid;
    }

    public int getQuantityBought() {
	return quantityBought;
    }

    @XmlElement
    public void setQuantityBought(int quantityBought) {
	this.quantityBought = quantityBought;
    }

    public boolean isCancelled() {
	return cancelled;
    }

    @XmlElement
    public void setCancelled(boolean cancelled) {
	this.cancelled = cancelled;
    }

    public BigDecimal getLatestQuantity() {
	return latestQuantity;
    }

    @XmlElement
    public void setLatestQuantity(BigDecimal latest) {
	this.latestQuantity = latest;
    }

}
