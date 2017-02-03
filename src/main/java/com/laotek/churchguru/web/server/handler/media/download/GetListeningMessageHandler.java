package com.laotek.churchguru.web.server.handler.media.download;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.AudioMessageDao;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessageCategory;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioMessageSpeaker;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.GetAudioMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningMessageHandler extends AbstractCommandHandler
	implements ActionHandler<GetAudioMessageAction, GetAudioMessageResult> {

    @Autowired
    private AudioMessageDao eStoreDao;

    @Override
    public GetAudioMessageResult execute(GetAudioMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	AudioMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<AudioMessageCategory> categories = eStoreDao.getCategories();

	List<AudioMessagePicture> pictures = eStoreDao.getEStoreMessagePicture();

	List<AudioMessageSpeaker> speakers = eStoreDao.getSpeakers();

	return new GetAudioMessageResult(mapMessages(message), mapSpeakers(speakers), mapCategories(categories),
		mapMessagePictures(pictures));
    }

    @Override
    public Class<GetAudioMessageAction> getActionType() {
	return GetAudioMessageAction.class;
    }

    @Override
    public void rollback(GetAudioMessageAction action, GetAudioMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
