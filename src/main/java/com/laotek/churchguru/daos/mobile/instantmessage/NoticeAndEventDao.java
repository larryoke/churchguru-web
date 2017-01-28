package com.laotek.churchguru.daos.mobile.instantmessage;

import java.util.Date;
import java.util.List;

import com.laotek.churchguru.model.NoticeAndEvent;
import com.laotek.churchguru.model.shared.enums.BrowseMessagesType;
import com.laotek.churchguru.model.shared.enums.EventTime;

public interface NoticeAndEventDao {

    NoticeAndEvent createNoticeAndEvent(String subject, String clientSessionId);

    void postNoticeAndEventForPreview(int key, String identifier,
	    String subject, String message, Date eventDate,
	    EventTime eventTime, String clientSessionId);

    void completeNoticeAndEvent(int key, String identifier);

    void uploadNoticeAndEventPicture(int key, String identifier,
	    String base64Data, String filename, String contentType);

    NoticeAndEvent getNoticeAndEvent(long id);

    NoticeAndEvent getNoticeAndEvent(long id, String identifier);

    List<NoticeAndEvent> getNoticeAndEvents(
	    BrowseMessagesType browseMessagesType, int index, int count);

    void delete(int id, String identifier);

    NoticeAndEvent duplicate(int id, String identifier, String clientSessionId);

    void removePicture(int id, String identifier);
}
