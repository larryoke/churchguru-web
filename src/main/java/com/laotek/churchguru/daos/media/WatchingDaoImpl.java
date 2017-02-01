package com.laotek.churchguru.daos.media;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.VideoMessage;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class WatchingDaoImpl extends BaseSessionFactory implements WatchingDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	VideoMessage eStoreMessage = new VideoMessage();
	eStoreMessage.setIdentifier(messageId);
	eStoreMessage.setTitle(title);
	eStoreMessage.setCreatedDate(new Date());
	eStoreMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public VideoMessage getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery("from VideoMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	VideoMessage message = (VideoMessage) query.uniqueResult();
	return message;
    }

    @Override
    public void updateMessage(VideoMessage eStoreMessage) {
	eStoreMessage = (VideoMessage) getCurrentSession().merge(eStoreMessage);

	getCurrentSession().update(eStoreMessage);
    }

    @Override
    public List<VideoMessage> getMessages() {
	@SuppressWarnings("unchecked")
	List<VideoMessage> list = getCurrentSession().createQuery("from VideoMessage e order by e.messageDate desc")
		.list();
	return list;
    }
}
