package com.laotek.churchguru.web.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.laotek.churchguru.model.shared.enums.NotificationSchedule;
import com.laotek.churchguru.model.shared.enums.NotificationStatus;
import com.laotek.churchguru.model.shared.enums.NotificationType;

public class NotificationDto implements IsSerializable {
    private String recipientFullname;
    private String recipientEmailAddress;

    private NotificationType notificationType;
    private NotificationSchedule notificationSchedule;
    private NotificationStatus notificationStatus;

    public String getRecipientFullname() {
	return recipientFullname;
    }

    public void setRecipientFullname(String recipientFullname) {
	this.recipientFullname = recipientFullname;
    }

    public String getRecipientEmailAddress() {
	return recipientEmailAddress;
    }

    public void setRecipientEmailAddress(String recipientEmailAddress) {
	this.recipientEmailAddress = recipientEmailAddress;
    }

    public NotificationType getNotificationType() {
	return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
	this.notificationType = notificationType;
    }

    public NotificationSchedule getNotificationSchedule() {
	return notificationSchedule;
    }

    public void setNotificationSchedule(
	    NotificationSchedule notificationSchedule) {
	this.notificationSchedule = notificationSchedule;
    }

    public NotificationStatus getNotificationStatus() {
	return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
	this.notificationStatus = notificationStatus;
    }
}
