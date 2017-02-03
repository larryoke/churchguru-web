package com.laotek.churchguru.web.server.handler.media.download;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.AudioMessageDao;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessagesAction;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessagesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningMessagesHandler extends AbstractCommandHandler
	implements ActionHandler<GetAudioMessagesAction, GetAudioMessagesResult> {

    @Autowired
    private AudioMessageDao eStoreDao;

    @Override
    public GetAudioMessagesResult execute(GetAudioMessagesAction action, ExecutionContext context)
	    throws DispatchException {

	List<AudioMessage> messages = eStoreDao.getMessages();
	List<AudioMessageDto> dtos = get(messages);
	return new GetAudioMessagesResult(dtos);
    }

    private List<AudioMessageDto> get(List<AudioMessage> messages) {
	List<AudioMessageDto> dtos = new ArrayList<AudioMessageDto>();
	for (AudioMessage message : messages) {
	    dtos.add(mapMessages(message));
	}
	return dtos;
    }

    @Override
    public Class<GetAudioMessagesAction> getActionType() {
	return GetAudioMessagesAction.class;
    }

    @Override
    public void rollback(GetAudioMessagesAction action, GetAudioMessagesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
