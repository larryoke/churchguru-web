package com.laotek.churchguru.web.clientm.activity.home;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

public class SubmitDonationDetailsAction implements
	Action<SubmitDonationDetailsResult> {

    private Map<String, String> details = new HashMap<String, String>();
    private Map<DonationType, BigDecimal> payments = new HashMap<DonationType, BigDecimal>();

    public Map<String, String> getDetails() {
	return details;
    }

    public void setDetails(Map<String, String> details) {
	this.details = details;
    }

    public Map<DonationType, BigDecimal> getPayments() {
	return payments;
    }

    public void setPayments(Map<DonationType, BigDecimal> payments) {
	this.payments = payments;
    }
}
