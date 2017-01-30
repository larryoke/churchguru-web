package com.laotek.churchguru.daos.listening;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.*;
import com.laotek.churchguru.model.shared.enums.ListeningNotificationType;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.laotek.churchguru.daos.BaseSessionFactory;
import com.laotek.churchguru.model.ListeningCategory;
import com.laotek.churchguru.model.shared.enums.Title;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ListeningDaoImpl extends BaseSessionFactory implements ListeningDao {

    @Override
    public void createNewMessage(String messageId, String title) {
        ListeningMessage eStoreMessage = new ListeningMessage();
        eStoreMessage.setTitle(title);
        eStoreMessage.setDescription("desc...");
        eStoreMessage.setIdentifier(messageId);
        eStoreMessage.setCreatedDate(new Date());
        eStoreMessage.setLastUpdatedDate(new Date());
        getCurrentSession().persist(eStoreMessage);
    }

    @Override
    public ListeningMessage getMessageByIdentifier(String identifier) {
        Query query = getCurrentSession().createQuery(
                "from ListeningMessage m where m.identifier = :identifier");
        query.setParameter("identifier", identifier);
        ListeningMessage message = (ListeningMessage) query.uniqueResult();
        Hibernate.initialize(message.getEStoreMessageNotifications());
        return message;
    }

    @Override
    public void updateMessage(ListeningMessage eStoreMessage,
                              Map<String, String> otherDetails) {
        eStoreMessage = (ListeningMessage) getCurrentSession()
                .merge(eStoreMessage);

        processAddSpeaker(eStoreMessage, otherDetails);

        processAddCategory(eStoreMessage, otherDetails);

        processAddNotifications(eStoreMessage, otherDetails);

        getCurrentSession().update(eStoreMessage);
    }

    public void processAddNotifications(ListeningMessage eStoreMessage,
                                        Map<String, String> otherDetails) {
        String notificationTypesSpaceDelimited = otherDetails
                .get("notificationTypes");

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

                if (type.equals(ListeningNotificationType
                        .valueOf(notificationType))) {

                    selectedByUser = true;

                    // If this message does not have this notification
                    // selected by user
                    if (!isMessageNotificationExists(eStoreMessage, type)) {

                        ListeningNotification notification = getEStoreNotification(ListeningNotificationType
                                .valueOf(notificationType));

                        // Create the message notification
                        getCurrentSession().persist(
                                new ListeningMessageNotification(eStoreMessage,
                                        notification));
                    }

                }
            }

            if (!selectedByUser) {
                // if not selected by user but does exist
                if (isMessageNotificationExists(eStoreMessage, type)) {

                    ListeningNotification notification = getEStoreNotification(type);

                    // Delete the message notification
                    ListeningMessageNotification mesgNotification = getEStoreMessageNotification(
                            eStoreMessage, notification);
                    getCurrentSession().delete(mesgNotification);
                }
            }

        }
    }

    public void processAddCategory(ListeningMessage eStoreMessage,
                                   Map<String, String> otherDetails) {
        String categoryIdentifier = otherDetails
                .get("existingCategoryIdentifier");

        // setupe message with existing category
        if (categoryIdentifier != null) {
            Query query = getCurrentSession().createQuery(
                    "from ListeningCategory c Where c.identifier = :identifier");
            query.setParameter("identifier", categoryIdentifier);
            ListeningCategory eStoreCategory = (ListeningCategory) query
                    .uniqueResult();
            eStoreMessage.seteStoreCategory(eStoreCategory);

        } else {

            // check just in case if the category name is existing
            String name = otherDetails.get("newCategoryName");
            Query query = getCurrentSession()
                    .createQuery(
                            "from ListeningCategory c Where c.categoryName = :categoryName");
            query.setParameter("categoryName", name);
            ListeningCategory eStoreCategory = (ListeningCategory) query
                    .uniqueResult();

            // create a new cat if not existing
            if (eStoreCategory == null) {
                String identifier = otherDetails.get("newCategoryIdentifier");
                eStoreCategory = new ListeningCategory();
                eStoreCategory.setCategoryName(name);
                eStoreCategory.setIdentifier(identifier);
                getCurrentSession().persist(eStoreCategory);
            }
            eStoreMessage.seteStoreCategory(eStoreCategory);
        }
    }

    public void processAddSpeaker(ListeningMessage eStoreMessage,
                                  Map<String, String> otherDetails) {
        String speakerIdentifier = otherDetails
                .get("existingSpeakerIdentifier");
        if (speakerIdentifier != null) {
            Query query = getCurrentSession().createQuery(
                    "from ListeningSpeaker s Where s.identifier = :identifier");
            query.setParameter("identifier", speakerIdentifier);
            ListeningSpeaker eStoreSpeaker = (ListeningSpeaker) query.uniqueResult();
            eStoreMessage.setEStoreSpeaker(eStoreSpeaker);

        } else {
            // check just in case if the speaker is existing
            Title title = Title.valueOf(otherDetails.get("newSpeakerTitle"));
            String forenames = otherDetails.get("newSpeakerForenames");
            String surname = otherDetails.get("newSpeakerSurname");
            Query query = getCurrentSession()
                    .createQuery(
                            "from ListeningSpeaker s Where s.title = :title and s.forenames = :forenames and s.surname = :surname");
            query.setParameter("title", title);
            query.setParameter("forenames", forenames);
            query.setParameter("surname", surname);
            ListeningSpeaker eStoreSpeaker = (ListeningSpeaker) query.uniqueResult();

            // create a new speaker if not existing
            if (eStoreSpeaker == null) {

                String desc = otherDetails.get("newSpeakerDesc");
                String identifier = otherDetails.get("newSpeakerIdentifier");

                eStoreSpeaker = new ListeningSpeaker();
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
    public List<ListeningMessage> getMessages() {
        @SuppressWarnings("unchecked")
        List<ListeningMessage> list = getCurrentSession().createQuery(
                "from ListeningMessage e order by e.messageDate desc").list();
        for (ListeningMessage msg : list) {
            msg.getEStoreMessageNotifications().size();
        }
        return list;
    }

    @Override
    public List<ListeningSpeaker> getSpeakers() {
        @SuppressWarnings("unchecked")
        List<ListeningSpeaker> list = getCurrentSession().createQuery(
                "from ListeningSpeaker ").list();
        return list;
    }

    @Override
    public List<ListeningCategory> getCategories() {
        @SuppressWarnings("unchecked")
        List<ListeningCategory> list = getCurrentSession().createQuery(
                "from ListeningCategory ").list();
        return list;
    }

    @Override
    public List<ListeningMessagePicture> getEStoreMessagePicture() {
        @SuppressWarnings("unchecked")
        List<ListeningMessagePicture> list = getCurrentSession().createQuery(
                "from ListeningMessagePicture ").list();
        return list;
    }

    @Override
    public void loadNotifications() {
        for (ListeningNotificationType notificationType : ListeningNotificationType
                .values()) {
            ListeningNotification notification = getEStoreNotification(notificationType);
            if (notification == null) {
                createEStoreNotification(notificationType);
            }
        }
    }

    private void createEStoreNotification(
            ListeningNotificationType notificationType) {
        ListeningNotification messageNotification = new ListeningNotification();
        messageNotification.setEStoreNotificationType(notificationType);
        messageNotification.setCreatedDate(new Date());
        messageNotification.setLastUpdatedDate(new Date());
        messageNotification.setIdentifier(RandomStringUtils.random(30, true,
                true));
        getCurrentSession().persist(messageNotification);
    }

    private ListeningMessageNotification getEStoreMessageNotification(
            ListeningMessage message, ListeningNotification notification) {
        ListeningMessageNotification.Id id = new ListeningMessageNotification.Id(
                message.getId(), notification.getId());

        Query query = getCurrentSession().createQuery(
                "from ListeningMessageNotification e where e.id = :id");
        query.setParameter("id", id);
        return (ListeningMessageNotification) query.uniqueResult();
    }

    private ListeningNotification getEStoreNotification(
            ListeningNotificationType notificationType) {
        Query query = getCurrentSession()
                .createQuery(
                        "from ListeningNotification e where e.eStoreNotificationType = :notificationType");
        query.setParameter("notificationType", notificationType);
        ListeningNotification notification = (ListeningNotification) query
                .uniqueResult();

        return notification;
    }

    private boolean isMessageNotificationExists(ListeningMessage message,
                                                ListeningNotificationType notificationType) {
        Query query = getCurrentSession()
                .createQuery(
                        "from ListeningNotification e where e.eStoreNotificationType = :notificationType");
        query.setParameter("notificationType", notificationType);
        ListeningNotification notification = (ListeningNotification) query
                .uniqueResult();

        ListeningMessageNotification.Id id = new ListeningMessageNotification.Id(
                message.getId(), notification.getId());

        query = getCurrentSession().createQuery(
                "from ListeningMessageNotification e where e.id = :id");
        query.setParameter("id", id);
        ListeningMessageNotification messageNotification = (ListeningMessageNotification) query
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
