package com.laotek.churchguru.web.server.handler.estore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laotek.churchguru.daos.estore.EStoreDao;
import com.laotek.churchguru.model.EStoreMessage;
import com.laotek.churchguru.model.shared.enums.EStoreNotificationType;
import com.laotek.churchguru.web.client.activity.estore.SubmitEStoreMessageAction;
import com.laotek.churchguru.web.client.activity.estore.SubmitEStoreMessageResult;
import com.laotek.churchguru.web.server.handler.AbstractCommandHandler;
import com.laotek.churchguru.web.shared.estore.EStoreMessageDto;

@Component
public class SubmitEStoreMessageHandler extends AbstractCommandHandler
	implements
	ActionHandler<SubmitEStoreMessageAction, SubmitEStoreMessageResult> {

    @Autowired
    private EStoreDao eStoreDao;

    @Override
    public SubmitEStoreMessageResult execute(SubmitEStoreMessageAction action,
	    ExecutionContext context) throws DispatchException {

	String identifier = action.getIdentifier();

	EStoreMessage message = eStoreDao.getMessageByIdentifier(identifier);
	message.setSalePointPerMessage(action.getSalesChargePerMessage());
	message.setDescription(action.getBriefDescription());
	message.setLocation(action.getLocation());
	message.setLastUpdatedDate(new Date());
	message.setMessageDate(action.getMessageDate());

	Map<String, String> otherDetails = new HashMap<String, String>();
	if (action.getExistingSpeaker() != null) {
	    otherDetails.put("existingSpeakerIdentifier",
		    action.getExistingSpeaker());
	} else {
	    otherDetails.put("newSpeakerIdentifier",
		    RandomStringUtils.random(20, true, true));
	    otherDetails.put("newSpeakerTitle", action.getNewSpeaker()
		    .getTitle().name());
	    otherDetails.put("newSpeakerForenames", action.getNewSpeaker()
		    .getForenames());
	    otherDetails.put("newSpeakerSurname", action.getNewSpeaker()
		    .getSurname());
	    otherDetails.put("newSpeakerDesc", action.getSpeakerDesc());
	}

	if (action.getExistingCategory() != null) {
	    otherDetails.put("existingCategoryIdentifier",
		    action.getExistingCategory());
	} else {
	    otherDetails.put("newCategoryIdentifier",
		    RandomStringUtils.random(20, true, true));
	    otherDetails.put("newCategoryName", action.getNewCategory());
	}

	StringBuffer notificationsSpaceDelimited = new StringBuffer();
	for (EStoreNotificationType notificationType : action
		.getNotifications()) {
	    notificationsSpaceDelimited.append(notificationType.name());
	    notificationsSpaceDelimited.append(" ");
	}
	String notificationTypes = notificationsSpaceDelimited.toString()
		.trim();
	if (notificationTypes != null && !notificationTypes.equals("")) {
	    otherDetails.put("notificationTypes", notificationTypes);
	}

	List<String> workersWithFreeAccessToMessage = action
		.getWorkersWithFreeAccessToMessage();
	if (workersWithFreeAccessToMessage != null
		&& workersWithFreeAccessToMessage.size() > 0) {
	    String workersWithFreeAccessToMessageStr = reformatWorkersWithFreeAccessToMessage(action
		    .getWorkersWithFreeAccessToMessage());
	    otherDetails.put("workersWithFreeAccessToMessage",
		    workersWithFreeAccessToMessageStr);
	}

	eStoreDao.updateMessage(message, otherDetails);

	return new SubmitEStoreMessageResult(map(message));
    }

    private EStoreMessageDto map(EStoreMessage message) {
	EStoreMessageDto dto = new EStoreMessageDto();
	dto.setDescription(message.getDescription());
	dto.setTitle(message.getTitle());
	dto.setIdentifier(message.getIdentifier());
	return dto;
    }

    @Override
    public Class<SubmitEStoreMessageAction> getActionType() {
	return SubmitEStoreMessageAction.class;
    }

    @Override
    public void rollback(SubmitEStoreMessageAction action,
	    SubmitEStoreMessageResult result, ExecutionContext context)
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
