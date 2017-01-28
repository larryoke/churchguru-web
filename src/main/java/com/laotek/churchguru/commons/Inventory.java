package com.laotek.churchguru.commons;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.laotek.churchguru.commons")
public class Inventory {

    private List<Product> products = new ArrayList<Product>();

    public List<Product> getProducts() {
	return products;
    }

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    public void setProducts(List<Product> products) {
	this.products = products;
    }

}
