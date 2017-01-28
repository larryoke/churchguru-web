package com.laotek.churchguru.daos.estore;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.EStoreCategory;
import com.laotek.churchguru.model.EStoreMessage;
import com.laotek.churchguru.model.EStoreMessageNotification;
import com.laotek.churchguru.model.EStoreMessagePicture;
import com.laotek.churchguru.model.EStoreNotification;
import com.laotek.churchguru.model.EStoreSpeaker;
import com.laotek.churchguru.model.shared.enums.EStoreNotificationType;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class EStoreDaoImpl extends BaseSessionFactory implements EStoreDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	EStoreMessage eStoreMessage = new EStoreMessage();
	eStoreMessage.setTitle(title);
	eStoreMessage.setDescription("desc...");
	eStoreMessage.setIdentifier(messageId);
	eStoreMessage.setCreatedDate(new Date());
	eStoreMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public EStoreMessage getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery(
		"from EStoreMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	EStoreMessage message = (EStoreMessage) query.uniqueResult();
	Hibernate.initialize(message.getEStoreMessageNotifications());
	return message;
    }

    @Override
    public void updateMessage(EStoreMessage eStoreMessage,
	    Map<String, String> otherDetails) {
	eStoreMessage = (EStoreMessage) getCurrentSession()
		.merge(eStoreMessage);

	processAddSpeaker(eStoreMessage, otherDetails);

	processAddCategory(eStoreMessage, otherDetails);

	processAddNotifications(eStoreMessage, otherDetails);

	getCurrentSession().update(eStoreMessage);
    }

    public void processAddNotifications(EStoreMessage eStoreMessage,
	    Map<String, String> otherDetails) {
	String notificationTypesSpaceDelimited = otherDetails
		.get("notificationTypes");

	// user checked 1 or more notifications
	String notificationTypeArr[] = new String[0];
	if (!StringUtils.isEmpty(notificationTypesSpaceDelimited)) {
	    notificationTypeArr = notificationTypesSpaceDelimited.split("\\s+");
	}

	// for all notifications possible
	for (EStoreNotificationType type : EStoreNotificationType.values()) {

	    boolean selectedByUser = false;

	    // for each notification checked
	    for (String notificationType : notificationTypeArr) {

		if (type.equals(EStoreNotificationType
			.valueOf(notificationType))) {

		    selectedByUser = true;

		    // If this message does not have this notification
		    // selected by user
		    if (!isMessageNotificationExists(eStoreMessage, type)) {

			EStoreNotification notification = getEStoreNotification(EStoreNotificationType
				.valueOf(notificationType));

			// Create the message notification
			getCurrentSession().persist(
				new EStoreMessageNotification(eStoreMessage,
					notification));
		    }

		}
	    }

	    if (!selectedByUser) {
		// if not selected by user but does exist
		if (isMessageNotificationExists(eStoreMessage, type)) {

		    EStoreNotification notification = getEStoreNotification(type);

		    // Delete the message notification
		    EStoreMessageNotification mesgNotification = getEStoreMessageNotification(
			    eStoreMessage, notification);
		    getCurrentSession().delete(mesgNotification);
		}
	    }

	}
    }

    public void processAddCategory(EStoreMessage eStoreMessage,
	    Map<String, String> otherDetails) {
	String categoryIdentifier = otherDetails
		.get("existingCategoryIdentifier");

	// setupe message with existing category
	if (categoryIdentifier != null) {
	    Query query = getCurrentSession().createQuery(
		    "from EStoreCategory c Where c.identifier = :identifier");
	    query.setParameter("identifier", categoryIdentifier);
	    EStoreCategory eStoreCategory = (EStoreCategory) query
		    .uniqueResult();
	    eStoreMessage.seteStoreCategory(eStoreCategory);

	} else {

	    // check just in case if the category name is existing
	    String name = otherDetails.get("newCategoryName");
	    Query query = getCurrentSession()
		    .createQuery(
			    "from EStoreCategory c Where c.categoryName = :categoryName");
	    query.setParameter("categoryName", name);
	    EStoreCategory eStoreCategory = (EStoreCategory) query
		    .uniqueResult();

	    // create a new cat if not existing
	    if (eStoreCategory == null) {
		String identifier = otherDetails.get("newCategoryIdentifier");
		eStoreCategory = new EStoreCategory();
		eStoreCategory.setCategoryName(name);
		eStoreCategory.setIdentifier(identifier);
		getCurrentSession().persist(eStoreCategory);
	    }
	    eStoreMessage.seteStoreCategory(eStoreCategory);
	}
    }

    public void processAddSpeaker(EStoreMessage eStoreMessage,
	    Map<String, String> otherDetails) {
	String speakerIdentifier = otherDetails
		.get("existingSpeakerIdentifier");
	if (speakerIdentifier != null) {
	    Query query = getCurrentSession().createQuery(
		    "from EStoreSpeaker s Where s.identifier = :identifier");
	    query.setParameter("identifier", speakerIdentifier);
	    EStoreSpeaker eStoreSpeaker = (EStoreSpeaker) query.uniqueResult();
	    eStoreMessage.setEStoreSpeaker(eStoreSpeaker);

	} else {
	    // check just in case if the speaker is existing
	    Title title = Title.valueOf(otherDetails.get("newSpeakerTitle"));
	    String forenames = otherDetails.get("newSpeakerForenames");
	    String surname = otherDetails.get("newSpeakerSurname");
	    Query query = getCurrentSession()
		    .createQuery(
			    "from EStoreSpeaker s Where s.title = :title and s.forenames = :forenames and s.surname = :surname");
	    query.setParameter("title", title);
	    query.setParameter("forenames", forenames);
	    query.setParameter("surname", surname);
	    EStoreSpeaker eStoreSpeaker = (EStoreSpeaker) query.uniqueResult();

	    // create a new speaker if not existing
	    if (eStoreSpeaker == null) {

		String desc = otherDetails.get("newSpeakerDesc");
		String identifier = otherDetails.get("newSpeakerIdentifier");

		eStoreSpeaker = new EStoreSpeaker();
		eStoreSpeaker.setIdentifier(identifier);
		eStoreSpeaker.setForenames(forenames);
		eStoreSpeaker.setSurname(surname);
		eStoreSpeaker.setTitle(title);
		eStoreSpeaker.setDescription(desc);
		getCurrentSession().persist(eStoreSpeaker);
	    }

	    eStoreMessage.setEStoreSpeaker(eStoreSpeaker);
	}
    }

    @Override
    public List<EStoreMessage> getMessages() {
	@SuppressWarnings("unchecked")
	List<EStoreMessage> list = getCurrentSession().createQuery(
		"from EStoreMessage e order by e.messageDate desc").list();
	for (EStoreMessage msg : list) {
	    msg.getEStoreMessageNotifications().size();
	}
	return list;
    }

    @Override
    public List<EStoreSpeaker> getSpeakers() {
	@SuppressWarnings("unchecked")
	List<EStoreSpeaker> list = getCurrentSession().createQuery(
		"from EStoreSpeaker ").list();
	return list;
    }

    @Override
    public List<EStoreCategory> getCategories() {
	@SuppressWarnings("unchecked")
	List<EStoreCategory> list = getCurrentSession().createQuery(
		"from EStoreCategory ").list();
	return list;
    }

    @Override
    public List<EStoreMessagePicture> getEStoreMessagePicture() {
	@SuppressWarnings("unchecked")
	List<EStoreMessagePicture> list = getCurrentSession().createQuery(
		"from EStoreMessagePicture ").list();
	return list;
    }

    @Override
    public void loadNotifications() {
	for (EStoreNotificationType notificationType : EStoreNotificationType
		.values()) {
	    EStoreNotification notification = getEStoreNotification(notificationType);
	    if (notification == null) {
		createEStoreNotification(notificationType);
	    }
	}
    }

    private void createEStoreNotification(
	    EStoreNotificationType notificationType) {
	EStoreNotification messageNotification = new EStoreNotification();
	messageNotification.setEStoreNotificationType(notificationType);
	messageNotification.setCreatedDate(new Date());
	messageNotification.setLastUpdatedDate(new Date());
	messageNotification.setIdentifier(RandomStringUtils.random(30, true,
		true));
	getCurrentSession().persist(messageNotification);
    }

    private EStoreMessageNotification getEStoreMessageNotification(
	    EStoreMessage message, EStoreNotification notification) {
	EStoreMessageNotification.Id id = new EStoreMessageNotification.Id(
		message.getId(), notification.getId());

	Query query = getCurrentSession().createQuery(
		"from EStoreMessageNotification e where e.id = :id");
	query.setParameter("id", id);
	return (EStoreMessageNotification) query.uniqueResult();
    }

    private EStoreNotification getEStoreNotification(
	    EStoreNotificationType notificationType) {
	Query query = getCurrentSession()
		.createQuery(
			"from EStoreNotification e where e.eStoreNotificationType = :notificationType");
	query.setParameter("notificationType", notificationType);
	EStoreNotification notification = (EStoreNotification) query
		.uniqueResult();

	return notification;
    }

    private boolean isMessageNotificationExists(EStoreMessage message,
	    EStoreNotificationType notificationType) {
	Query query = getCurrentSession()
		.createQuery(
			"from EStoreNotification e where e.eStoreNotificationType = :notificationType");
	query.setParameter("notificationType", notificationType);
	EStoreNotification notification = (EStoreNotification) query
		.uniqueResult();

	EStoreMessageNotification.Id id = new EStoreMessageNotification.Id(
		message.getId(), notification.getId());

	query = getCurrentSession().createQuery(
		"from EStoreMessageNotification e where e.id = :id");
	query.setParameter("id", id);
	EStoreMessageNotification messageNotification = (EStoreMessageNotification) query
		.uniqueResult();
	if (messageNotification != null) {
	    return true;
	}
	return false;
    }

    @Override
    public Map<String, Boolean> getWorkersSelectedForFreeMessages() {
	Map<String, Boolean> temp = new HashMap<String, Boolean>();

	// Query query = getCurrentSession().createQuery(
	// "from Member m where m.memberDepartments IS NOT EMPTY");
	// @SuppressWarnings("unchecked")
	// List<Member> members = query.list();
	// for (Member member : members) {
	// StringBuffer sb = new StringBuffer();
	// sb.append(member.getFullname());
	// sb.append("&lt;");
	// sb.append(member.getEmailAddress());
	// sb.append("&gt;");
	// temp.put(sb.toString(), true);
	// }
	return temp;
    }
}
