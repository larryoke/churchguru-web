package com.laotek.churchguru.web.server.handler.org.mobile;

import java.util.List;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.mobile.instantmessage.NoticeAndEventDao;
import com.laotek.churchguru.daos.org.OrganisationDao;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.web.clientm.activity.notice.titles.GetNoticeAndEventTitlesAction;
import com.laotek.churchguru.web.clientm.activity.notice.titles.GetNoticeAndEventTitlesResult;
import com.laotek.churchguru.web.clientm.activity.notice.titles.NoticeAndEventDto;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetMessageTitlesHandler extends AbstractCommandHandler
	implements
	ActionHandler<GetNoticeAndEventTitlesAction, GetNoticeAndEventTitlesResult> {

    @Autowired
    private NoticeAndEventDao instantMessageDao;

    @Autowired
    private OrganisationDao organisationDao;

    @Override
    public GetNoticeAndEventTitlesResult execute(
	    GetNoticeAndEventTitlesAction action, ExecutionContext context)
	    throws DispatchException {

	List<NoticeAndEventDto> dtos = findMessages(action);

	GetNoticeAndEventTitlesResult result = new GetNoticeAndEventTitlesResult();
	result.setMessages(dtos);

	// if (action.getChurchAppTopicEnum() != null) {
	// String newsTypeLabel = organisationDao
	// .getNewsTypeChurchAppLabel(action.getChurchAppTopicEnum());
	// result.setNewsTypeLabel(newsTypeLabel);
	// }

	return result;
    }

    private List<NoticeAndEventDto> findMessages(
	    GetNoticeAndEventTitlesAction action) {
	int currentIndex = action.getIndex();
	if (currentIndex < 0) {
	    throw new RuntimeException("Index cannot be < 0");

	}
	// else if (currentIndex > temps.size()) {
	// throw new RuntimeException("More more messages available");
	// }
	int currentCounter = action.getCounter();

	List<NoticeAndEvent> messages = instantMessageDao.getNoticeAndEvents(
		BrowseMessagesType.POSTED, currentIndex, currentCounter);

	return mapMessageTitles(messages);
    }

    @Override
    public Class<GetNoticeAndEventTitlesAction> getActionType() {
	return GetNoticeAndEventTitlesAction.class;
    }

    @Override
    public void rollback(GetNoticeAndEventTitlesAction action,
	    GetNoticeAndEventTitlesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
