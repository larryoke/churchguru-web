package com.laotek.churchguru.web.server.handler.media.download;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessagesResult;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessagesAction;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMediaMessagesHandler extends AbstractCommandHandler
	implements ActionHandler<GetMediaMessagesAction, GetMediaMessagesResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public GetMediaMessagesResult execute(GetMediaMessagesAction action, ExecutionContext context)
	    throws DispatchException {

	List<MediaMessage> messages = eStoreDao.getMessages();
	List<MediaMessageDto> dtos = get(messages);
	return new GetMediaMessagesResult(dtos);
    }

    private List<MediaMessageDto> get(List<MediaMessage> messages) {
	List<MediaMessageDto> dtos = new ArrayList<MediaMessageDto>();
	for (MediaMessage message : messages) {
	    dtos.add(mapMessages(message));
	}
	return dtos;
    }

    @Override
    public Class<GetMediaMessagesAction> getActionType() {
	return GetMediaMessagesAction.class;
    }

    @Override
    public void rollback(GetMediaMessagesAction action, GetMediaMessagesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
