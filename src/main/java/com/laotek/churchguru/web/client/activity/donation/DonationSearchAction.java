package com.laotek.churchguru.web.client.activity.donation;

import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.web.client.activity.AbstractDispatchAction;
import com.laotek.churchguru.web.shared.role.HasDonationViewRole;

public class DonationSearchAction extends AbstractDispatchAction implements
	Action<DonationSearchResult>, HasDonationViewRole {

    private String surname;
    private String forename;
    private List<DonationType> donationTypes;
    private List<DonationTransactionStatus> donationTransactionStatuses;
    private Date fromDate;
    private Date toDate;
    private int maxResult;

    public DonationSearchAction() {
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getForename() {
	return forename;
    }

    public void setForename(String forename) {
	this.forename = forename;
    }

    public Date getFromDate() {
	return fromDate;
    }

    public void setFromDate(Date fromDate) {
	this.fromDate = fromDate;
    }

    public Date getToDate() {
	return toDate;
    }

    public void setToDate(Date toDate) {
	this.toDate = toDate;
    }

    public List<DonationType> getDonationTypes() {
	return donationTypes;
    }

    public void setDonationTypes(List<DonationType> donationTypes) {
	this.donationTypes = donationTypes;
    }

    public List<DonationTransactionStatus> getDonationTransactionStatuses() {
	return donationTransactionStatuses;
    }

    public void setDonationTransactionStatuses(
	    List<DonationTransactionStatus> donationTransactionStatuses) {
	this.donationTransactionStatuses = donationTransactionStatuses;
    }

    public int getMaxResult() {
	return maxResult;
    }

    public void setMaxResult(int maxResult) {
	this.maxResult = maxResult;
    }
}
