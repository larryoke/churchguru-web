package com.laotek.churchguru.daos.mobile.instantmessage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.User;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NoticeAndEventDaoImpl extends BaseSessionFactory implements
	NoticeAndEventDao {

    @Override
    public NoticeAndEvent getNoticeAndEvent(long id) {
	return (NoticeAndEvent) getCurrentSession().get(NoticeAndEvent.class,
		id);
    }

    @Override
    public NoticeAndEvent getNoticeAndEvent(long id, String identifier) {
	NoticeAndEvent ne = (NoticeAndEvent) getCurrentSession().get(
		NoticeAndEvent.class, id);
	if (!ne.getIdentifier().equals(identifier)) {
	    return null;
	}
	return ne;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<NoticeAndEvent> getNoticeAndEvents(
	    BrowseMessagesType browseMessagesType, int index, int count) {
	Query query = getCurrentSession()
		.createQuery(
			"from NoticeAndEvent ne where ne.messageType = :messageType order by (case when eventDate is null then createdDate  else eventDate end) desc");
	query.setParameter("messageType", browseMessagesType);
	query.setFirstResult(index);
	query.setMaxResults(count);
	List<NoticeAndEvent> messages = query.list();
	return messages;
    }

    @Override
    public void postNoticeAndEventForPreview(int key, String identifier,
	    String subject, String message, Date eventDate,
	    EventTime eventTime, String clientSessionId) {
	NoticeAndEvent noticeAndEvent = findNoticeAndEvent(key, identifier);
	noticeAndEvent.setLastUpdatedDate(new Date());
	noticeAndEvent.setSubject(subject);
	noticeAndEvent.setMessageBody(message);
	if (eventDate != null && eventTime != null) {
	    eventDate = format(eventDate, eventTime);
	}
	noticeAndEvent.setEventDate(eventDate);
	noticeAndEvent.setEventTime(eventTime);
	getCurrentSession().merge(noticeAndEvent);
    }

    @Override
    public NoticeAndEvent createNoticeAndEvent(String subject,
	    String clientSessionId) {
	NoticeAndEvent noticeAndEvent = new NoticeAndEvent();
	noticeAndEvent.setCreatedDate(new Date());
	noticeAndEvent.setLastUpdatedDate(new Date());
	noticeAndEvent.setSubject(subject);
	// noticeAndEvent.setMessageBody("message here...");
	// Date eventDate = format(new Date(), EventTime.H00_00);
	// noticeAndEvent.setEventDate(eventDate);
	// noticeAndEvent.setEventTime(EventTime.H00_00);

	Query query = getCurrentSession().createQuery(
		"from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	User op = (User) query.uniqueResult();
	noticeAndEvent.setUser(op);
	noticeAndEvent.setMessageType(BrowseMessagesType.DRAFT);
	noticeAndEvent.setIdentifier(RandomStringUtils.random(10, true, true));
	return (NoticeAndEvent) getCurrentSession().merge(noticeAndEvent);
    }

    private Date format(Date date, EventTime eventTime) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR, eventTime.getHour());
	cal.set(Calendar.MINUTE, eventTime.getMinutes());
	return cal.getTime();
    }

    @Override
    public void delete(int id, String identifier) {
	NoticeAndEvent ne = (NoticeAndEvent) getCurrentSession().get(
		NoticeAndEvent.class, new Long(id));
	if (ne.getIdentifier().equals(identifier)) {
	    getCurrentSession().delete(ne);
	}
    }

    @Override
    public void completeNoticeAndEvent(int key, String identifier) {
	NoticeAndEvent noticeAndEvent = findNoticeAndEvent(key, identifier);
	noticeAndEvent.setLastUpdatedDate(new Date());
	noticeAndEvent.setMessageType(BrowseMessagesType.POSTED);
	getCurrentSession().merge(noticeAndEvent);
    }

    @Override
    public void uploadNoticeAndEventPicture(int key, String identifier,
	    String base64Data, String filename, String contentType) {
	NoticeAndEvent noticeAndEvent = findNoticeAndEvent(key, identifier);
	noticeAndEvent.setBase64Data(base64Data);
	noticeAndEvent.setFilename(filename);
	noticeAndEvent.setContentType(contentType);

	String message = noticeAndEvent.getMessageBody();
	if (message == null) {
	    message = "";
	}
	if (!message.contains("<img")) {
	    StringBuffer sb = generateImgMarkup(key, identifier);
	    noticeAndEvent.setMessageBody(sb.toString() + message);
	}
	getCurrentSession().merge(noticeAndEvent);
    }

    @Override
    public void removePicture(int id, String identifier) {
	NoticeAndEvent noticeAndEvent = findNoticeAndEvent(id, identifier);
	noticeAndEvent.setBase64Data(null);
	noticeAndEvent.setContentType(null);
	noticeAndEvent.setFilename(null);

	StringBuffer sb = generateImgMarkup(id, identifier);
	String message = noticeAndEvent.getMessageBody();
	noticeAndEvent.setMessageBody(message.replace(sb.toString(), ""));
	getCurrentSession().merge(noticeAndEvent);
    }

    private StringBuffer generateImgMarkup(int key, String identifier) {
	StringBuffer sb = new StringBuffer();
	sb.append("<br/>");
	sb.append("<img width=\"100%\" ");
	sb.append("src=\"/uploadedphotos/photos/noticeorevent");
	sb.append("/");
	sb.append(key);
	sb.append("/");
	sb.append(identifier);
	sb.append("\">");
	sb.append("<br/>");
	sb.append("<br/>");
	return sb;
    }

    private NoticeAndEvent findNoticeAndEvent(int key, String identifier) {
	Query query = getCurrentSession()
		.createQuery(
			"from NoticeAndEvent ne where id= :id and identifier = :identifier");
	query.setParameter("id", new Long(key));
	query.setParameter("identifier", identifier);
	NoticeAndEvent noticeAndEvent = (NoticeAndEvent) query.uniqueResult();
	return noticeAndEvent;
    }

    @Override
    public NoticeAndEvent duplicate(int id, String identifier,
	    String clientSessionId) {
	Query query = getCurrentSession()
		.createQuery(
			"from NoticeAndEvent ne where id= :id and identifier = :identifier");
	query.setParameter("id", new Long(id));
	query.setParameter("identifier", identifier);
	NoticeAndEvent original = (NoticeAndEvent) query.uniqueResult();

	NoticeAndEvent copy = new NoticeAndEvent();
	copy.setCreatedDate(new Date());
	copy.setLastUpdatedDate(new Date());
	copy.setSubject(original.getSubject());
	copy.setMessageBody(original.getMessageBody());
	Date eventDate = format(new Date(), EventTime.H00_00);
	copy.setEventDate(original.getEventDate());
	copy.setEventTime(original.getEventTime());
	copy.setBase64Data(original.getBase64Data());
	copy.setContentType(original.getContentType());
	copy.setFilename(original.getFilename());

	query = getCurrentSession().createQuery(
		"from User o where o.clientSessionId = :clientSessionId");
	query.setParameter("clientSessionId", clientSessionId);
	User op = (User) query.uniqueResult();
	copy.setUser(op);
	copy.setMessageType(BrowseMessagesType.DRAFT);
	copy.setIdentifier(RandomStringUtils.random(10, true, true));
	return (NoticeAndEvent) getCurrentSession().merge(copy);
    }

}
