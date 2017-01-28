package com.laotek.churchguru.web.shared;

import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ShoppingCartDto implements IsSerializable {
    private String desc;
    private BigDecimal price;
    private int quantity;
    private BigDecimal vatRate;
    private BigDecimal vatValue;
    private BigDecimal subtotal;
    private BigDecimal total;

    public ShoppingCartDto() {
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
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

    public BigDecimal getSubtotal() {
	return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
	this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
	return total;
    }

    public void setTotal(BigDecimal total) {
	this.total = total;
    }

    public BigDecimal getVatRate() {
	return vatRate;
    }

    public void setVatRate(BigDecimal vatRate) {
	this.vatRate = vatRate;
    }

    public BigDecimal getVatValue() {
	return vatValue;
    }

    public void setVatValue(BigDecimal vatValue) {
	this.vatValue = vatValue;
    }
}
