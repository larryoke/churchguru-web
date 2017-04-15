package com.laotek.churchguru.web.server.handler.media.download;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.MediaMessageDao;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageAction;
import com.laotek.churchguru.web.client.activity.media.SubmitMediaMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.MediaMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class SubmitMediaMessageHandler extends AbstractCommandHandler
	implements ActionHandler<SubmitMediaMessageAction, SubmitMediaMessageResult> {

    @Autowired
    private MediaMessageDao eStoreDao;

    @Override
    public SubmitMediaMessageResult execute(SubmitMediaMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	MediaMessage message = eStoreDao.getMessageByIdentifier(identifier);
	message.setSalePointPerMessage(action.getSalesChargePerMessage());
	message.setDescription(action.getBriefDescription());
	message.setLocation(action.getLocation());
	message.setLastUpdatedDate(new Date());
	message.setMessageDate(action.getMessageDate());
	message.setMessageStatus(action.getStatus());

	String newSpeakerIdentifier = null;
	String newCategoryIdentifier = null;

	Map<String, String> otherDetails = new HashMap<String, String>();
	if (action.getExistingSpeaker() != null) {
	    otherDetails.put("existingSpeakerIdentifier", action.getExistingSpeaker());
	} else {
	    newSpeakerIdentifier = RandomStringUtils.random(20, true, true);
	    otherDetails.put("newSpeakerIdentifier", newSpeakerIdentifier);
	    otherDetails.put("newSpeakerTitle", action.getNewSpeaker().getTitle().name());
	    otherDetails.put("newSpeakerForenames", action.getNewSpeaker().getForenames());
	    otherDetails.put("newSpeakerSurname", action.getNewSpeaker().getSurname());
	    otherDetails.put("newSpeakerDesc", action.getSpeakerDesc());
	}

	if (action.getExistingCategory() != null) {
	    otherDetails.put("existingCategoryIdentifier", action.getExistingCategory());
	} else {
	    newCategoryIdentifier = RandomStringUtils.random(20, true, true);
	    otherDetails.put("newCategoryIdentifier", newCategoryIdentifier);
	    otherDetails.put("newCategoryName", action.getNewCategory());
	}

	eStoreDao.updateMessage(message, otherDetails);

	SubmitMediaMessageResult result = new SubmitMediaMessageResult(map(message));
	result.setNewSpeakerIdentity(newSpeakerIdentifier);
	return result;
    }

    private MediaMessageDto map(MediaMessage message) {
	MediaMessageDto dto = new MediaMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitMediaMessageAction> getActionType() {
	return SubmitMediaMessageAction.class;
    }

    @Override
    public void rollback(SubmitMediaMessageAction action, SubmitMediaMessageResult result, ExecutionContext context)
	    throws DispatchException {
    }

    private String reformatWorkersWithFreeAccessToMessage(List<String> ids) {
	StringBuffer sb = new StringBuffer();
	for (String id : ids) {
	    id = id.replace("&lt;", "<");
	    id = id.replace("&gt;", ">");
	    sb.append(id);
	    if (ids.indexOf(id) + 1 < ids.size()) {
		sb.append(",");
	    }
	}
	return sb.toString();
    }

}
