package com.laotek.churchguru.web.server.handler.media;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.AudioMessageDao;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageResult;
import com.laotek.churchguru.web.client.activity.audio.SubmitAudioMessageAction;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.listening.AudioMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class SubmitListeningMessageHandler extends AbstractCommandHandler
	implements ActionHandler<SubmitAudioMessageAction, SubmitAudioMessageResult> {

    @Autowired
    private AudioMessageDao eStoreDao;

    @Override
    public SubmitAudioMessageResult execute(SubmitAudioMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	AudioMessage message = eStoreDao.getMessageByIdentifier(identifier);
	message.setSalePointPerMessage(action.getSalesChargePerMessage());
	message.setDescription(action.getBriefDescription());
	message.setLocation(action.getLocation());
	message.setLastUpdatedDate(new Date());
	message.setMessageDate(action.getMessageDate());

	Map<String, String> otherDetails = new HashMap<String, String>();
	if (action.getExistingSpeaker() != null) {
	    otherDetails.put("existingSpeakerIdentifier", action.getExistingSpeaker());
	} else {
	    otherDetails.put("newSpeakerIdentifier", RandomStringUtils.random(20, true, true));
	    otherDetails.put("newSpeakerTitle", action.getNewSpeaker().getTitle().name());
	    otherDetails.put("newSpeakerForenames", action.getNewSpeaker().getForenames());
	    otherDetails.put("newSpeakerSurname", action.getNewSpeaker().getSurname());
	    otherDetails.put("newSpeakerDesc", action.getSpeakerDesc());
	}

	if (action.getExistingCategory() != null) {
	    otherDetails.put("existingCategoryIdentifier", action.getExistingCategory());
	} else {
	    otherDetails.put("newCategoryIdentifier", RandomStringUtils.random(20, true, true));
	    otherDetails.put("newCategoryName", action.getNewCategory());
	}

	eStoreDao.updateMessage(message, otherDetails);

	return new SubmitAudioMessageResult(map(message));
    }

    private AudioMessageDto map(AudioMessage message) {
	AudioMessageDto dto = new AudioMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitAudioMessageAction> getActionType() {
	return SubmitAudioMessageAction.class;
    }

    @Override
    public void rollback(SubmitAudioMessageAction action, SubmitAudioMessageResult result,
	    ExecutionContext context) throws DispatchException {
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
