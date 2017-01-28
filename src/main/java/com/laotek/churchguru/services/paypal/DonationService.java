package com.laotek.churchguru.services.paypal;

import com.laotek.churchguru.model.Donation;
import com.paypal.api.payments.Payment;

public interface DonationService {
    Payment createPaypalDonation(Donation donation, String returnUrl,
	    String cancelUrl);

    Payment executePaypalDonation(String paymentId, String payerId);

    Payment getPaypalDonation(String paymentId);
}
