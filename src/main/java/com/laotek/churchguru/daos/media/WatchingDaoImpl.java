package com.laotek.churchguru.daos.media;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.WatchingMessage;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class WatchingDaoImpl extends BaseSessionFactory implements WatchingDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	WatchingMessage eStoreMessage = new WatchingMessage();
	eStoreMessage.setTitle(title);
	eStoreMessage.setDescription("desc...");
	eStoreMessage.setIdentifier(messageId);
	eStoreMessage.setCreatedDate(new Date());
	eStoreMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public WatchingMessage getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery("from WatchingMessage m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	WatchingMessage message = (WatchingMessage) query.uniqueResult();
	return message;
    }

    @Override
    public void updateMessage(WatchingMessage eStoreMessage, Map<String, String> otherDetails) {
	eStoreMessage = (WatchingMessage) getCurrentSession().merge(eStoreMessage);

	getCurrentSession().update(eStoreMessage);
    }

    @Override
    public List<WatchingMessage> getMessages() {
	@SuppressWarnings("unchecked")
	List<WatchingMessage> list = getCurrentSession()
		.createQuery("from WatchingMessage e order by e.messageDate desc").list();
	return list;
    }
}
