package com.laotek.churchguru.web.server.handler.churchmobileapp;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetSingleNoticeOrEventAction;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.GetSingleNoticeOrEventResult;
import com.laotek.churchguru.web.client.activity.churchapp.noticeandevent.NoticeOrEventDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetSingleNoticeOrEventHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetSingleNoticeOrEventAction, GetSingleNoticeOrEventResult> {

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Override
    public GetSingleNoticeOrEventResult execute(
	    GetSingleNoticeOrEventAction action, ExecutionContext context)
	    throws DispatchException {

	NoticeAndEvent ne = noticeAndEventDao.getNoticeAndEvent(action.getId(),
		action.getIdentifier());

	NoticeOrEventDto dto = mapNoticeOrEvent(ne);

	GetSingleNoticeOrEventResult result = new GetSingleNoticeOrEventResult(
		dto);
	return result;
    }

    @Override
    public Class<GetSingleNoticeOrEventAction> getActionType() {
	return GetSingleNoticeOrEventAction.class;
    }

    @Override
    public void rollback(GetSingleNoticeOrEventAction arg0,
	    GetSingleNoticeOrEventResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
