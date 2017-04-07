package com.laotek.churchguru.web.server.handler.media.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.web.client.activity.media.UpdateMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.UpdateMediaMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class UpdateMediaMessageHandler extends AbstractCommandHandler
	implements ActionHandler<UpdateMediaMessageAction, UpdateMediaMessageResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public UpdateMediaMessageResult execute(UpdateMediaMessageAction action, ExecutionContext context)
	    throws DispatchException {

	if ("speaker".equals(action.getUploadType())) {
	    eStoreDao.updateSpeakerPictureURL(action.getIdentity(), action.getDownloadUrl());

	} else if ("desc".equals(action.getUploadType())) {
	    eStoreDao.updateDescPictureURL(action.getIdentity(), action.getDownloadUrl());

	} else if ("message".equals(action.getUploadType())) {
	    eStoreDao.updateMediaMessageURL(action.getIdentity(), action.getDownloadUrl());
	}

	UpdateMediaMessageResult result = new UpdateMediaMessageResult();
	return result;
    }

    @Override
    public Class<UpdateMediaMessageAction> getActionType() {
	return UpdateMediaMessageAction.class;
    }

    @Override
    public void rollback(UpdateMediaMessageAction action, UpdateMediaMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

}
