package com.laotek.churchguru.web.server.handler.estore;

import java.util.ArrayList;
import java.util.List;

import com.laotek.churchguru.model.ListeningMessage;
import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.listening.ListeningDao;
import com.laotek.churchguru.web.client.activity.listening.GetEStoreMessagesAction;
import com.laotek.churchguru.web.client.activity.listening.GetEStoreMessagesResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;

@Component
public class GetEStoreMessagesHandler extends AbstractCommandHandler implements
	ActionHandler<GetEStoreMessagesAction, GetEStoreMessagesResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public GetEStoreMessagesResult execute(GetEStoreMessagesAction action,
	    ExecutionContext context) throws DispatchException {

	List<ListeningMessage> messages = eStoreDao.getMessages();
	List<EStoreMessageDto> dtos = get(messages);
	return new GetEStoreMessagesResult(dtos);
    }

    private List<EStoreMessageDto> get(List<ListeningMessage> messages) {
	List<EStoreMessageDto> dtos = new ArrayList<EStoreMessageDto>();
	for (ListeningMessage message : messages) {
	    dtos.add(mapMessages(message));
	}
	return dtos;
    }

    @Override
    public Class<GetEStoreMessagesAction> getActionType() {
	return GetEStoreMessagesAction.class;
    }

    @Override
    public void rollback(GetEStoreMessagesAction action,
	    GetEStoreMessagesResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
