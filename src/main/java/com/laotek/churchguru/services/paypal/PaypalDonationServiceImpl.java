package com.laotek.churchguru.services.paypal;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.model.shared.enums.sharedmob.DonationType;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreateProfileResponse;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.Presentation;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.api.payments.WebProfile;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalDonationServiceImpl implements DonationService {

    private static Logger LOG = LoggerFactory.getLogger(PaypalDonationServiceImpl.class);

    @Value("${logoImage}")
    private String logoImage;

    @Value("${org.name}")
    private String orgName;

    private OAuthTokenCredential tokenCredential;

    private String webProfileName;

    @PostConstruct
    public void init() {
	try {
	    tokenCredential = createToken();
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
    }

    @Override
    public Payment createPaypalDonation(Donation donation, String returnUrl, String cancelUrl) {
	LOG.debug("createPaypalDonation->");
	try {
	    if (!isAccessTokenValid()) {
		tokenCredential = createToken();
	    }
	    String accessToken = createAccessToken();
	    if (webProfileName == null) {
		webProfileName = createWebProfileExperience(accessToken, orgName);
	    }
	    Payment createdPayment = createDonationPayment(donation, returnUrl, cancelUrl, webProfileName, accessToken);
	    LOG.debug("<-createPaypalDonation");
	    return createdPayment;
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	    throw new PaypalServiceException(e.getMessage());
	}
    }

    private boolean isAccessTokenValid() {
	if (tokenCredential != null && tokenCredential.expiresIn() > 0) {
	    return true;
	}
	return false;
    }

    private OAuthTokenCredential createToken() throws PayPalRESTException, IOException {
	return Payment.initConfig(new FileInputStream(
		System.getProperty("user.home") + "/.churchguru-deploy/paypal/church.paypal.properties"));
    }

    private String createWebProfileExperience(String accessToken, String siteName) throws PayPalRESTException {

	WebProfile webProfile = null;
	List<WebProfile> profiles = WebProfile.getList(accessToken);
	if (profiles != null && profiles.size() > 0) {
	    int last = profiles.size() - 1;
	    webProfile = profiles.get(last);
	    return webProfile.getId();
	} else {
	    webProfile = new WebProfile(siteName);
	    Presentation presentation = new Presentation();
	    presentation.setBrandName(siteName);
	    presentation.setLogoImage(logoImage);
	    webProfile.setPresentation(presentation);
	    CreateProfileResponse response = webProfile.create(accessToken);
	    return response.getId();
	}

    }

    private Payment createDonationPayment(Donation donation, String returnUrl, String cancelUrl, String experienceId,
	    String accessToken) throws PayPalRESTException {
	List<Transaction> transactions = createDonationPayment(donation);

	Payer payer = new Payer();
	payer.setPaymentMethod("paypal");

	RedirectUrls redirectUrls = new RedirectUrls();
	redirectUrls.setReturnUrl(returnUrl);
	redirectUrls.setCancelUrl(cancelUrl);

	Payment payment = new Payment();
	payment.setExperienceProfileId(experienceId);
	payment.setIntent("sale");
	payment.setPayer(payer);
	payment.setTransactions(transactions);
	payment.setRedirectUrls(redirectUrls);

	Payment createdPayment = payment.create(accessToken);
	return createdPayment;
    }

    private Payment createDonationPayment(Donation donation, String experienceId, String accessToken)
	    throws PayPalRESTException {
	List<Transaction> transactions = createDonationPayment(donation);

	Payer payer = new Payer();
	payer.setPaymentMethod("paypal");

	Payment payment = new Payment();
	payment.setExperienceProfileId(experienceId);
	payment.setIntent("sale");
	payment.setPayer(payer);
	payment.setTransactions(transactions);

	Payment createdPayment = payment.create(accessToken);
	return createdPayment;
    }

    private List<Transaction> createDonationPayment(Donation donation) {
	Details amountDetails = new Details();
	amountDetails.setSubtotal(null);
	amountDetails.setTax(null);

	Amount amount = new Amount();
	amount.setTotal(donation.getAmountTotal().toPlainString());
	amount.setCurrency("GBP");
	amount.setDetails(amountDetails);

	ItemList itemList = new ItemList();
	itemList.setItems(new ArrayList<Item>());

	Item item = new Item();
	item.setName(DonationType.OFFERING.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getOffering().toPlainString());
	itemList.getItems().add(item);

	item = new Item();
	item.setName(DonationType.TITHE.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getTithe().toPlainString());
	itemList.getItems().add(item);

	item = new Item();
	item.setName(DonationType.THANKSGIVING.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getThanksGiving().toPlainString());
	itemList.getItems().add(item);

	item = new Item();
	item.setName(DonationType.BUILDING_FUND.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getBuildingFund().toPlainString());
	itemList.getItems().add(item);

	item = new Item();
	item.setName(DonationType.SPECIAL_OFFERING.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getSpecialOffering().toPlainString());
	itemList.getItems().add(item);

	item = new Item();
	item.setName(DonationType.OTHER.getDesc());
	item.setCurrency("GBP");
	item.setQuantity("1");
	item.setPrice(donation.getOtherOffering().toPlainString());
	itemList.getItems().add(item);

	Transaction transaction = new Transaction();
	transaction.setAmount(amount);
	transaction.setDescription("Giving");
	transaction.setItemList(itemList);

	List<Transaction> transactions = new ArrayList<Transaction>();
	transactions.add(transaction);
	return transactions;
    }

    @Override
    public Payment getPaypalDonation(String paymentId) {
	LOG.debug("getDonation->");
	try {
	    String accessToken = createAccessToken();
	    Payment payment = Payment.get(accessToken, paymentId);
	    LOG.debug("<-getDonation");
	    return payment;
	} catch (Exception e) {
	    e.printStackTrace();
	    LOG.error(e.getMessage(), e);
	    throw new PaypalServiceException(e.getMessage());
	}
    }

    private String createAccessToken() throws Exception {
	LOG.debug("createAccessToken->");
	String accessToken = tokenCredential.getAccessToken();
	LOG.debug("<-createAccessToken");
	return accessToken;
    }

    @Override
    public Payment executePaypalDonation(String paymentId, String payerId) {
	LOG.debug("executeSmsPayment->");
	try {
	    String accessToken = createAccessToken();
	    Payment createdPayment = getPaypalDonation(paymentId);

	    PaymentExecution paymentExecution = new PaymentExecution();
	    paymentExecution.setPayerId(payerId);
	    LOG.debug("<-executeSmsPayment");
	    return createdPayment.execute(accessToken, paymentExecution);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOG.error(e.getMessage(), e);
	    throw new PaypalServiceException(e.getMessage());
	}
    }
}
