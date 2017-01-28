package com.laotek.churchguru.web.server.handler.mobile;

import java.math.BigDecimal;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.donation.DonationDao;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.shared.enums.CountryCode;
import com.laotek.churchguru.model.shared.enums.Title;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.laotek.churchguru.services.paypal.DonationService;
import com.laotek.churchguru.services.paypal.PaypalServiceException;
import com.laotek.churchguru.web.clientm.activity.home.SubmitDonationDetailsAction;
import com.laotek.churchguru.web.clientm.activity.home.SubmitDonationDetailsResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.PaypalException;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;

@Component
public class SubmitDonationDetailsHandler extends AbstractCommandHandler
	implements
	ActionHandler<SubmitDonationDetailsAction, SubmitDonationDetailsResult> {

    static Logger log = LoggerFactory
	    .getLogger(SubmitDonationDetailsHandler.class);

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    private DonationService donationService;

    @Autowired
    private DonationDao donationDao;

    @Autowired
    @Value("${hostname}")
    private String hostname;

    @Override
    public SubmitDonationDetailsResult execute(
	    SubmitDonationDetailsAction action, ExecutionContext context)
	    throws DispatchException {
	log.debug("execute->");

	SubmitDonationDetailsResult result = new SubmitDonationDetailsResult();

	try {

	    Map<String, String> giveDetails = action.getDetails();
	    Map<DonationType, BigDecimal> payments = action.getPayments();

	    Donation donation = new Donation();

	    BigDecimal total = new BigDecimal(0);
	    for (Map.Entry<DonationType, BigDecimal> entry : payments
		    .entrySet()) {
		if (DonationType.OFFERING.equals(entry.getKey())) {
		    donation.setOffering(entry.getValue());
		    total = total.add(entry.getValue());

		} else if (DonationType.TITHE.equals(entry.getKey())) {
		    donation.setTithe(entry.getValue());
		    total = total.add(entry.getValue());

		} else if (DonationType.THANKSGIVING.equals(entry.getKey())) {
		    donation.setThanksGiving(entry.getValue());
		    total = total.add(entry.getValue());

		} else if (DonationType.SPECIAL_OFFERING.equals(entry.getKey())) {
		    donation.setSpecialOffering(entry.getValue());
		    total = total.add(entry.getValue());

		} else if (DonationType.BUILDING_FUND.equals(entry.getKey())) {
		    donation.setBuildingFund(entry.getValue());
		    total = total.add(entry.getValue());

		} else if (DonationType.OTHER.equals(entry.getKey())) {
		    String otherGivingType = giveDetails.get("otherGivingType");
		    donation.setOtherGivingType(otherGivingType);
		    donation.setOtherOffering(entry.getValue());
		    total = total.add(entry.getValue());
		}
	    }

	    donation.setAmountTotal(total);

	    String identifier = RandomStringUtils.random(20, true, true);
	    donation.setIdentifier(identifier);

	    donation.setMember(Boolean.valueOf(giveDetails.get("member")));
	    donation.setInMailingList(Boolean.valueOf(giveDetails
		    .get("inMailingList")));

	    if (giveDetails.get("gift-aid-today") != null) {
		donation.setGiftAidToday(true);
	    }

	    if (giveDetails.get("gift-aid-past4Years") != null) {
		donation.setGiftAidPast4Years(true);
	    }

	    if (giveDetails.get("gift-aid-future") != null) {
		donation.setGiftAidFuture(true);
	    }
	    log.debug("donation set");

	    // donor details
	    String title = giveDetails.get("title");
	    donation.setTitle(Title.find(title));
	    donation.setForenames(giveDetails.get("forenames"));
	    donation.setSurname(giveDetails.get("surname"));
	    donation.setMobile("07777");
	    donation.setMobileCountryCode(CountryCode.UK);

	    // address
	    donation.setEmailAddress(giveDetails.get("emailAddress"));
	    donation.setAddressLine1(giveDetails.get("addressLine1"));
	    donation.setAddressLine2(giveDetails.get("addressLine2"));
	    donation.setPostcode(giveDetails.get("postcode"));

	    boolean isIOs = Boolean.valueOf(giveDetails.get("isIOs"));

	    Payment payment = null;
	    String returnUrl = null;
	    String cancelUrl = null;
	    if (isIOs) {
		returnUrl = createReturnUrl(hostname, identifier, "i");
		cancelUrl = createCancelUrl(hostname, identifier, "i");
	    } else {
		returnUrl = createReturnUrl(hostname, identifier, "a");
		cancelUrl = createCancelUrl(hostname, identifier, "a");
	    }

	    payment = donationService.createPaypalDonation(donation, returnUrl,
		    cancelUrl);

	    // set paypal id
	    String id = payment.getId();
	    donation.setPaymentId(id);

	    log.debug("createDonation->");
	    donationDao.createDonation(donation);
	    log.debug("<-createDonation");

	    for (Links link : payment.getLinks()) {
		if ("approval_url".equals(link.getRel())) {
		    result.setPaypalApprovalUrl(link.getHref());
		    break;
		}
	    }
	} catch (PaypalServiceException e) {
	    throw new PaypalException(
		    "Unable to connect to PayPal. Please try again later");
	} catch (Exception e) {
	    throw new RuntimeException(
		    "Unable to complete payment. Please try again later");
	}

	return result;
    }

    @Override
    public Class<SubmitDonationDetailsAction> getActionType() {
	return SubmitDonationDetailsAction.class;
    }

    @Override
    public void rollback(SubmitDonationDetailsAction action,
	    SubmitDonationDetailsResult result, ExecutionContext context)
	    throws DispatchException {

    }

    protected String createReturnUrl(String hostname, String identifier,
	    String platform) {
	StringBuffer sb = new StringBuffer();
	sb.append("https://");
	sb.append(hostname);
	sb.append("/donationresult/paypal/success");
	sb.append("?identifier=");
	sb.append(identifier);
	sb.append("&platform=");
	sb.append(platform);
	return sb.toString();
    }

    protected String createCancelUrl(String hostname, String identifier,
	    String platform) {
	StringBuffer sb = new StringBuffer();
	sb.append("https://");
	sb.append(hostname);
	sb.append("/donationresult/paypal/cancelled");
	sb.append("?identifier=");
	sb.append(identifier);
	sb.append("&platform=");
	sb.append(platform);
	return sb.toString();
    }
}
