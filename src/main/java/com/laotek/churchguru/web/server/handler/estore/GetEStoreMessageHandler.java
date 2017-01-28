package com.laotek.churchguru.web.server.handler.estore;

import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.estore.EStoreDao;
import com.laotek.churchguru.model.EStoreCategory;
import com.laotek.churchguru.model.EStoreMessage;
import com.laotek.churchguru.model.EStoreMessagePicture;
import com.laotek.churchguru.model.EStoreSpeaker;
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessageAction;
import com.laotek.churchguru.web.client.activity.estore.GetEStoreMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetEStoreMessageHandler extends AbstractCommandHandler implements
	ActionHandler<GetEStoreMessageAction, GetEStoreMessageResult> {

    @Autowired
    private EStoreDao eStoreDao;

    @Override
    public GetEStoreMessageResult execute(GetEStoreMessageAction action,
	    ExecutionContext context) throws DispatchException {

	String identifier = action.getIdentifier();

	EStoreMessage message = eStoreDao.getMessageByIdentifier(identifier);

	List<EStoreCategory> categories = eStoreDao.getCategories();

	List<EStoreMessagePicture> pictures = eStoreDao
		.getEStoreMessagePicture();

	List<EStoreSpeaker> speakers = eStoreDao.getSpeakers();

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
