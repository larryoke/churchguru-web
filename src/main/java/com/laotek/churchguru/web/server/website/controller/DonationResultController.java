package com.laotek.churchguru.web.server.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String cancelledPayPalRequest(@RequestParam("identifier") String identifier,
	    @RequestParam("platform") String platform) {
	log.debug("cancelledPayPalRequest->");

	Donation donation = donationDao.getDonationByIdentifier(identifier);

	Payment payment = donationService.getPaypalDonation(donation.getPaymentId());

	if (DonationTransactionStatus.CREATED.getLabel().equals(payment.getState())) {
	    donationDao.cancelDonation(donation.getPaymentId());
	}

	if ("i".equalsIgnoreCase(platform)) {
	    return getCancelAndCloseHTML();
	}
	return String.format(metaHttp, "mobi.htm");
    }

    @RequestMapping(value = "/paypal/success", method = RequestMethod.GET)
    @ResponseBody
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
		return getThanksAndCloseHTML();
	    }
	    return String.format(metaHttp, "mobi.htm");
	}
	log.debug("<-successPaypalPayment");
	return null;
    }

    private String getCancelAndCloseHTML() {
	StringBuffer sb = new StringBuffer();
	sb.append("<html>");
	sb.append("<head>");
	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
	sb.append("<link type=\"text/css\" rel=\"stylesheet\" href=mobile.css>");
	sb.append("<meta name=\"viewport\"");
	sb.append("content=\"width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=0\" />");
	sb.append("</head>");
	sb.append("<body onload=\"window.close()\">	");
	sb.append("<div class=\"paypal-img-layout\"><img src=\"/uploadedphotos/photos/org/logo\" /></div>");
	sb.append("<br/>");
	sb.append("<br/>");
	sb.append("<br/>");
	sb.append(
		"<div class=\"paypal-exit-layout\"><a class=\"btn\" href=\"javascript:history.go(-1)\">Return to Paypal</a></div>");
	sb.append("<br/>");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
    }

    private String getThanksAndCloseHTML() {
	StringBuffer sb = new StringBuffer();

	sb.append("<html>");
	sb.append("<head>");
	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
	sb.append("<link type=\"text/css\" rel=\"stylesheet\" href=mobile.css>");
	sb.append(
		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=0\" />");
	sb.append("</head>");
	sb.append("<body onload=\"window.close()\">");
	sb.append("<div class=\"paypal-img-layout\"><img src=\"/uploadedphotos/photos/org/logo\" /></div>");
	sb.append(
		"<div class=\"paypal-exit-layout\"> <font size=\"4\">Thank you for your gift to the ministry. God bless you<br>");
	sb.append("To continue, please return to the mobile app.</font></div>");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
    }
}
