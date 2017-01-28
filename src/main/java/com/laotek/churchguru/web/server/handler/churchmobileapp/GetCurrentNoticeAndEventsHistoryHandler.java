package com.laotek.churchguru.web.server.handler.churchmobileapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryAction;
import com.laotek.churchguru.web.client.activity.churchapp.instantmessage.GetCurrentNoticesAndEventsHistoryResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.instantmessage.MessageDto;

@Component
public class GetCurrentNoticeAndEventsHistoryHandler extends
	AbstractCommandHandler
	implements
	ActionHandler<GetCurrentNoticesAndEventsHistoryAction, GetCurrentNoticesAndEventsHistoryResult> {

    @Autowired
    private NoticeAndEventDao noticeAndEventDao;

    @Override
    public GetCurrentNoticesAndEventsHistoryResult execute(
	    GetCurrentNoticesAndEventsHistoryAction action,
	    ExecutionContext context) throws DispatchException {

	int index = action.getCurrentIndex();
	int count = action.getCount();
	BrowseMessagesType browseMessagesType = action.getBrowseMessagesType();
	List<NoticeAndEvent> messages = noticeAndEventDao.getNoticeAndEvents(
		browseMessagesType, index, count);

	List<MessageDto> dtos = convertMessages(messages);

	GetCurrentNoticesAndEventsHistoryResult result = new GetCurrentNoticesAndEventsHistoryResult(
		dtos);
	return result;
    }

    @Override
    public Class<GetCurrentNoticesAndEventsHistoryAction> getActionType() {
	return GetCurrentNoticesAndEventsHistoryAction.class;
    }

    @Override
    public void rollback(GetCurrentNoticesAndEventsHistoryAction arg0,
	    GetCurrentNoticesAndEventsHistoryResult arg1,
	    ExecutionContext context) throws DispatchException {

    }

    private List<MessageDto> convertMessages(List<NoticeAndEvent> messages) {
	List<MessageDto> dtos = new ArrayList<MessageDto>();
	for (NoticeAndEvent im : messages) {
	    MessageDto dto = new MessageDto(im.getId().intValue(),
		    im.getIdentifier(), im.getSubject(), im.getMessageBody(),
		    format(im.getEventDate()));
	    dtos.add(dto);
	}
	return dtos;
    }

    private String format(Date date) {
	if (date == null) {
	    return "";
	}
	SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM, yyyy, HH:mm");
	return sdf.format(date);
    }

}
