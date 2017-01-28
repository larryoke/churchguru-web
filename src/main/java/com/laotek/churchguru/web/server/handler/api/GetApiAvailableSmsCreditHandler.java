package com.laotek.churchguru.web.server.handler.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.laotek.churchguru.commons.AvailableCredit;
import com.laotek.churchguru.commons.CreditBalance;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.Organisation;
import com.laotek.churchguru.web.client.activity.api.GetApiAvailableCreditAction;
import com.laotek.churchguru.web.client.activity.api.GetApiAvailableCreditResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

//@Component
@Deprecated
public class GetApiAvailableSmsCreditHandler extends AbstractCommandHandler
	implements ActionHandler<GetApiAvailableCreditAction, GetApiAvailableCreditResult> {

    @Autowired
    private RestTemplate apiRestTemplate;

    @Autowired
    private OrganisationDao organisationDao;

    @Autowired
    @Value("${api.uri}")
    private String apiUri;

    @Override
    public GetApiAvailableCreditResult execute(GetApiAvailableCreditAction action, ExecutionContext context)
	    throws DispatchException {
	try {
	    Organisation org = organisationDao.getOrganisationFromClientSessionId(action.getClientSessionId());

	    CreditBalance creditBalance = apiRestTemplate.getForObject(
		    apiUri + "/services/sms/secure/{subdomain}/creditbalance", CreditBalance.class, org.getSubdomain());

	    AvailableCredit credit = (AvailableCredit) apiRestTemplate
		    .getForObject(apiUri + "/services/sms/availablecredit", AvailableCredit.class);

	    return new GetApiAvailableCreditResult(creditBalance.getQuantity(), credit.getCredit(),
		    credit.getPricePerCredit());
	} catch (Exception e) {
	    throw new RuntimeException(e.getMessage() + " " + apiUri);
	}
    }

    @Override
    public Class<GetApiAvailableCreditAction> getActionType() {
	return GetApiAvailableCreditAction.class;
    }

    @Override
    public void rollback(GetApiAvailableCreditAction action, GetApiAvailableCreditResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
