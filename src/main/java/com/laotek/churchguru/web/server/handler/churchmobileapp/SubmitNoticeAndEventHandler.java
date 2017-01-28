package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.services.GoogleServiceNotificationService;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.SubmitNoticeAndEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.SubmitNoticeAndEventResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class SubmitNoticeAndEventHandler extends AbstractCommandHandler
	implements
	ActionHandler<SubmitNoticeAndEventAction, SubmitNoticeAndEventResult> {

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Autowired
    GoogleServiceNotificationService googleServiceNotificationService;

    @Override
    public SubmitNoticeAndEventResult execute(
	    SubmitNoticeAndEventAction action, ExecutionContext context)
	    throws DispatchException {

	try {

	    noticeAndEventDao.postNoticeAndEventForPreview(action.getKey(),
		    action.getIdentifier(), action.getSubject(),
		    action.getMessage(), action.getEventDate(),
		    action.getEventTime(), action.getClientSessionId());

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return new SubmitNoticeAndEventResult();
    }

    @Override
    public Class<SubmitNoticeAndEventAction> getActionType() {
	return SubmitNoticeAndEventAction.class;
    }

    @Override
    public void rollback(SubmitNoticeAndEventAction arg0,
	    SubmitNoticeAndEventResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
