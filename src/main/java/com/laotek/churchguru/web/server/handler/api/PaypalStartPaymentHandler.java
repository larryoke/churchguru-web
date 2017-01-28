package com.laotek.churchguru.web.server.handler.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.laotek.churchguru.commons.PaymentDetails;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.client.activity.api.PaypalStartPaymentAction;
import com.laotek.churchguru.web.client.activity.api.PaypalStartPaymentResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.PaymentDetailsDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

//@Component
@Deprecated
public class PaypalStartPaymentHandler extends AbstractCommandHandler
	implements ActionHandler<PaypalStartPaymentAction, PaypalStartPaymentResult> {

    @Autowired
    private RestTemplate apiRestTemplate;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${api.uri}")
    private String apiUri;

    @Autowired
    @Value("${hostname}")
    private String hostName;

    @Override
    public PaypalStartPaymentResult execute(PaypalStartPaymentAction action, ExecutionContext context)
	    throws DispatchException {
	String clientSessionId = action.getClientSessionId();
	Organisation org = organisationDao.getOrganisationFromClientSessionId(clientSessionId);

	Map<String, String> var = new HashMap<String, String>();
	var.put("amountRequired", String.valueOf(action.getAmountOfCredit()));
	var.put("hostname", hostName);
	var.put("contextpath", "communicator");
	var.put("subdomain", org.getSubdomain());
	PaymentDetails shoppingCart = (PaymentDetails) apiRestTemplate.getForObject(
		apiUri + "/services/secure/sms/startpayment/{hostname}/{contextpath}/{subdomain}/{amountRequired}",
		PaymentDetails.class, var);

	PaymentDetailsDto dto = map(shoppingCart);
	return new PaypalStartPaymentResult(dto);
    }

    private PaymentDetailsDto map(PaymentDetails paymentDetails) {
	PaymentDetailsDto dto = new PaymentDetailsDto();
	dto.setPaymentId(paymentDetails.getPaymentId());
	dto.setApprovalUrl(paymentDetails.getApprovalUrl());
	return dto;
    }

    @Override
    public Class<PaypalStartPaymentAction> getActionType() {
	return PaypalStartPaymentAction.class;
    }

    @Override
    public void rollback(PaypalStartPaymentAction action, PaypalStartPaymentResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
