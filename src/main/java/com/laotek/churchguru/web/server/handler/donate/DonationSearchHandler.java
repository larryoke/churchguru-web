package com.laotek.churchguru.web.server.handler.donate;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.donation.DonationDao;
import com.laotek.churchguru.daos.user.UserAuditDao;
import com.laotek.churchguru.model.Donation;
import com.laotek.churchguru.web.client.activity.donation.DonationDto;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchAction;
import com.laotek.churchguru.web.client.activity.donation.DonationSearchResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class DonationSearchHandler extends AbstractCommandHandler implements
	ActionHandler<DonationSearchAction, DonationSearchResult> {

    @Autowired
    private DonationDao donationDao;

    @Autowired
    private UserAuditDao userAuditService;

    @Override
    public DonationSearchResult execute(DonationSearchAction action,
	    ExecutionContext context) throws DispatchException {

	List<Donation> donations = donationDao.search(action.getSurname(),
		action.getForename(), action.getDonationTypes(),
		action.getDonationTransactionStatuses(), action.getFromDate(),
		action.getToDate(), action.getMaxResult());

	List<DonationDto> dtos = getDonations(donations);

	return new DonationSearchResult(dtos);
    }

    @Override
    public Class<DonationSearchAction> getActionType() {
	return DonationSearchAction.class;
    }

    @Override
    public void rollback(DonationSearchAction action,
	    DonationSearchResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
