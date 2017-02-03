package com.laotek.churchguru.daos.media;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.YoutubeVideo;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class YoutubeVideoDaoImpl extends BaseSessionFactory implements YoutubeVideoDao {

    @Override
    public void createNewMessage(String messageId, String title) {
	YoutubeVideo eStoreMessage = new YoutubeVideo();
	eStoreMessage.setIdentifier(messageId);
	eStoreMessage.setTitle(title);
	eStoreMessage.setCreatedDate(new Date());
	eStoreMessage.setLastUpdatedDate(new Date());
	getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public YoutubeVideo getMessageByIdentifier(String identifier) {
	Query query = getCurrentSession().createQuery("from YoutubeVideo m where m.identifier = :identifier");
	query.setParameter("identifier", identifier);
	YoutubeVideo message = (YoutubeVideo) query.uniqueResult();
	return message;
    }

    @Override
    public void updateMessage(YoutubeVideo eStoreMessage) {
	eStoreMessage = (YoutubeVideo) getCurrentSession().merge(eStoreMessage);

	getCurrentSession().update(eStoreMessage);
    }

    @Override
    public List<YoutubeVideo> getMessages() {
	@SuppressWarnings("unchecked")
	List<YoutubeVideo> list = getCurrentSession().createQuery("from YoutubeVideo e order by e.messageDate desc")
		.list();
	return list;
    }
}
