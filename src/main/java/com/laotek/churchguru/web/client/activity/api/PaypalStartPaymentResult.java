package com.laotek.churchguru.web.client.activity.api;

import net.customware.gwt.dispatch.shared.Result;

import com.laotek.churchguru.web.shared.PaymentDetailsDto;

public class PaypalStartPaymentResult implements Result {
    private PaymentDetailsDto dto;

    public PaypalStartPaymentResult() {
    }

    public PaypalStartPaymentResult(PaymentDetailsDto dto) {
	this.dto = dto;
    }

    public PaymentDetailsDto getDto() {
	return dto;
    }

    public void setDto(PaymentDetailsDto dto) {
	this.dto = dto;
    }

}
