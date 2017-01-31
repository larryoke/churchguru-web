package com.laotek.churchguru.web.server.handler.media;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.ListeningDao;
import com.laotek.churchguru.model.AudioCategory;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioSpeaker;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class GetListeningMessageHandler extends AbstractCommandHandler
	implements ActionHandler<GetListeningMessageAction, GetListeningMessageResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public GetListeningMessageResult execute(GetListeningMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	AudioMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<AudioCategory> categories = eStoreDao.getCategories();

	List<AudioMessagePicture> pictures = eStoreDao.getEStoreMessagePicture();

	List<AudioSpeaker> speakers = eStoreDao.getSpeakers();

	Map<String, Boolean> workersSelectedForFreeMessages = eStoreDao.getWorkersSelectedForFreeMessages();

	return new GetListeningMessageResult(mapMessages(message), mapSpeakers(speakers), mapCategories(categories),
		mapMessagePictures(pictures), workersSelectedForFreeMessages);
    }

    @Override
    public Class<GetListeningMessageAction> getActionType() {
	return GetListeningMessageAction.class;
    }

    @Override
    public void rollback(GetListeningMessageAction action, GetListeningMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
