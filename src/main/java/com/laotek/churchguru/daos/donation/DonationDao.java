package com.laotek.churchguru.daos.donation;

import java.util.Date;
import java.util.List;

import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;

public interface DonationDao {

    void createDonation(Donation donation);

    Donation getDonationByIdentifier(String identifier);

    void cancelDonation(String identifier);

    void completeDonation(String identifier);

    List<Donation> search(String surname, String forename,
	    List<DonationType> donationTypes,
	    List<DonationTransactionStatus> statuses, Date from, Date to,
	    int maxResult);
}
