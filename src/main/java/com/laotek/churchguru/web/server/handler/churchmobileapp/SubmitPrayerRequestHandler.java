package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.prayerrequest.PrayerRequestDao;
import com.laotek.churchguru.model.shared.enums.Title;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestAction;
import com.laotek.churchguru.web.clientm.activity.prayerrequest.PrayerRequestResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class SubmitPrayerRequestHandler extends AbstractCommandHandler
	implements ActionHandler<PrayerRequestAction, PrayerRequestResult> {

    @Autowired
    private PrayerRequestDao prayerRequestDao;

    @Override
    public PrayerRequestResult execute(PrayerRequestAction action,
	    ExecutionContext context) throws DispatchException {

	prayerRequestDao.save(Title.find(action.getTitle()),
		action.getForename(), action.getSurname(),
		action.getEmailAddress(), action.getMobileNo(),
		action.getMessage());

	return new PrayerRequestResult();
    }

    @Override
    public Class<PrayerRequestAction> getActionType() {
	return PrayerRequestAction.class;
    }

    @Override
    public void rollback(PrayerRequestAction arg0, PrayerRequestResult arg1,
	    ExecutionContext arg2) throws DispatchException {
	// TODO Auto-generated method stub

    }

}
