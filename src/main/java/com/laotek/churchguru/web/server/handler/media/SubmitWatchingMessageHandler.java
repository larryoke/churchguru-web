package com.laotek.churchguru.web.server.handler.media;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.media.WatchingDao;
import com.laotek.churchguru.model.WatchingMessage;
import com.laotek.churchguru.web.client.activity.media.watching.SubmitWatchingMessageAction;
import com.laotek.churchguru.web.client.activity.media.watching.SubmitWatchingMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.watching.WatchingMessageDto;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

@Component
public class SubmitWatchingMessageHandler extends AbstractCommandHandler
	implements ActionHandler<SubmitWatchingMessageAction, SubmitWatchingMessageResult> {

    @Autowired
    private WatchingDao eStoreDao;

    @Override
    public SubmitWatchingMessageResult execute(SubmitWatchingMessageAction action, ExecutionContext context)
	    throws DispatchException {

	String identifier = action.getIdentifier();

	WatchingMessage message = eStoreDao.getMessageByIdentifier(identifier);
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

	return new SubmitWatchingMessageResult(map(message));
    }

    private WatchingMessageDto map(WatchingMessage message) {
	WatchingMessageDto dto = new WatchingMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitWatchingMessageAction> getActionType() {
	return SubmitWatchingMessageAction.class;
    }

    @Override
    public void rollback(SubmitWatchingMessageAction action, SubmitWatchingMessageResult result,
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
