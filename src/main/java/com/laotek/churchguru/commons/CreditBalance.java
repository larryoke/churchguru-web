package com.laotek.churchguru.commons;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "creditBalance")
public class CreditBalance {
    private BigDecimal quantity;
    private BigDecimal creditBalance;

    public CreditBalance(BigDecimal quantity, BigDecimal creditBalance) {
	this.quantity = quantity;
	this.setCreditBalance(creditBalance);
    }

    public CreditBalance() {
    }

    public BigDecimal getQuantity() {
	return quantity;
    }

    @XmlElement
    public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
    }

    public BigDecimal getCreditBalance() {
	return creditBalance;
    }

    @XmlElement
    public void setCreditBalance(BigDecimal creditBalance) {
	this.creditBalance = creditBalance;
    }

}
