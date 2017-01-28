package com.laotek.churchguru.web.server.handler.org.mobile;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.web.clientm.activity.message.body.GetSingleNoticeAndEventAction;
import com.laotek.churchguru.web.clientm.activity.message.body.GetSingleNoticeAndEventResult;
import com.laotek.churchguru.web.clientm.activity.message.titles.NoticeAndEventDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class SingleNoticeAndEventHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetSingleNoticeAndEventAction, GetSingleNoticeAndEventResult> {

    @Autowired
    private NoticeAndEventDao instantMessageDao;

    @Override
    public GetSingleNoticeAndEventResult execute(
	    GetSingleNoticeAndEventAction action, ExecutionContext context)
	    throws DispatchException {

	NoticeAndEvent letter = instantMessageDao.getNoticeAndEvent(action
		.getId());

	NoticeAndEventDto dto = mapMessageTitle(letter);
	dto.setCreatedTimeDesc(formatCreatedTimeDesc(letter.getCreatedDate()));
	dto.setBody(letter.getMessageBody());
	return new GetSingleNoticeAndEventResult(dto.getTitle(), dto.getBody(),
		dto.getCreatedTimeDesc(), dto.getEventDate());
    }

    @Override
    public Class<GetSingleNoticeAndEventAction> getActionType() {
	return GetSingleNoticeAndEventAction.class;
    }

    @Override
    public void rollback(GetSingleNoticeAndEventAction arg0,
	    GetSingleNoticeAndEventResult arg1, ExecutionContext arg2)
	    throws DispatchException {

    }

}
