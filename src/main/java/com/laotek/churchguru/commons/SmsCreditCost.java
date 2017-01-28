package com.laotek.churchguru.commons;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SmsCreditCost")
public class SmsCreditCost {

    private BigDecimal creditCost;
    private BigDecimal creditBalance;
    private BigDecimal creditQuantityBalance;
    private int requestedQuantity;

    private List<SmsSendResultError> errors = new ArrayList<SmsSendResultError>();

    public List<SmsSendResultError> getErrors() {
	return errors;
    }

    public BigDecimal getCreditCost() {
	return creditCost;
    }

    @XmlElement
    public void setCreditCost(BigDecimal creditCost) {
	this.creditCost = creditCost;
    }

    public BigDecimal getCreditBalance() {
	return creditBalance;
    }

    @XmlElement
    public void setCreditBalance(BigDecimal creditBalance) {
	this.creditBalance = creditBalance;
    }

    public int getRequestedQuantity() {
	return requestedQuantity;
    }

    @XmlElement
    public void setRequestedQuantity(int requestedQuantity) {
	this.requestedQuantity = requestedQuantity;
    }

    public BigDecimal getCreditQuantityBalance() {
	return creditQuantityBalance;
    }

    @XmlElement
    public void setCreditQuantityBalance(BigDecimal creditQuantityBalance) {
	this.creditQuantityBalance = creditQuantityBalance;
    }

    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    public void setErrors(List<SmsSendResultError> errors) {
	this.errors = errors;
    }

}
