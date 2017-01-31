package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.services.GoogleServiceNotificationService;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.EnumNoticeOrEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeAndEventActionResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class NoticeAndEventActionsHandler extends AbstractCommandHandler
	implements
	ActionHandler<NoticeAndEventAction, NoticeAndEventActionResult> {

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Autowired
    GoogleServiceNotificationService googleServiceNotificationService;

    @Override
    public NoticeAndEventActionResult execute(NoticeAndEventAction action,
	    ExecutionContext context) throws DispatchException {

	if (EnumNoticeOrEventAction.CREATE.equals(action.getAction())) {
	    NoticeAndEvent ne = noticeAndEventDao.createNoticeAndEvent(
		    action.getSubject(), action.getClientSessionId());
	    return new NoticeAndEventActionResult(ne.getId().intValue(),
		    ne.getIdentifier());

	} else if (EnumNoticeOrEventAction.DELETE.equals(action.getAction())) {
	    noticeAndEventDao.delete(action.getId(), action.getIdentifier());
	    return new NoticeAndEventActionResult();

	} else if (EnumNoticeOrEventAction.DUPLICATE.equals(action.getAction())) {
	    NoticeAndEvent ne = noticeAndEventDao.duplicate(action.getId(),
		    action.getIdentifier(), action.getClientSessionId());
	    return new NoticeAndEventActionResult(ne.getId().intValue(),
		    ne.getIdentifier());

	} else if (EnumNoticeOrEventAction.POST.equals(action.getAction())) {
	    try {
		NoticeAndEvent ne = noticeAndEventDao.getNoticeAndEvent(
			action.getId(), action.getIdentifier());
		noticeAndEventDao.completeNoticeAndEvent(action.getId(),
			action.getIdentifier());
		if (action.isNotifyAppUsers()) {
		    googleServiceNotificationService.sendNotification(ne
			    .getSubject());
		}

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else if (EnumNoticeOrEventAction.REMOVE_PICTURE.equals(action
		.getAction())) {
	    noticeAndEventDao.removePicture(action.getId(),
		    action.getIdentifier());
	}
	return new NoticeAndEventActionResult();
    }

    @Override
    public Class<NoticeAndEventAction> getActionType() {
	return NoticeAndEventAction.class;
    }

    @Override
    public void rollback(NoticeAndEventAction action,
	    NoticeAndEventActionResult arg1, ExecutionContext context)
	    throws DispatchException {

    }
}
