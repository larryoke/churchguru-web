package com.laotek.churchguru.commons;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shoppingCart")
public class ShoppingCart {
    private String desc;
    private BigDecimal price;
    private int quantity;
    private BigDecimal vatRate;
    private BigDecimal vatValue;
    private BigDecimal subtotal;
    private BigDecimal total;
    
    public ShoppingCart() {
    }

    public String getDesc() {
        return desc;
    }
    
    @XmlElement
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public BigDecimal getPrice() {
        return price;
    }
    
    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    
    @XmlElement
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getVatRate() {
        return vatRate;
    }

    @XmlElement
    public void setVatRate(BigDecimal vatRate) {
        this.vatRate = vatRate;
    }

    public BigDecimal getVatValue() {
        return vatValue;
    }

    @XmlElement
    public void setVatValue(BigDecimal vatValue) {
        this.vatValue = vatValue;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    @XmlElement
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @XmlElement
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
