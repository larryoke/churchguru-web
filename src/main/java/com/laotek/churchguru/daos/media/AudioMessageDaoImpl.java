package com.laotek.churchguru.daos.media;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.AudioMessage;
import com.laotek.churchguru.model.AudioMessageCategory;
import com.laotek.churchguru.model.AudioMessagePicture;
import com.laotek.churchguru.model.AudioMessageSpeaker;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AudioMessageDaoImpl extends BaseSessionFactory implements AudioMessageDao {

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
	Query query = getCurrentSession().createQuery("from AudioMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	AudioMessage message = (AudioMessage) query.uniqueResult();
	return message;
    }

    @Override
    public void updateMessage(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	eStoreMessage = (AudioMessage) getCurrentSession().merge(eStoreMessage);

	processAddSpeaker(eStoreMessage, otherDetails);

	processAddCategory(eStoreMessage, otherDetails);

	getCurrentSession().update(eStoreMessage);
    }

    public void processAddCategory(AudioMessage eStoreMessage, Map<String, String> otherDetails) {
	String categoryIdentifier = otherDetails.get("existingCategoryIdentifier");

	// setupe message with existing category
	if (categoryIdentifier != null) {
	    Query query = getCurrentSession()
		    .createQuery("from AudioMessageCategory c Where c.identifier = :identifier");
	    query.setParameter("identifier", categoryIdentifier);
	    AudioMessageCategory eStoreCategory = (AudioMessageCategory) query.uniqueResult();
	    eStoreMessage.seteStoreCategory(eStoreCategory);

	} else {

	    // check just in case if the category name is existing
	    String name = otherDetails.get("newCategoryName");
	    Query query = getCurrentSession()
		    .createQuery("from AudioMessageCategory c Where c.categoryName = :categoryName");
	    query.setParameter("categoryName", name);
	    AudioMessageCategory eStoreCategory = (AudioMessageCategory) query.uniqueResult();

	    // create a new cat if not existing
	    if (eStoreCategory == null) {
		String identifier = otherDetails.get("newCategoryIdentifier");
		eStoreCategory = new AudioMessageCategory();
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
	    Query query = getCurrentSession()
		    .createQuery("from AudioMessageSpeaker s Where s.identifier = :identifier");
	    query.setParameter("identifier", speakerIdentifier);
	    AudioMessageSpeaker eStoreSpeaker = (AudioMessageSpeaker) query.uniqueResult();
	    eStoreMessage.setEStoreSpeaker(eStoreSpeaker);

	} else {
	    // check just in case if the speaker is existing
	    Title title = Title.valueOf(otherDetails.get("newSpeakerTitle"));
	    String forenames = otherDetails.get("newSpeakerForenames");
	    String surname = otherDetails.get("newSpeakerSurname");
	    Query query = getCurrentSession().createQuery(
		    "from AudioMessageSpeaker s Where s.title = :title and s.forenames = :forenames and s.surname = :surname");
	    query.setParameter("title", title);
	    query.setParameter("forenames", forenames);
	    query.setParameter("surname", surname);
	    AudioMessageSpeaker eStoreSpeaker = (AudioMessageSpeaker) query.uniqueResult();

	    // create a new speaker if not existing
	    if (eStoreSpeaker == null) {

		String desc = otherDetails.get("newSpeakerDesc");
		String identifier = otherDetails.get("newSpeakerIdentifier");

		eStoreSpeaker = new AudioMessageSpeaker();
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
	List<AudioMessage> list = getCurrentSession().createQuery("from AudioMessage e order by e.messageDate desc")
		.list();
	return list;
    }

    @Override
    public List<AudioMessageSpeaker> getSpeakers() {
	@SuppressWarnings("unchecked")
	List<AudioMessageSpeaker> list = getCurrentSession().createQuery("from AudioMessageSpeaker ").list();
	return list;
    }

    @Override
    public List<AudioMessageCategory> getCategories() {
	@SuppressWarnings("unchecked")
	List<AudioMessageCategory> list = getCurrentSession().createQuery("from AudioMessageCategory ").list();
	return list;
    }

    @Override
    public List<AudioMessagePicture> getEStoreMessagePicture() {
	@SuppressWarnings("unchecked")
	List<AudioMessagePicture> list = getCurrentSession().createQuery("from AudioMessagePicture ").list();
	return list;
    }
}
