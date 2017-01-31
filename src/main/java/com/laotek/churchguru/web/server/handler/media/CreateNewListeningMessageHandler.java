package com.laotek.churchguru.web.server.handler.media;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.AudioMessageDao;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageAction;
import com.laotek.churchguru.web.client.activity.audio.CreateNewAudioMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class CreateNewListeningMessageHandler extends AbstractCommandHandler
	implements ActionHandler<CreateNewAudioMessageAction, CreateNewAudioMessageResult> {

    @Autowired
    private AudioMessageDao eStoreDao;

    @Override
    public CreateNewAudioMessageResult execute(CreateNewAudioMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = RandomStringUtils.random(20, true, true);
	String title = action.getTitle();
	eStoreDao.createNewMessage(identifier, title);
	return new CreateNewAudioMessageResult(identifier);
    }

    @Override
    public Class<CreateNewAudioMessageAction> getActionType() {
	return CreateNewAudioMessageAction.class;
    }

    @Override
    public void rollback(CreateNewAudioMessageAction action, CreateNewAudioMessageResult result,
	    ExecutionContext context) throws DispatchException {
    }

}
