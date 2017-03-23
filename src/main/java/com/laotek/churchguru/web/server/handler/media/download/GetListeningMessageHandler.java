package com.laotek.churchguru.web.server.handler.media.download;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessagePicture;
import com.laotek.churchguru.model.MediaMessageSpeaker;
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
    private MediaMessageDao eStoreDao;

    @Override
    public GetAudioMessageResult execute(GetAudioMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	MediaMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<MediaMessageCategory> categories = eStoreDao.getCategories();

	List<MediaMessagePicture> pictures = eStoreDao.getEStoreMessagePicture();

	List<MediaMessageSpeaker> speakers = eStoreDao.getSpeakers();

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
