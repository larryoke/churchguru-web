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
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageAction;
import com.laotek.churchguru.web.client.activity.listening.GetListeningMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

@Component
public class GetListeningMessageHandler extends AbstractCommandHandler implements
        ActionHandler<GetListeningMessageAction, GetListeningMessageResult> {

    @Autowired
    private ListeningDao eStoreDao;

    @Override
    public GetListeningMessageResult execute(GetListeningMessageAction action,
                                             ExecutionContext context) throws DispatchException {

        String identifier = action.getIdentifier();

        ListeningMessage message = eStoreDao.getMessageByIdentifier(identifier);

        List<ListeningCategory> categories = eStoreDao.getCategories();

        List<ListeningMessagePicture> pictures = eStoreDao
                .getEStoreMessagePicture();

        List<ListeningSpeaker> speakers = eStoreDao.getSpeakers();

        Map<String, Boolean> workersSelectedForFreeMessages = eStoreDao
                .getWorkersSelectedForFreeMessages();

        return new GetListeningMessageResult(mapMessages(message),
                mapSpeakers(speakers), mapCategories(categories),
                mapMessagePictures(pictures), workersSelectedForFreeMessages);
    }

    @Override
    public Class<GetListeningMessageAction> getActionType() {
        return GetListeningMessageAction.class;
    }

    @Override
    public void rollback(GetListeningMessageAction action,
                         GetListeningMessageResult result, ExecutionContext context)
            throws DispatchException {
    }

}
