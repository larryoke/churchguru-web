package com.laotek.churchguru.daos.media;

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
import com.laotek.churchguru.model.AudioCategory;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessageNotification;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioNotification;
import com.laotek.churchguru.model.AudioSpeaker;
import com.laotek.churchguru.model.shared.enums.ListeningNotificationType;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ListeningDaoImpl extends BaseSessionFactory implements ListeningDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	AudioMessage eStoreMessage = new AudioMessage();
	eStoreMessage.setTitle(title);
	eStoreMessage.setDescription("desc...");
	eStoreMessage.setIdentifier(messageId);
	eStoreMessage.setCreatedDate(new Date());
	eStoreMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public AudioMessage getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery("from ListeningMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	AudioMessage message = (AudioMessage) query.uniqueResult();
	Hibernate.initialize(message.getEStoreMessageNotifications());
	return message;
    }

    @Override
    public void updateMessage(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	eStoreMessage = (AudioMessage) getCurrentSession().merge(eStoreMessage);

	processAddSpeaker(eStoreMessage, otherDetails);

	processAddCategory(eStoreMessage, otherDetails);

	processAddNotifications(eStoreMessage, otherDetails);

	getCurrentSession().update(eStoreMessage);
    }

    public void processAddNotifications(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	String notificationTypesSpaceDelimited = otherDetails.get("notificationTypes");

	// user checked 1 or more notifications
	String notificationTypeArr[] = new String[0];
	if (!StringUtils.isEmpty(notificationTypesSpaceDelimited)) {
	    notificationTypeArr = notificationTypesSpaceDelimited.split("\\s+");
	}

	// for all notifications possible
	for (ListeningNotificationType type : ListeningNotificationType.values()) {

	    boolean selectedByUser = false;

	    // for each notification checked
	    for (String notificationType : notificationTypeArr) {

		if (type.equals(ListeningNotificationType.valueOf(notificationType))) {

		    selectedByUser = true;

		    // If this message does not have this notification
		    // selected by user
		    if (!isMessageNotificationExists(eStoreMessage, type)) {

			AudioNotification notification = getEStoreNotification(
				ListeningNotificationType.valueOf(notificationType));

			// Create the message notification
			getCurrentSession().persist(new AudioMessageNotification(eStoreMessage, notification));
		    }

		}
	    }

	    if (!selectedByUser) {
		// if not selected by user but does exist
		if (isMessageNotificationExists(eStoreMessage, type)) {

		    AudioNotification notification = getEStoreNotification(type);

		    // Delete the message notification
		    AudioMessageNotification mesgNotification = getEStoreMessageNotification(eStoreMessage,
			    notification);
		    getCurrentSession().delete(mesgNotification);
		}
	    }

	}
    }

    public void processAddCategory(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	String categoryIdentifier = otherDetails.get("existingCategoryIdentifier");

	// setupe message with existing category
	if (categoryIdentifier != null) {
	    Query query = getCurrentSession().createQuery("from ListeningCategory c Where c.identifier = :identifier");
	    query.setParameter("identifier", categoryIdentifier);
	    AudioCategory eStoreCategory = (AudioCategory) query.uniqueResult();
	    eStoreMessage.seteStoreCategory(eStoreCategory);

	} else {

	    // check just in case if the category name is existing
	    String name = otherDetails.get("newCategoryName");
	    Query query = getCurrentSession()
		    .createQuery("from ListeningCategory c Where c.categoryName = :categoryName");
	    query.setParameter("categoryName", name);
	    AudioCategory eStoreCategory = (AudioCategory) query.uniqueResult();

	    // create a new cat if not existing
	    if (eStoreCategory == null) {
		String identifier = otherDetails.get("newCategoryIdentifier");
		eStoreCategory = new AudioCategory();
		eStoreCategory.setCategoryName(name);
		eStoreCategory.setIdentifier(identifier);
		getCurrentSession().persist(eStoreCategory);
	    }
	    eStoreMessage.seteStoreCategory(eStoreCategory);
	}
    }

    public void processAddSpeaker(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	String speakerIdentifier = otherDetails.get("existingSpeakerIdentifier");
	if (speakerIdentifier != null) {
	    Query query = getCurrentSession().createQuery("from ListeningSpeaker s Where s.identifier = :identifier");
	    query.setParameter("identifier", speakerIdentifier);
	    AudioSpeaker eStoreSpeaker = (AudioSpeaker) query.uniqueResult();
	    eStoreMessage.setEStoreSpeaker(eStoreSpeaker);

	} else {
	    // check just in case if the speaker is existing
	    Title title = Title.valueOf(otherDetails.get("newSpeakerTitle"));
	    String forenames = otherDetails.get("newSpeakerForenames");
	    String surname = otherDetails.get("newSpeakerSurname");
	    Query query = getCurrentSession().createQuery(
		    "from ListeningSpeaker s Where s.title = :title and s.forenames = :forenames and s.surname = :surname");
	    query.setParameter("title", title);
	    query.setParameter("forenames", forenames);
	    query.setParameter("surname", surname);
	    AudioSpeaker eStoreSpeaker = (AudioSpeaker) query.uniqueResult();

	    // create a new speaker if not existing
	    if (eStoreSpeaker == null) {

		String desc = otherDetails.get("newSpeakerDesc");
		String identifier = otherDetails.get("newSpeakerIdentifier");

		eStoreSpeaker = new AudioSpeaker();
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
    public List<AudioMessage> getMessages() {
	@SuppressWarnings("unchecked")
	List<AudioMessage> list = getCurrentSession()
		.createQuery("from ListeningMessage e order by e.messageDate desc").list();
	for (AudioMessage msg : list) {
	    msg.getEStoreMessageNotifications().size();
	}
	return list;
    }

    @Override
    public List<AudioSpeaker> getSpeakers() {
	@SuppressWarnings("unchecked")
	List<AudioSpeaker> list = getCurrentSession().createQuery("from ListeningSpeaker ").list();
	return list;
    }

    @Override
    public List<AudioCategory> getCategories() {
	@SuppressWarnings("unchecked")
	List<AudioCategory> list = getCurrentSession().createQuery("from ListeningCategory ").list();
	return list;
    }

    @Override
    public List<AudioMessagePicture> getEStoreMessagePicture() {
	@SuppressWarnings("unchecked")
	List<AudioMessagePicture> list = getCurrentSession().createQuery("from ListeningMessagePicture ").list();
	return list;
    }

    @Override
    public void loadNotifications() {
	for (ListeningNotificationType notificationType : ListeningNotificationType.values()) {
	    AudioNotification notification = getEStoreNotification(notificationType);
	    if (notification == null) {
		createEStoreNotification(notificationType);
	    }
	}
    }

    private void createEStoreNotification(ListeningNotificationType notificationType) {
	AudioNotification messageNotification = new AudioNotification();
	messageNotification.setEStoreNotificationType(notificationType);
	messageNotification.setCreatedDate(new Date());
	messageNotification.setLastUpdatedDate(new Date());
	messageNotification.setIdentifier(RandomStringUtils.random(30, true, true));
	getCurrentSession().persist(messageNotification);
    }

    private AudioMessageNotification getEStoreMessageNotification(AudioMessage message,
	    AudioNotification notification) {
	AudioMessageNotification.Id id = new AudioMessageNotification.Id(message.getId(), notification.getId());

	Query query = getCurrentSession().createQuery("from ListeningMessageNotification e where e.id = :id");
	query.setParameter("id", id);
	return (AudioMessageNotification) query.uniqueResult();
    }

    private AudioNotification getEStoreNotification(ListeningNotificationType notificationType) {
	Query query = getCurrentSession()
		.createQuery("from ListeningNotification e where e.eStoreNotificationType = :notificationType");
	query.setParameter("notificationType", notificationType);
	AudioNotification notification = (AudioNotification) query.uniqueResult();

	return notification;
    }

    private boolean isMessageNotificationExists(AudioMessage message, ListeningNotificationType notificationType) {
	Query query = getCurrentSession()
		.createQuery("from ListeningNotification e where e.eStoreNotificationType = :notificationType");
	query.setParameter("notificationType", notificationType);
	AudioNotification notification = (AudioNotification) query.uniqueResult();

	AudioMessageNotification.Id id = new AudioMessageNotification.Id(message.getId(), notification.getId());

	query = getCurrentSession().createQuery("from ListeningMessageNotification e where e.id = :id");
	query.setParameter("id", id);
	AudioMessageNotification messageNotification = (AudioMessageNotification) query.uniqueResult();
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
