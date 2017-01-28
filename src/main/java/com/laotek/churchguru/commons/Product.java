package com.laotek.churchguru.commons;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {
    private String quickDesc;
    private String detailDesc;
    private BigDecimal price;
    private int quantity;

    public String getQuickDesc() {
	return quickDesc;
    }

    public void setQuickDesc(String quickDesc) {
	this.quickDesc = quickDesc;
    }

    public String getDetailDesc() {
	return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
	this.detailDesc = detailDesc;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

}
