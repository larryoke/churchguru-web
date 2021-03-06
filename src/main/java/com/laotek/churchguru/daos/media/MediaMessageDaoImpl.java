package com.laotek.churchguru.daos.media;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.MediaMessage;
import com.laotek.churchguru.model.MediaMessageCategory;
import com.laotek.churchguru.model.MediaMessageSpeaker;
import com.laotek.churchguru.model.shared.enums.MediaMessageStatus;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MediaMessageDaoImpl extends BaseSessionFactory implements MediaMessageDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	MediaMessage mediaMessage = new MediaMessage();
	mediaMessage.setTitle(title);
	// mediaMessage.setDescription("desc...");
	mediaMessage.setIdentifier(messageId);
	mediaMessage.setCreatedDate(new Date());
	mediaMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(mediaMessage);
    }

    @Override
    public MediaMessage getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery("from MediaMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	MediaMessage message = (MediaMessage) query.uniqueResult();
	return message;
    }

    @Override
    public void updateMessage(MediaMessage eStoreMessage, Map<String, String> otherDetails) {
	eStoreMessage = (MediaMessage) getCurrentSession().merge(eStoreMessage);

	processAddSpeaker(eStoreMessage, otherDetails);

	processAddCategory(eStoreMessage, otherDetails);

	getCurrentSession().update(eStoreMessage);
    }

    public void processAddCategory(MediaMessage eStoreMessage, Map<String, String> otherDetails) {
	String categoryIdentifier = otherDetails.get("existingCategoryIdentifier");

	// setupe message with existing category
	if (categoryIdentifier != null) {
	    Query query = getCurrentSession()
		    .createQuery("from MediaMessageCategory c Where c.identifier = :identifier");
	    query.setParameter("identifier", categoryIdentifier);
	    MediaMessageCategory eStoreCategory = (MediaMessageCategory) query.uniqueResult();
	    eStoreMessage.seteStoreCategory(eStoreCategory);

	} else {

	    // check just in case if the category name is existing
	    String name = otherDetails.get("newCategoryName");
	    Query query = getCurrentSession()
		    .createQuery("from MediaMessageCategory c Where c.categoryName = :categoryName");
	    query.setParameter("categoryName", name);
	    MediaMessageCategory eStoreCategory = (MediaMessageCategory) query.uniqueResult();

	    // create a new cat if not existing
	    if (eStoreCategory == null) {
		String identifier = otherDetails.get("newCategoryIdentifier");
		eStoreCategory = new MediaMessageCategory();
		eStoreCategory.setCategoryName(name);
		eStoreCategory.setIdentifier(identifier);
		getCurrentSession().persist(eStoreCategory);
	    }
	    eStoreMessage.seteStoreCategory(eStoreCategory);
	}
    }

    public void processAddSpeaker(MediaMessage eStoreMessage, Map<String, String> otherDetails) {
	String speakerIdentifier = otherDetails.get("existingSpeakerIdentifier");
	if (speakerIdentifier != null) {
	    Query query = getCurrentSession()
		    .createQuery("from MediaMessageSpeaker s Where s.identifier = :identifier");
	    query.setParameter("identifier", speakerIdentifier);
	    MediaMessageSpeaker eStoreSpeaker = (MediaMessageSpeaker) query.uniqueResult();
	    eStoreMessage.setEStoreSpeaker(eStoreSpeaker);

	} else {
	    // check just in case if the speaker is existing
	    Title title = Title.valueOf(otherDetails.get("newSpeakerTitle"));
	    String forenames = otherDetails.get("newSpeakerForenames");
	    String surname = otherDetails.get("newSpeakerSurname");
	    Query query = getCurrentSession().createQuery(
		    "from MediaMessageSpeaker s Where s.title = :title and s.forenames = :forenames and s.surname = :surname");
	    query.setParameter("title", title);
	    query.setParameter("forenames", forenames);
	    query.setParameter("surname", surname);
	    MediaMessageSpeaker eStoreSpeaker = (MediaMessageSpeaker) query.uniqueResult();

	    // create a new speaker if not existing
	    if (eStoreSpeaker == null) {

		String desc = otherDetails.get("newSpeakerDesc");
		String identifier = otherDetails.get("newSpeakerIdentifier");

		eStoreSpeaker = new MediaMessageSpeaker();
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
    public List<MediaMessage> getMessages() {
	@SuppressWarnings("unchecked")
	List<MediaMessage> list = getCurrentSession().createQuery("from MediaMessage e order by e.messageDate desc")
		.list();
	return list;
    }

    @Override
    public List<MediaMessageSpeaker> getSpeakers() {
	@SuppressWarnings("unchecked")
	List<MediaMessageSpeaker> list = getCurrentSession().createQuery("from MediaMessageSpeaker ").list();
	return list;
    }

    @Override
    public List<MediaMessageCategory> getPublishedCategories() {
	Query query = getCurrentSession().createQuery(
		"select distinct cat from MediaMessageCategory cat join cat.messages messages where messages.messageStatus = :status");
	query.setParameter("status", MediaMessageStatus.PUBLISHED);
	@SuppressWarnings("unchecked")
	List<MediaMessageCategory> list = query.list();
	for (MediaMessageCategory cat : list) {
	    cat.getMessages().size();
	}
	return list;
    }

    @Override
    public List<MediaMessageCategory> getCategories() {
	Query query = getCurrentSession().createQuery("from MediaMessageCategory cat ");
	@SuppressWarnings("unchecked")
	List<MediaMessageCategory> list = query.list();
	for (MediaMessageCategory cat : list) {
	    cat.getMessages().size();
	}
	return list;
    }

    @Override
    public MediaMessageCategory getCategory(String identifier) {
	Query query = getCurrentSession().createQuery("from MediaMessageCategory s Where s.identifier = :identifier");
	query.setParameter("identifier", identifier);
	MediaMessageCategory category = (MediaMessageCategory) query.uniqueResult();
	category.getMessages().size();
	return category;
    }

    @Override
    public void updateSpeakerPictureURL(String identifier, String pictureURL) {
	Query query = getCurrentSession().createQuery("from MediaMessageSpeaker s Where s.identifier = :identifier");
	query.setParameter("identifier", identifier);
	MediaMessageSpeaker eStoreSpeaker = (MediaMessageSpeaker) query.uniqueResult();
	eStoreSpeaker.setPictureUrl(pictureURL);
    }

    @Override
    public void updateDescPictureURL(String identifier, String pictureURL) {
	MediaMessage message = getMessageByIdentifier(identifier);
	message.setDescPictureUrl(pictureURL);
    }

    @Override
    public void updateMediaMessageURL(String identifier, String mediaURL) {
	MediaMessage message = getMessageByIdentifier(identifier);
	message.setMessageStatus(MediaMessageStatus.LOADED);
	message.setMediaMessageUrl(mediaURL);
    }
}
