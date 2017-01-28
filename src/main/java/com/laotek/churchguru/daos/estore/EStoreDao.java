package com.laotek.churchguru.daos.estore;

import java.util.List;
import java.util.Map;

import com.laotek.churchguru.model.EStoreCategory;
import com.laotek.churchguru.model.EStoreMessage;
import com.laotek.churchguru.model.EStoreMessagePicture;
import com.laotek.churchguru.model.EStoreSpeaker;

public interface EStoreDao {
    void createNewMessage(String messageId, String title);

    EStoreMessage getMessageByIdentifier(String identifier);

    List<EStoreMessage> getMessages();

    List<EStoreSpeaker> getSpeakers();

    List<EStoreCategory> getCategories();

    List<EStoreMessagePicture> getEStoreMessagePicture();

    void updateMessage(EStoreMessage eStoreMessage,
	    Map<String, String> otherDetails);

    void loadNotifications();

    Map<String, Boolean> getWorkersSelectedForFreeMessages();
}
