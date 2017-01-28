package com.laotek.churchguru.commons;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SmsCreditHistory {
    private List<SmsCreditHistoryItem> items = new ArrayList<SmsCreditHistoryItem>();

    public List<SmsCreditHistoryItem> getItems() {
	return items;
    }

    @XmlElementWrapper(name = "smsAccountCreditHistory")
    @XmlElement(name = "history")
    public void setItems(List<SmsCreditHistoryItem> items) {
	this.items = items;
    }
    
    
}
