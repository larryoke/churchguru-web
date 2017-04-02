package com.laotek.churchguru.web.server.handler.media.download;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessagePicture;
import com.laotek.churchguru.model.MediaMessageSpeaker;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.GetMediaMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetMediaMessageHandler extends AbstractCommandHandler
	implements ActionHandler<GetMediaMessageAction, GetMediaMessageResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public GetMediaMessageResult execute(GetMediaMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	MediaMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<MediaMessageCategory> categories = eStoreDao.getCategories();

	List<MediaMessagePicture> pictures = eStoreDao.getEStoreMessagePicture();

	List<MediaMessageSpeaker> speakers = eStoreDao.getSpeakers();

	return new GetMediaMessageResult(mapMessages(message), mapSpeakers(speakers), mapCategories(categories),
		mapMessagePictures(pictures));
    }

    @Override
    public Class<GetMediaMessageAction> getActionType() {
	return GetMediaMessageAction.class;
    }

    @Override
    public void rollback(GetMediaMessageAction action, GetMediaMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
