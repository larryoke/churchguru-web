package com.laotek.churchguru.commons;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "availableCredit")
public class AvailableCredit {
    private BigDecimal credit;
    private BigDecimal pricePerCredit;

    public BigDecimal getCredit() {
	return credit;
    }

    @XmlElement
    public void setCredit(BigDecimal credit) {
	this.credit = credit;
    }

    public BigDecimal getPricePerCredit() {
	return pricePerCredit;
    }

    @XmlElement
    public void setPricePerCredit(BigDecimal pricePerCredit) {
	this.pricePerCredit = pricePerCredit;
    }
}
