package com.laotek.churchguru.web.server.handler.media;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.ListeningDao;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessagesAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessagesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.AudioMessageMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningMessagesHandler extends AbstractCommandHandler
	implements ActionHandler<GetListeningMessagesAction, GetListeningMessagesResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public GetListeningMessagesResult execute(GetListeningMessagesAction action, ExecutionContext context)
	    throws DispatchException {

	List<AudioMessage> messages = eStoreDao.getMessages();
	List<AudioMessageMessageDto> dtos = get(messages);
	return new GetListeningMessagesResult(dtos);
    }

    private List<AudioMessageMessageDto> get(List<AudioMessage> messages) {
	List<AudioMessageMessageDto> dtos = new ArrayList<AudioMessageMessageDto>();
	for (AudioMessage message : messages) {
	    dtos.add(mapMessages(message));
	}
	return dtos;
    }

    @Override
    public Class<GetListeningMessagesAction> getActionType() {
	return GetListeningMessagesAction.class;
    }

    @Override
    public void rollback(GetListeningMessagesAction action, GetListeningMessagesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
