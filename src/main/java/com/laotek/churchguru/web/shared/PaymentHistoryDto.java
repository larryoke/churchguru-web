package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PaymentHistoryDto implements IsSerializable {
    private String time;
    private String desc;

    public PaymentHistoryDto() {
    }

    public String getTime() {
	return time;
    }

    public void setTime(String time) {
	this.time = time;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

}
