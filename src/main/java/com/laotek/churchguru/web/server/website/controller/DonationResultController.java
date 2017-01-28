package com.laotek.churchguru.web.server.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.laotek.churchguru.daos.donation.DonationDao;
import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.shared.enums.DonationTransactionStatus;
import com.laotek.churchguru.services.paypal.DonationService;
import com.paypal.api.payments.Payment;

@Controller
@RequestMapping("/donationresult")
public class DonationResultController {
    static Logger log = LoggerFactory.getLogger(DonationResultController.class);

    @Autowired
    private DonationDao donationDao;

    @Autowired
    private DonationService donationService;

    private String metaHttp = "<meta http-equiv=\"refresh\" content=\"0; url=/%s\" />";

    @RequestMapping(value = "/paypal/cancelled", method = RequestMethod.GET)
    public String cancelledPayPalRequest(@RequestParam("identifier") String identifier,
	    @RequestParam("platform") String platform) {
	log.debug("cancelledPayPalRequest->");

	Donation donation = donationDao.getDonationByIdentifier(identifier);

	Payment payment = donationService.getPaypalDonation(donation.getPaymentId());

	if (DonationTransactionStatus.CREATED.getLabel().equals(payment.getState())) {
	    donationDao.cancelDonation(donation.getPaymentId());
	}

	if ("i".equalsIgnoreCase(platform)) {
	    return String.format(metaHttp, "cancelAndCloseIOSBrowserForPaypalMobile.htm");
	}
	return String.format(metaHttp, "cancelAndCloseAndroidBrowserForPaypalMobile.htm");
    }

    @RequestMapping(value = "/paypal/success", method = RequestMethod.GET)
    public String successPaypalPayment(@RequestParam("identifier") String identifier,
	    @RequestParam("PayerID") String payerId, @RequestParam("platform") String platform,
	    HttpServletRequest request) {
	log.debug("successPaypalPayment->");

	Donation donation = donationDao.getDonationByIdentifier(identifier);

	Payment payment = donationService.getPaypalDonation(donation.getPaymentId());

	if (DonationTransactionStatus.CREATED.getLabel().equals(payment.getState())) {
	    donationService.executePaypalDonation(donation.getPaymentId(), payerId);
	    donationDao.completeDonation(identifier);

	    if ("i".equalsIgnoreCase(platform)) {
		return String.format(metaHttp, "thanksAndCloseIOSBrowserForPaypalMobile.htm");
	    }
	    return String.format(metaHttp, "thanksAndCloseAndroidBrowserForPaypalMobile.htm");
	}
	log.debug("<-successPaypalPayment");
	return null;
    }
}
