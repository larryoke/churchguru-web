package com.laotek.churchguru.web.server.handler.estore;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.ListeningMessagePicture;
import com.laotek.churchguru.model.ListeningSpeaker;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.listening.ListeningDao;
import com.laotek.churchguru.model.ListeningCategory;
import com.laotek.churchguru.model.ListeningMessage;
import com.laotek.churchguru.web.client.activity.listening.GetEStoreMessageAction;
import com.laotek.churchguru.web.client.activity.listening.GetEStoreMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetEStoreMessageHandler extends AbstractCommandHandler implements
	ActionHandler<GetEStoreMessageAction, GetEStoreMessageResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public GetEStoreMessageResult execute(GetEStoreMessageAction action,
	    ExecutionContext context) throws DispatchException {

	String identifier = action.getIdentifier();

	ListeningMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<ListeningCategory> categories = eStoreDao.getCategories();

	List<ListeningMessagePicture> pictures = eStoreDao
		.getEStoreMessagePicture();

	List<ListeningSpeaker> speakers = eStoreDao.getSpeakers();

	Map<String, Boolean> workersSelectedForFreeMessages = eStoreDao
		.getWorkersSelectedForFreeMessages();

	return new GetEStoreMessageResult(mapMessages(message),
		mapSpeakers(speakers), mapCategories(categories),
		mapMessagePictures(pictures), workersSelectedForFreeMessages);
    }

    @Override
    public Class<GetEStoreMessageAction> getActionType() {
	return GetEStoreMessageAction.class;
    }

    @Override
    public void rollback(GetEStoreMessageAction action,
	    GetEStoreMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
