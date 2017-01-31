package com.laotek.churchguru.daos.listening;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.ListeningCategory;
import com.laotek.churchguru.model.ListeningMessage;
import com.laotek.churchguru.model.ListeningMessagePicture;
import com.laotek.churchguru.model.ListeningSpeaker;

public interface ListeningDao {
    void createNewMessage(String messageId, String title);

    ListeningMessage getMessageByIdentifier(String identifier);

    List<ListeningMessage> getMessages();

    List<ListeningSpeaker> getSpeakers();

    List<ListeningCategory> getCategories();

    List<ListeningMessagePicture> getEStoreMessagePicture();

    void updateMessage(ListeningMessage eStoreMessage,
	    Map<String, String> otherDetails);

    void loadNotifications();

    Map<String, Boolean> getWorkersSelectedForFreeMessages();
}
