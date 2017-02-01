package com.laotek.churchguru.web.server.handler.media;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.model.VideoMessage;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessagesAction;
import com.laotek.churchguru.web.client.activity.media.watching.GetWatchingMessagesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.watching.VideoMessageDto;

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

	List<VideoMessage> messages = eStoreDao.getMessages();
	List<VideoMessageDto> dtos = new ArrayList<VideoMessageDto>();
	messages.stream().forEach(message -> dtos.add(mapWatchingMessages(message)));
	return new GetWatchingMessagesResult(dtos);
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
