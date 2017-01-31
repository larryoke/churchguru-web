package com.laotek.churchguru.web.server.handler.media;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.model.WatchingMessage;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessagesAction;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessagesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetWatchingMessagesHandler extends AbstractCommandHandler
	implements ActionHandler<GetWatchingMessagesAction, GetWatchingMessagesResult> {

    @Autowired
    private WatchingDao eStoreDao;

    @Override
    public GetWatchingMessagesResult execute(GetWatchingMessagesAction action, ExecutionContext context)
	    throws DispatchException {

	List<WatchingMessage> messages = eStoreDao.getMessages();
	List<WatchingMessageDto> dtos = get(messages);
	return new GetWatchingMessagesResult(dtos);
    }

    private List<WatchingMessageDto> get(List<WatchingMessage> messages) {
	List<WatchingMessageDto> dtos = new ArrayList<WatchingMessageDto>();
	for (WatchingMessage message : messages) {
	    dtos.add(mapWatchingMessages(message));
	}
	return dtos;
    }

    @Override
    public Class<GetWatchingMessagesAction> getActionType() {
	return GetWatchingMessagesAction.class;
    }

    @Override
    public void rollback(GetWatchingMessagesAction action, GetWatchingMessagesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
