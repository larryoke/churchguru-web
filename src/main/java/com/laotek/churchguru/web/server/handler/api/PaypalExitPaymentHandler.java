package com.laotek.churchguru.web.server.handler.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.laotek.churchguru.commons.PaymentResult;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.web.client.activity.api.PaypalExitPaymentAction;
import com.laotek.churchguru.web.client.activity.api.PaypalExitPaymentResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

//@Component
@Deprecated
public class PaypalExitPaymentHandler extends AbstractCommandHandler
	implements ActionHandler<PaypalExitPaymentAction, PaypalExitPaymentResult> {

    @Autowired
    private RestTemplate apiRestTemplate;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${api.uri}")
    private String apiUri;

    @Override
    public PaypalExitPaymentResult execute(PaypalExitPaymentAction action, ExecutionContext context)
	    throws DispatchException {

	PaymentResult result = null;
	Map<String, String> var = new HashMap<String, String>();
	if ("success".equals(action.getStatus())) {
	    var.put("identifier", action.getIdentifier());
	    var.put("payerId", action.getPayerId());
	    result = apiRestTemplate.getForObject(
		    apiUri + "/services/secure/sms/exitpayment/success/{identifier}/{payerId}", PaymentResult.class,
		    var);
	    return new PaypalExitPaymentResult(result.getAmountPaid(), result.getQuantityBought(),
		    result.getLatestQuantity(), result.isCancelled());
	} else {
	    var.put("identifier", action.getIdentifier());
	    result = apiRestTemplate.getForObject(apiUri + "/services/secure/sms/exitpayment/cancel/{identifier}",
		    PaymentResult.class, var);
	    return new PaypalExitPaymentResult(result.getAmountPaid(), result.getQuantityBought(),
		    result.getLatestQuantity(), true);

	}
    }

    @Override
    public Class<PaypalExitPaymentAction> getActionType() {
	return PaypalExitPaymentAction.class;
    }

    @Override
    public void rollback(PaypalExitPaymentAction action, PaypalExitPaymentResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
